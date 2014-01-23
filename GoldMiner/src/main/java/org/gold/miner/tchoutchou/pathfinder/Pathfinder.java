package org.gold.miner.tchoutchou.pathfinder;

import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.MinerAction;

public interface Pathfinder {

	MinerAction moveTo(Position currentPosition, Position destination);

}
