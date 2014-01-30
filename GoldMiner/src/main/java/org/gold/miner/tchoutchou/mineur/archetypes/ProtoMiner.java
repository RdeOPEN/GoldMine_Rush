package org.gold.miner.tchoutchou.mineur.archetypes;

import java.util.List;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.Miner;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.pathfinder.Pathfinder;

public class ProtoMiner extends Miner {

	public ProtoMiner(Pathfinder pathfinder, Position trolleyPosition, Position currentPosition, MinerAction directionOfMiner, LineSight lineSight, List<Position> positionOpponents,
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
	@Override
	public MinerAction move() {
		// TODO: destination a determiner (position des diamants, partie de la carte a explorer...)
		Position destination = null;

		// recupere l'action a faire
		return pathfinder.moveTo(currentPosition, destination);
	}
	
	/**
	 * Ordonne au mineur de retourner au chariot
	 * 
	 * @return MinerAction
	 */
	protected MinerAction returnToTheTrolley() {
		return pathfinder.moveTo(currentPosition, trolleyPosition);
	}
	
	/**
	 * Retourne true si le mineur est sur un depot de diamant
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
