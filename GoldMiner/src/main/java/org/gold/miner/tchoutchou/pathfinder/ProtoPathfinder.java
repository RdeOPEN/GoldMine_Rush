package org.gold.miner.tchoutchou.pathfinder;

import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.MinerAction;

public class ProtoPathfinder implements Pathfinder {

	Mine mine;

	public ProtoPathfinder(Mine mine) {
		this.mine = mine;
	}

	@Override
	public MinerAction moveTo(Position currentPosition, Position destination) {
		return null;
	}

}
