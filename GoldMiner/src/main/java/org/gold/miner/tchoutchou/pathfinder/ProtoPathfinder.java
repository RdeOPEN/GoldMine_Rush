package org.gold.miner.tchoutchou.pathfinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.gold.miner.tchoutchou.graphe.GrapheFactory;
import org.gold.miner.tchoutchou.graphe.NodeGraphe;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.tree.ResultatRechercheChemin;

public class ProtoPathfinder implements Pathfinder {

	Mine mine;

	public ProtoPathfinder(Mine mine) {
		this.mine = mine;
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

	@Override
	public ResultatRechercheChemin gotoDiamonds(Position currentPosition) {
		List<ResultatRechercheChemin> resultats = new ArrayList<ResultatRechercheChemin>();

		// recuperation du plan de la mine
		final Map<Position, Case> casesInMap = mine.getCasesInMap();

		// construction Graphe de decision
		final Map<Position, NodeGraphe> graphe = GrapheFactory.constructGraphe(casesInMap);

		// On part de la position courante du mineur pour déterminer la direction à prendre vers la destination
		final NodeGraphe nodeCurrentPosition = graphe.get(currentPosition);

		// La position des diamants est récupéré dans le champ de vision immédiat et on calcul le plus court chemin
		for (Case caseWithDiamonds : mine.getDiamondsPositions()) {
			final ResultatRechercheChemin resultatRecherche = new ResultatRechercheChemin();
			nodeCurrentPosition.calculateShortWayToDestination(resultatRecherche, null, graphe.get(caseWithDiamonds.getPosition()).getCase());
			// ResultatRechercheChemin resultatRecherche = this.exploreTo(currentPosition, caseWithDiamonds.getPosition());
			if (resultatRecherche.isCompleted()) {
				System.out.println(resultatRecherche);
				resultats.add(resultatRecherche);
			}
		}

		ResultatRechercheChemin minerAction = null;
		if (!resultats.isEmpty()) {
			// on trie les resultats en fonction de la distance à parcourir (voir méthode compareTo de la classe ResultatRechercheChemin)
			Collections.sort(resultats);
			minerAction = resultats.get(0);
		}
		return minerAction;
	}
}
