package org.gold.miner.tchoutchou.pathfinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.gold.miner.tchoutchou.FileUtils;
import org.gold.miner.tchoutchou.graphe.GrapheFactory;
import org.gold.miner.tchoutchou.graphe.NodeGraphe;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.MinePart;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.MinerAction;
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
		nodeCurrentPosition.calculateShortWayToDestination(resultatRecherche, null, graphe.get(destination).getCase(), new String(), new HashSet<NodeGraphe>());

		return resultatRecherche;
	}

	@Override
	public ResultatRechercheChemin searchDiamonds(Position currentPosition) {
		FileUtils.writeInTracesFile("== Entree Pathfinder. methode searchDiamonds ==");

		ResultatRechercheChemin resultatRechercheFinal = null;
		List<ResultatRechercheChemin> resultats = new ArrayList<ResultatRechercheChemin>();

		Collection<Case> diamondsPositions = mine.getDiamondsPositions();

		if (diamondsPositions != null && !diamondsPositions.isEmpty()) {
			FileUtils.writeInTracesFile("Des positions contenant des diamants ont ete trouves: " + diamondsPositions.toString());
			// recuperation du plan de la mine
			final Map<Position, Case> casesInMap = mine.getCasesInMap();

			// construction Graphe de decision
			final Map<Position, NodeGraphe> graphe = GrapheFactory.constructGraphe(casesInMap);

			// On part de la position courante du mineur pour déterminer la direction à prendre vers la destination
			final NodeGraphe nodeCurrentPosition = graphe.get(currentPosition);

			// La position des diamants est récupéré dans le champ de vision immédiat et on calcul le plus court chemin
			for (Case caseWithDiamonds : diamondsPositions) {
				final ResultatRechercheChemin resultatRecherche = new ResultatRechercheChemin();
				NodeGraphe nodeGrapheDestination = graphe.get(caseWithDiamonds.getPosition());
				if (nodeGrapheDestination != null) {
					nodeCurrentPosition.calculateShortWayToDestination(resultatRecherche, null, nodeGrapheDestination.getCase(), new String(),
							new HashSet<NodeGraphe>());
					if (resultatRecherche.isCompleted()) {
						resultats.add(resultatRecherche);
					}
				}
			}

			if (!resultats.isEmpty()) {
				// on trie les resultats en fonction de la distance à parcourir (voir méthode compareTo de la classe ResultatRechercheChemin)
				Collections.sort(resultats);
				ResultatRechercheChemin resultatRechercheCheminSelected = resultats.get(0);
				resultatRechercheFinal = resultatRechercheCheminSelected;
				FileUtils.writeInTracesFile("Resultat de la recherche de diamants : " + resultatRechercheFinal);
			}
		} else {
			FileUtils.writeInTracesFile("Aucun diamant n'a ete trouve dans la mine. Recherche annulee.");
		}
		FileUtils.writeInTracesFile("== Sortie Pathfinder. methode searchDiamonds ==");
		return resultatRechercheFinal;
	}

	@Override
	public ResultatRechercheChemin exploreMine(Position currentPosition, MinerAction minerDirection) {
		FileUtils.writeInTracesFile("== Entree Pathfinder. methode exploreMine ==");
		ResultatRechercheChemin resultatRechercheFinal = null;

		// recuperation du plan de la mine
		final Map<Position, Case> casesInMap = mine.getCasesInMap();
		Case currentCase = casesInMap.get(currentPosition);

		int posX = currentCase.getPosition().getPositionX();
		int posY = currentCase.getPosition().getPositionY();

		Case caseNord = casesInMap.get(new Position(posX, posY - 1));
		if (caseNord != null && caseNord.canPass() && !MinerAction.NORTH.equals(minerDirection)) {
			// on explore la case au Nord
			resultatRechercheFinal = new ResultatRechercheChemin(caseNord, MinerAction.NORTH, 1);
		} else {
			Case caseEst = casesInMap.get(new Position(posX + 1, posY));
			if (caseEst != null && caseEst.canPass() && !MinerAction.EAST.equals(minerDirection)) {
				// on explore la case a l'Est
				resultatRechercheFinal = new ResultatRechercheChemin(caseEst, MinerAction.EAST, 1);
			} else {
				Case caseSud = casesInMap.get(new Position(posX, posY + 1));
				if (caseSud != null && caseSud.canPass() && !MinerAction.SOUTH.equals(minerDirection)) {
					// on explore la case au Sud
					resultatRechercheFinal = new ResultatRechercheChemin(caseSud, MinerAction.SOUTH, 1);
				} else {
					// on explore la case a l'Ouest
					Case caseOuest = casesInMap.get(new Position(posX - 1, posY));
					resultatRechercheFinal = new ResultatRechercheChemin(caseOuest, MinerAction.WEST, 1);
				}
			}
		}

		// on recupere les carres de 5*5 constituant la map
		Map<Integer, MinePart> mapMineParts = mine.getMapMineParts();

		List<Integer> numeroParts = new ArrayList<Integer>();
		for (Entry<Integer, MinePart> entry : mapMineParts.entrySet()) {
			if (entry.getValue().isExplored()) {
				System.out.println("MinePart deja exploré: " + entry.getValue());
			} else {
				numeroParts.add(entry.getKey());
			}
		}

		Collections.sort(numeroParts);
		// recuperation du premier élément de la liste
		Integer keyMinePart = numeroParts.get(0);
		MinePart minePartToExplore = mapMineParts.get(keyMinePart);
		Position destination = minePartToExplore.getPositionToGo();

		resultatRechercheFinal = this.exploreTo(currentPosition, destination);

		FileUtils.writeInTracesFile("== Sortie Pathfinder. methode exploreMine: " + resultatRechercheFinal + "  ==");
		return resultatRechercheFinal;
	}
}
