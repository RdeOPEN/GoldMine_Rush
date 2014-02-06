package org.gold.miner.tchoutchou.pathfinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.gold.miner.tchoutchou.FileUtils;
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
		nodeCurrentPosition.calculateShortWayToDestination(resultatRecherche, null, graphe.get(destination).getCase(), new StringBuilder());

		return resultatRecherche;
	}

	@Override
	public ResultatRechercheChemin searchDiamonds(Position currentPosition) {
		FileUtils.writeInFile("== Entree Pathfinder. méthode searchDiamonds ==");

		ResultatRechercheChemin resultatRechercheFinal = null;
		List<ResultatRechercheChemin> resultats = new ArrayList<ResultatRechercheChemin>();

		Collection<Case> diamondsPositions = mine.getDiamondsPositions();

		if (diamondsPositions != null && !diamondsPositions.isEmpty()) {
			FileUtils.writeInFile("Des positions contenant des diamants ont été trouvés: " + diamondsPositions.toString());
			// recuperation du plan de la mine
			final Map<Position, Case> casesInMap = mine.getCasesInMap();

			FileUtils.writeInFile("Construction graphe de la mine.");
			// construction Graphe de decision
			final Map<Position, NodeGraphe> graphe = GrapheFactory.constructGraphe(casesInMap);

			// On part de la position courante du mineur pour déterminer la direction à prendre vers la destination
			final NodeGraphe nodeCurrentPosition = graphe.get(currentPosition);

			// La position des diamants est récupéré dans le champ de vision immédiat et on calcul le plus court chemin
			for (Case caseWithDiamonds : diamondsPositions) {
				final ResultatRechercheChemin resultatRecherche = new ResultatRechercheChemin();
				NodeGraphe nodeGrapheDestination = graphe.get(caseWithDiamonds.getPosition());
				// System.out.println(nodeGrapheDestination);
				if (nodeGrapheDestination != null) {
					nodeCurrentPosition.calculateShortWayToDestination(resultatRecherche, null, nodeGrapheDestination.getCase(), new StringBuilder());
					if (resultatRecherche.isCompleted()) {
						// System.out.println(resultatRecherche);
						resultats.add(resultatRecherche);
					}
				}
			}

			if (!resultats.isEmpty()) {
				// on trie les resultats en fonction de la distance à parcourir (voir méthode compareTo de la classe ResultatRechercheChemin)
				Collections.sort(resultats);
				ResultatRechercheChemin resultatRechercheCheminSelected = resultats.get(0);
				resultatRechercheFinal = resultatRechercheCheminSelected;
				// System.out.println("ResultatRechercheCheminSelected: " + resultatRechercheCheminSelected);
				FileUtils.writeInFile("Resultat de la recherche de diamants : " + resultatRechercheFinal);
			}
		} else {
			FileUtils.writeInFile("Aucun diamants n'a été trouvé dans la mine. Aucune action ne sera effectuée.");
		}
		FileUtils.writeInFile("== Sortie Pathfinder ==");
		return resultatRechercheFinal;
	}
}
