package org.gold.miner.tchoutchou.graphe;

import java.util.HashMap;
import java.util.Map;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;

public class GrapheFactory {

	public static Map<Position, NodeGraphe> constructGraphe(Map<Position, Case> cases) {
		Map<Position, NodeGraphe> nodes = new HashMap<Position, NodeGraphe>();

		for (Case caseCourante : cases.values()) {
			// creation d'un node et on reli aux autres noeuds en les construisant, si les noeuds existent deja dans nodes on ne les recrees pas.
			NodeGraphe node = nodes.get(caseCourante.getPosition());
			if (node == null) {
				node = new NodeGraphe(caseCourante);
				nodes.put(caseCourante.getPosition(), node);
			}

			// ajout de la case EST
			final Position positionNoeudEst = Position.calculatePositionEast(node.getCase().getPosition());
			node.addNodeEast(addNode(nodes, cases, positionNoeudEst));

			// ajout de la case SUD
			final Position positionNoeudSud = Position.calculatePositionSouth(node.getCase().getPosition());
			node.addNodeSouth(addNode(nodes, cases, positionNoeudSud));

			// ajout de la case OUEST
			final Position positionNoeudOuest = Position.calculatePositionWest(node.getCase().getPosition());
			node.addNodeWest(addNode(nodes, cases, positionNoeudOuest));

			// ajout de la case NORD
			final Position positionNoeudNord = Position.calculatePositionNorth(node.getCase().getPosition());
			node.addNodeNorth(addNode(nodes, cases, positionNoeudNord));

		}
		return nodes;
	}

	private static NodeGraphe addNode(Map<Position, NodeGraphe> nodes, Map<Position, Case> cases, Position positionNoeudVoisin) {

		// si le noeud est deja dans la map on le retourne tel quel
		NodeGraphe nodeAdjacent = nodes.get(positionNoeudVoisin);

		// si le noeud est null alors on le cree
		if (nodeAdjacent == null) {
			Case caseTmp = cases.get(positionNoeudVoisin);
			// si la case a la position X,Y n'est pas null on cree le noeud et on l'ajoute a la map des noeuds
			if (caseTmp != null) {
				nodeAdjacent = new NodeGraphe(caseTmp);
				nodes.put(positionNoeudVoisin, nodeAdjacent);
			}
		}

		return nodeAdjacent;
	}

}
