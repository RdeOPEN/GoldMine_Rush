package org.gold.miner.tchoutchou.graphe;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.tree.ResultatRecherche;

public class NodeGraphe {

	protected static final int INTEGER_DISTANCE_INITIALE = 9999;

	private Case caseNode;

	private NodeGraphe nodeEast;
	private NodeGraphe nodeSouth;
	private NodeGraphe nodeWest;
	private NodeGraphe nodeNorth;

	public NodeGraphe(Case caseTmp) {
		caseNode = caseTmp;
	}

	public Integer calculateShortWayToDestination(ResultatRecherche resultat, Case destination) {

		// si la case actuelle est la destination on retourne 1 et si c'est une feuille on retourne null.
		if (destination.equals(caseNode)) {
			return 1;
		} else if (!caseNode.canPass()) {
			return null;
		}

		Integer distance = new Integer(INTEGER_DISTANCE_INITIALE);

		if (this.nodeEast != null) {
			Integer result = nodeEast.calculateShortWayToDestination(resultat, destination);
			if (result != null) {
				distance = getMinDistance(distance, result);

				if (distance == result)
					resultat.setSelectedCase(caseNode);
			}
		}

		if (this.nodeSouth != null) {
			Integer result = nodeSouth.calculateShortWayToDestination(resultat, destination);
			if (result != null) {
				distance = getMinDistance(distance, result);
			}
		}

		if (this.nodeWest != null) {
			Integer result = nodeWest.calculateShortWayToDestination(resultat, destination);
			if (result != null) {
				distance = getMinDistance(distance, result);
			}
		}

		if (this.nodeNorth != null) {
			Integer result = nodeNorth.calculateShortWayToDestination(resultat, destination);
			if (result != null) {
				distance = getMinDistance(distance, result);
			}
		}

		if (distance == INTEGER_DISTANCE_INITIALE) {
			// on remet a null la distance si on a pas trouvé la destination à un niveau inférieur
			distance = null;
		} else {
			// on ajoute 1 si on a trouvé la destination à un niveau inférieur
			distance++;
		}

		return distance;
	}

	private static int getMinDistance(Integer distance, Integer result) {
		if (distance == null && result != null) {
			return result;
		}
		return Math.min(result, distance);
	}

	public Case getCase() {
		return caseNode;
	}

	public void addNodeEast(NodeGraphe node) {
		this.nodeEast = node;
	}

	public void addNodeSouth(NodeGraphe node) {
		this.nodeSouth = node;
	}

	public void addNodeWest(NodeGraphe node) {
		this.nodeWest = node;
	}

	public void addNodeNorth(NodeGraphe node) {
		this.nodeNorth = node;
	}

	public NodeGraphe getNodeEast() {
		return nodeEast;
	}

	public NodeGraphe getNodeSouth() {
		return nodeSouth;
	}

	public NodeGraphe getNodeWest() {
		return nodeWest;
	}

	public NodeGraphe getNodeNorth() {
		return nodeNorth;
	}

	public int getPositionX() {
		return caseNode.getPosition().getPositionX();
	}

	public int getPositionY() {
		return caseNode.getPosition().getPositionY();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeEast == null) ? 0 : nodeEast.hashCode());
		result = prime * result + ((nodeNorth == null) ? 0 : nodeNorth.hashCode());
		result = prime * result + ((nodeWest == null) ? 0 : nodeWest.hashCode());
		result = prime * result + ((nodeSouth == null) ? 0 : nodeSouth.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeGraphe other = (NodeGraphe) obj;
		if (nodeEast == null) {
			if (other.nodeEast != null)
				return false;
		} else if (!nodeEast.equals(other.nodeEast))
			return false;
		if (nodeNorth == null) {
			if (other.nodeNorth != null)
				return false;
		} else if (!nodeNorth.equals(other.nodeNorth))
			return false;
		if (nodeWest == null) {
			if (other.nodeWest != null)
				return false;
		} else if (!nodeWest.equals(other.nodeWest))
			return false;
		if (nodeSouth == null) {
			if (other.nodeSouth != null)
				return false;
		} else if (!nodeSouth.equals(other.nodeSouth))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NodeGraphe [caseNode=" + caseNode + ", nodeEst=" + nodeEast + ", nodeSud=" + nodeSouth + ", nodeOuest=" + nodeWest + ", nodeNord=" + nodeNorth
				+ "]";
	}

}
