package org.gold.miner.tchoutchou.pathfinder;

import java.util.Random;

import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.tree.ResultatRechercheChemin;

public class RandomPathfinder implements Pathfinder {

	Random random;

	public RandomPathfinder() {
		this(new Random());
	}

	public RandomPathfinder(Random random) {
		this.random = random;
	}

	@Override
	public ResultatRechercheChemin exploreTo(Position currentPosition, Position destination) {
		MinerAction action = null;
		switch (random.nextInt(4)) {
		case 0:
			action = MinerAction.WEST;
			break;
		case 1:
			action = MinerAction.EAST;
			break;
		case 2:
			action = MinerAction.NORTH;
			break;
		case 3:
			action = MinerAction.SOUTH;
			break;
		}

		return new ResultatRechercheChemin(null, action, null);
	}

	@Override
	public ResultatRechercheChemin searchDiamonds(Position currentPosition) {
		return this.exploreTo(currentPosition, null);
	}

	@Override
	public ResultatRechercheChemin exploreMine(Position currentPosition, MinerAction minerDirection) {
		return this.exploreTo(currentPosition, null);
	}

}
