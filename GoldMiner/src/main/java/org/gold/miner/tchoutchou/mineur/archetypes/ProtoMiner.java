package org.gold.miner.tchoutchou.mineur.archetypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gold.miner.tchoutchou.FileUtils;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.Miner;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.pathfinder.Pathfinder;
import org.gold.miner.tchoutchou.tree.ResultatRechercheChemin;

public class ProtoMiner extends Miner {

	public static final int POIDS_ACTION_NOT_SELECTED = 0;
	public static final int POIDS_DROP_ACTION = 1000;
	public static final int POIDS_PICK_ACTION = 900;
	public static final int POIDS_RETURN_TROLLEY_ACTION = 800;
	public static final int POIDS_GO_TO_DIAMONDS_ACTION = 700;
	public static final int POIDS_EXPLORE_MINE_ACTION = 100;

	public ProtoMiner(Pathfinder pathfinder, Position trolleyPosition, Position currentPosition, MinerAction directionOfMiner, LineSight lineSight,
			List<Position> positionOpponents, int nbDiamonds) {
		super(pathfinder, trolleyPosition, currentPosition, directionOfMiner, lineSight, positionOpponents, nbDiamonds);
	}

	@Override
	public MinerAction doAction() {
		FileUtils.writeInTracesFile("=== Debut decision action prochain tour. ===");
		MinerAction action = null;

		// evaluation des différentes actions
		List<EvaluationAction> evaluationActions = new ArrayList<EvaluationAction>();
		EvaluationAction evaluateDropAction = evaluateDropAction();
		evaluationActions.add(evaluateDropAction);
		EvaluationAction evaluatePickAction = evaluatePickAction();
		evaluationActions.add(evaluatePickAction);

		if (evaluateDropAction.getPoidsAction() == POIDS_ACTION_NOT_SELECTED || evaluatePickAction.getPoidsAction() == POIDS_ACTION_NOT_SELECTED) {
			// on évite de rechercher le meileur chemin au sein de la mine si on sait deja que l'on va faire DROP ou PICK
			EvaluationAction evaluateReturnToTheTrolleyAction = evaluateReturnToTheTrolleyAction();
			evaluationActions.add(evaluateReturnToTheTrolleyAction);
			EvaluationAction evaluateGoToDiamondsAction = evaluateGoToDiamondsAction();
			evaluationActions.add(evaluateGoToDiamondsAction);
			EvaluationAction evaluateExploreMineAction = evaluateExploreMineAction();
			evaluationActions.add(evaluateExploreMineAction);
		}

		Collections.sort(evaluationActions);
		Collections.reverse(evaluationActions);

		// for (EvaluationAction evaluationAction : evaluationActions) {
		// System.out.println(evaluationAction);
		// }

		EvaluationAction evaluateActionChoisie = evaluationActions.get(0);
		action = evaluateActionChoisie.getMinerAction();

		if (MinerAction.PICK.equals(action)) {
			nbDiamonds = pickDiamonds();
		} else if (MinerAction.DROP.equals(action)) {
			dropDiamonds();
		}

		// si on bouge dans une direction alors la direction du mineur est modifiée
		if (MinerAction.EAST.equals(action) || MinerAction.WEST.equals(action) || MinerAction.NORTH.equals(action) || MinerAction.SOUTH.equals(action)) {
			direction = action;
		}

		FileUtils.writeInTracesFile("Action du mineur choisie après évaluation: " + evaluateActionChoisie);
		return action;
	}

	/**
	 * @return evaluationAction
	 */
	public EvaluationAction evaluateDropAction() {
		FileUtils.writeInTracesFile("Evaluation action DROP en cours...");
		int poids = POIDS_ACTION_NOT_SELECTED;
		if (hasDiamonds() && trolleyPosition.equals(currentPosition)) {
			poids = POIDS_DROP_ACTION;
		}
		EvaluationAction evaluationAction = new EvaluationAction(poids, MinerAction.DROP);
		FileUtils.writeInTracesFile("Evaluation action DROP resultat: " + evaluationAction);
		return evaluationAction;
	}

