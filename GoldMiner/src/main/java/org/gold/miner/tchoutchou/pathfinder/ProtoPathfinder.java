package org.gold.miner.tchoutchou.pathfinder;

import java.util.Map;

import org.gold.miner.tchoutchou.graphe.GrapheFactory;
import org.gold.miner.tchoutchou.graphe.NodeGraphe;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.tree.ResultatRechercheChemin;

public class ProtoPathfinder implements Pathfinder {

	Mine mine;

	public ProtoPathfinder(Mine mine) {
		this.mine = mine;
	}

	@Override
	public MinerAction getMinerActionToMoveTo(Position currentPosition, Position destination) {
		return this.exploreTo(currentPosition, destination).getMinerAction();
	}

	@Override
	public ResultatRechercheChemin exploreTo(Position currentPosition, Position destination) {
		// recuperation du plan de la mine
		final Map<Position, Case> casesInMap = mine.getCasesInMap();

		// construction Graphe de decision
		final Map<Position, NodeGraphe> graphe = GrapheFactory.constructGraphe(casesInMap);

		// On part de la position courante du mineur pour déterminer la direction à prendre vers la destination
		final NodeGraphe nodeCurrentPosition = graphe.get(currentPosition);
		final ResultatRechercheChemin resultatRecherche = new ResultatRechercheChemin();
		nodeCurrentPosition.calculateShortWayToDestination(resultatRecherche, null, graphe.get(destination).getCase());

		return resultatRecherche;
	}

}
