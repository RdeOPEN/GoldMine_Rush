package org.gold.miner.tchoutchou.graphe;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;

public class GrapheFactory {

	public static Collection<NodeGraphe> constructGraphe(Collection<Case> cases) {
		Set<NodeGraphe> nodes = new HashSet<NodeGraphe>();
		
		for (Case caseCourante : cases)
		{
			// creation d'un node et on reli aux autres noeuds en les construisants, si les noeuds existent deja dans nodes on ne les recrees pas.
			NodeGraphe node = new NodeGraphe(caseCourante);
			if(nodes.contains(o)) {
				continue;
			}
			
			if() {
				
			}
			
			addNodeEst(node, casesInMap);
			addNodeSud(node, casesInMap);
			addNodeOuest(node, casesInMap);
			addNodeNord(node, casesInMap);
		}
		return nodes;
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
