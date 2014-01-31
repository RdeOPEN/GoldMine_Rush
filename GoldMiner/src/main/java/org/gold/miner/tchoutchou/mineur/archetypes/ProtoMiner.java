package org.gold.miner.tchoutchou.mineur.archetypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.Miner;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.pathfinder.Pathfinder;
import org.gold.miner.tchoutchou.tree.ResultatRechercheChemin;

public class ProtoMiner extends Miner {

	public ProtoMiner(Pathfinder pathfinder, Position trolleyPosition, Position currentPosition, MinerAction directionOfMiner, LineSight lineSight,
			List<Position> positionOpponents,
			int nbDiamonds) {
		super(pathfinder, trolleyPosition, currentPosition, directionOfMiner, lineSight, positionOpponents, nbDiamonds);
	}

	@Override
	public MinerAction doAction() {
		MinerAction action = null;
		if (hasDiamonds() && trolleyPosition.equals(currentPosition)) {
			action = MinerAction.DROP;
			dropDiamonds();
		} else if (isFullDiamonds()) {
			action = returnToTheTrolley();
		} else if (!isFullDiamonds() && minerIsOnDiamonds()) {
			action = MinerAction.PICK;
			nbDiamonds = pickDiamonds();
		} else {
			action = this.move();
		}

		return action;
	}

	/**
	 * @return MinerAction
	 */
	public MinerAction move() {

		List<ResultatRechercheChemin> resultats = new ArrayList<ResultatRechercheChemin>();
		// La position des diamants est récupéré dans le champ de vision immédiat et on calcul le plus court chemin
		for (Case caseWithDiamonds : lineSight.getDiamondsPositions()) {
			ResultatRechercheChemin resultatRecherche = pathfinder.exploreTo(currentPosition, caseWithDiamonds.getPosition());
			resultats.add(resultatRecherche);

		}

		MinerAction minerAction = null;
		if (!resultats.isEmpty()) {
			// on trie les resultats en fonction de la distance à parcourir
			Collections.sort(resultats);
			minerAction = resultats.get(0).getMinerAction();
		}
		// recupere l'action a faire
		return minerAction;
	}

	/**
	 * Ordonne au mineur de retourner au chariot
	 * 
	 * @return MinerAction
	 */
	protected MinerAction returnToTheTrolley() {
		return pathfinder.getMinerActionToMoveTo(currentPosition, trolleyPosition);
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
