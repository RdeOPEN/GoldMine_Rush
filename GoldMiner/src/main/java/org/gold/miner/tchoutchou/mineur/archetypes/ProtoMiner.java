package org.gold.miner.tchoutchou.mineur.archetypes;

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

	public ProtoMiner(Pathfinder pathfinder, Position trolleyPosition, Position currentPosition, MinerAction directionOfMiner, LineSight lineSight,
			List<Position> positionOpponents, int nbDiamonds) {
		super(pathfinder, trolleyPosition, currentPosition, directionOfMiner, lineSight, positionOpponents, nbDiamonds);
	}

	@Override
	public MinerAction doAction() {
		FileUtils.writeInTracesFile("=== Debut decision action prochain tour. ===");
		MinerAction action = null;
		if (hasDiamonds() && trolleyPosition.equals(currentPosition)) {
			FileUtils.writeInTracesFile("Le mineur est sur le chariot et il possede des diamants sur lui : " + this.nbDiamonds);
			action = MinerAction.DROP;
			dropDiamonds();
		} else if (isFullDiamonds()) {
			FileUtils.writeInTracesFile("Le mineur a le maximum de diamants sur lui, il retourne donc au chariot les déposer à la position "
					+ Miner.trolleyPosition);
			action = returnToTheTrolley();
		} else if (!isFullDiamonds() && minerIsOnDiamonds()) {
			FileUtils.writeInTracesFile("Le mineur est sur des diamants et il peut encore en porter donc il va les ramasser à la position "
					+ this.currentPosition);
			action = MinerAction.PICK;
			nbDiamonds = pickDiamonds();
		} else {
			FileUtils.writeInTracesFile("Le mineur doit bouger (recherche diamants ou déplacement exploratoire).");
			action = this.move();
		}

		// il faut retourner une action quoi qu'il arrive donc au hasard
		if (action == null) {
			action = returnToTheTrolley();
			FileUtils.writeInTracesFile("Action par défaut choisi! Action: " + action);
		}

		FileUtils.writeInTracesFile("Action mineur: " + action);

		return action;
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
		return pathfinder.exploreTo(currentPosition, trolleyPosition).getMinerAction();
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
