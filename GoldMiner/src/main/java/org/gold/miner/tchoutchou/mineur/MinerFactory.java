package org.gold.miner.tchoutchou.mineur;

import java.util.List;

import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.archetypes.MinerArchetype;
import org.gold.miner.tchoutchou.mineur.archetypes.ProtoMiner;
import org.gold.miner.tchoutchou.mineur.archetypes.RandomMiner;
import org.gold.miner.tchoutchou.pathfinder.Pathfinder;

public class MinerFactory {

	public static Miner createMiner(MinerArchetype minerArchetype, Pathfinder pathfinder, Position positionChariot, Position currentPositionMiner,
			MinerAction directionOfMiner, LineSight lineSight, List<Position> positionOpponents, int nbDiamonds) {
		Miner miner = null;
		if (MinerArchetype.RANDOM_MINER.equals(minerArchetype)) {
			miner = createRandomMiner();
		} else if (MinerArchetype.PROTOMINER.equals(minerArchetype)) {
			miner = createProtoMiner(pathfinder, positionChariot, currentPositionMiner, directionOfMiner, lineSight, positionOpponents, nbDiamonds);
		}
		return miner;
	}

	private static Miner createProtoMiner(Pathfinder pathFinder, Position positionChariot, Position currentPositionMiner, MinerAction directionOfMiner, LineSight lineSight,
			List<Position> positionOpponents, int nbDiamonds) {
		return new ProtoMiner(pathFinder, positionChariot, currentPositionMiner, directionOfMiner, lineSight, positionOpponents, nbDiamonds);
	}

	private static Miner createRandomMiner() {
		return new RandomMiner();
	}
	
}