	/**
	 * @return evaluationAction
	 */
	public EvaluationAction evaluatePickAction() {
		FileUtils.writeInTracesFile("Evaluation action PICK en cours...");
		int poids = POIDS_ACTION_NOT_SELECTED;
		if (!isFullDiamonds() && minerIsOnDiamonds()) {
			poids = POIDS_PICK_ACTION;
		}
		EvaluationAction evaluationAction = new EvaluationAction(poids, MinerAction.PICK);
		FileUtils.writeInTracesFile("Evaluation action PICK resultat: " + evaluationAction);
		return evaluationAction;
	}

	/**
	 * @return evaluationAction
	 */
	public EvaluationAction evaluateReturnToTheTrolleyAction() {
		FileUtils.writeInTracesFile("Evaluation action RETURN TO TROLLEY en cours...");
		FileUtils.writeInTracesFile("Position chariot mineur: " + trolleyPosition);
		int poids = POIDS_ACTION_NOT_SELECTED;
		MinerAction action = null;
		if (isFullDiamonds()) {
			poids = POIDS_RETURN_TROLLEY_ACTION;
			action = returnToTheTrolley();
		}
		EvaluationAction evaluationAction = new EvaluationAction(poids, action);
		FileUtils.writeInTracesFile("Evaluation action RETURN TO TROLLEY resultat: " + evaluationAction);
		return evaluationAction;
	}

	/**
	 * @return evaluationAction
	 */
	public EvaluationAction evaluateExploreMineAction() {
		FileUtils.writeInTracesFile("Evaluation action EXPLORE MINE en cours...");

		int poids = POIDS_ACTION_NOT_SELECTED;
		MinerAction action = null;

		ResultatRechercheChemin exploreMine = pathfinder.exploreMine(currentPosition, this.direction);

		if (exploreMine != null) {
			poids = POIDS_EXPLORE_MINE_ACTION;
			action = exploreMine.getMinerAction();
		}

		EvaluationAction evaluationAction = new EvaluationAction(poids, action);
		FileUtils.writeInTracesFile("Evaluation action EXPLORE MINE resultat: " + evaluationAction);
		return evaluationAction;
	}

	/**
	 * @return evaluationAction
	 */
	public EvaluationAction evaluateGoToDiamondsAction() {
		FileUtils.writeInTracesFile("Evaluation action GO TO DIAMONDS en cours...");
		ResultatRechercheChemin searchDiamondsResultat = pathfinder.searchDiamonds(currentPosition);

		int poids = POIDS_ACTION_NOT_SELECTED;
		MinerAction action = null;
		if (searchDiamondsResultat != null) {
			poids = POIDS_GO_TO_DIAMONDS_ACTION;
			action = searchDiamondsResultat.getMinerAction();
		}

		EvaluationAction evaluationAction = new EvaluationAction(poids, action);
		FileUtils.writeInTracesFile("Evaluation action GO TO DIAMONDS resultat: " + evaluationAction);
		return evaluationAction;
	}

	/**
	 * @return MinerAction
	 */
	public MinerAction move() {
		// recupere l'action a faire
		ResultatRechercheChemin resultatRecherche = pathfinder.searchDiamonds(currentPosition);
		return resultatRecherche != null ? resultatRecherche.getMinerAction() : null;
	}

	/**
	 * Ordonne au mineur de retourner au chariot
	 * 
	 * @return MinerAction
	 */
	protected MinerAction returnToTheTrolley() {
		return pathfinder.exploreTo(currentPosition, Miner.trolleyPosition).getMinerAction();
	}

	/**
	 * Retourne true si le mineur est sur un depot de diamant
	 * 
	 * @return
	 */
	protected boolean minerIsOnDiamonds() {
		boolean result = false;
		List<Case> diamondsPositions = lineSight.getDiamondsPositions();
		for (Case caseDiamonds : diamondsPositions) {
			if (currentPosition.equals(caseDiamonds.getPosition())) {
				result = true;
				break;
			}
		}
		return result;
	}

}
