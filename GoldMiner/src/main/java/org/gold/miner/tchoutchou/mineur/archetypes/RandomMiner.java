package org.gold.miner.tchoutchou.mineur.archetypes;

import java.util.Random;

import org.gold.miner.tchoutchou.mineur.Miner;
import org.gold.miner.tchoutchou.mineur.MinerAction;

public class RandomMiner extends Miner {

	Random random;

	public RandomMiner() {
		this(new Random());
	}

	public RandomMiner(Random random) {
		super(null, null, null, null, null, null, 0);
		this.random = random;
	}

	@Override
	public MinerAction doAction() {
		MinerAction action = null;
		switch (random.nextInt(MinerAction.values().length)) {
		case 0:
			action = MinerAction.DROP;
			break;
		case 1:
			action = MinerAction.EAST;
			break;
		case 2:
			action = MinerAction.NORTH;
			break;
		case 3:
			action = MinerAction.PICK;
			break;
		case 4:
			action = MinerAction.SHOOT;
			break;
		case 5:
			action = MinerAction.SOUTH;
			break;
		case 6:
			action = MinerAction.WEST;
			break;
		}
		return action;
	}

}
