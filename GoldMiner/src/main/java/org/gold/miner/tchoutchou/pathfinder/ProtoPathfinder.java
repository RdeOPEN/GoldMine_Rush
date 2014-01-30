package org.gold.miner.tchoutchou.pathfinder;

import java.util.Map;

import org.gold.miner.tchoutchou.graphe.GrapheFactory;
import org.gold.miner.tchoutchou.graphe.NodeGraphe;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.tree.ResultatRecherche;

public class ProtoPathfinder implements Pathfinder {

	Mine mine;

	public ProtoPathfinder(Mine mine) {
		this.mine = mine;
	}

	@Override
	public MinerAction moveTo(Position currentPosition, Position destination) {
		// recuperation du plan de la mine
		Map<Position, Case> casesInMap = mine.getCasesInMap();

		// construction Graphe
		Map<Position, NodeGraphe> graphe = GrapheFactory.constructGraphe(casesInMap);

		NodeGraphe nodeCurrentPosition = graphe.get(currentPosition);

		ResultatRecherche resultatRecherche = new ResultatRecherche();
		nodeCurrentPosition.calculateShortWayToDestination(resultatRecherche, null, graphe.get(destination).getCase());

		MinerAction minerAction = resultatRecherche.getMinerAction();

		return minerAction;
	}

}
