package org.gold.miner.tchoutchou.graphe;

import java.util.Map;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.tree.NoeudArbreImpl;

public class GrapheFactory {

	public static NodeGraphe constructGraphe(Map<Position, Case> casesInMap, Position startPosition) {

		NodeGraphe racine = new NodeGraphe(casesInMap.get(startPosition));

		addNodeEst(racine, casesInMap);
		addNodeSud(racine, casesInMap);
		addNodeOuest(racine, casesInMap);
		addNodeNord(racine, casesInMap);

		return racine;
	}

	private static void addNodes(NodeGraphe node, Map<Position, Case> casesInMap) {
		
	}
	
	private static void addNodeEst(NodeGraphe node, Map<Position, Case> casesInMap) {
		// ajout de la case EST
		Case caseTmp = casesInMap.get(new Position(node.getPositionX() + 1, node.getPositionY()));
		if (caseTmp != null) {
			NodeGraphe nodeEst = new NodeGraphe(caseTmp);
			node.addNodeEst(nodeEst);
		}
	}

	private static void addNodeSud(NodeGraphe node, Map<Position, Case> casesInMap) {
		// ajout de la case SUD
		Case caseTmp = casesInMap.get(new Position(node.getPositionX(), node.getPositionY() + 1));
		if (caseTmp != null) {
			NodeGraphe nodeSud = new NodeGraphe(caseTmp);
			node.addNodeSud(nodeSud);
		}
	}

	private static void addNodeOuest(NodeGraphe node, Map<Position, Case> casesInMap) {
		// ajout de la case OUEST
		Case caseTmp = casesInMap.get(new Position(node.getPositionX() - 1, node.getPositionY()));
		if (caseTmp != null) {
			NodeGraphe nodeOuest = new NodeGraphe(caseTmp);
			node.addNodeOuest(nodeOuest);
		}
	}

	private static void addNodeNord(NodeGraphe node, Map<Position, Case> casesInMap) {
		// ajout de la case NORD
		Case caseTmp = casesInMap.get(new Position(node.getPositionX(), node.getPositionY() - 1));
		if (caseTmp != null) {
			NodeGraphe nodeNord = new NodeGraphe(caseTmp);
			node.addNodeNord(nodeNord);
		}
	}
}
