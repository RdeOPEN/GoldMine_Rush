package org.gold.miner.tchoutchou.pathfinder;

import org.gold.miner.tchoutchou.mine.Mine;


public class PathfinderFactory {

	public static Pathfinder createPathfinder(PathfinderArchetype pathfinderArchetype, Mine mine) {
		Pathfinder pathfinder = null;
		if (PathfinderArchetype.RANDOMPATHFINDER.equals(pathfinderArchetype)) {
			pathfinder = new RandomPathfinder();
		} else if (PathfinderArchetype.PROTOPATHFINDER.equals(pathfinderArchetype)) {
			pathfinder = new ProtoPathfinder(mine);
		}
		return pathfinder;
	}
	
}
