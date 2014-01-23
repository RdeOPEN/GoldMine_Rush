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
