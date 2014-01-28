package org.gold.miner.tchoutchou.pathfinder;

import java.util.Map;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.tree.Arbre;
import org.gold.miner.tchoutchou.tree.factories.TreeFactory;

public class ProtoPathfinder implements Pathfinder {

	Mine mine;

	public ProtoPathfinder(Mine mine) {
		this.mine = mine;
	}

	@Override
	public MinerAction moveTo(Position currentPosition, Position destination) {
		// recuperation du plan de la mine
		Map<Position, Case> casesInMap = mine.getCasesInMap();

		// construction Arbre
		Arbre arbre = TreeFactory.constructTree(currentPosition, casesInMap);

		MinerAction minerAction = arbre.parcoursArbreTo(casesInMap.get(destination));

		return minerAction;
	}

}
