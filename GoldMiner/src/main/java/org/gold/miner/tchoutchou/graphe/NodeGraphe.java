package org.gold.miner.tchoutchou.graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.tree.ResultatRechercheChemin;

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

	public Integer calculateShortWayToDestination(ResultatRechercheChemin resultat, NodeGraphe previousNode, Case destination) {

		// si la case actuelle est la destination on retourne 1 pour dire qu'on l'a trouvé.
		if (destination.equals(caseNode)) {
			return 1;
		} else if (!caseNode.canPass()) {
			return null;
		}

		List<ResultatRechercheChemin> resultats = new ArrayList<ResultatRechercheChemin>();

		if (this.nodeEast != null && !nodeEast.equals(previousNode)) {
			Integer result = nodeEast.calculateShortWayToDestination(resultat, this, destination);
			if (result != null) {
				ResultatRechercheChemin resultatEst = new ResultatRechercheChemin(nodeEast.getCase(), MinerAction.EAST, result);
				resultats.add(resultatEst);
			}
		}

		if (this.nodeSouth != null && !nodeSouth.equals(previousNode)) {
			Integer result = nodeSouth.calculateShortWayToDestination(resultat, this, destination);
			if (result != null) {
				ResultatRechercheChemin resultatEst = new ResultatRechercheChemin(nodeSouth.getCase(), MinerAction.SOUTH, result);
				resultats.add(resultatEst);
			}
		}

		if (this.nodeWest != null && !nodeWest.equals(previousNode)) {
			Integer result = nodeWest.calculateShortWayToDestination(resultat, this, destination);
			if (result != null) {
				ResultatRechercheChemin resultatEst = new ResultatRechercheChemin(nodeWest.getCase(), MinerAction.WEST, result);
				resultats.add(resultatEst);
			}
		}

		if (this.nodeNorth != null && !nodeNorth.equals(previousNode)) {
			Integer result = nodeNorth.calculateShortWayToDestination(resultat, this, destination);
			if (result != null) {
				ResultatRechercheChemin resultatEst = new ResultatRechercheChemin(nodeNorth.getCase(), MinerAction.NORTH, result);
				resultats.add(resultatEst);
			}
		}

		Integer distance = null;
		if (!resultats.isEmpty()) {
			// on tri les resultats en fonction de la distance a la destination
			Collections.sort(resultats);
			ResultatRechercheChemin resultatMinDistance = resultats.get(0);
			distance = resultatMinDistance.getDistance();
			resultat.setDistance(distance);
			resultat.setMinerAction(resultatMinDistance.getMinerAction());
			resultat.setSelectedCase(resultatMinDistance.getSelectedCase());
		}

		if (distance != null && previousNode != null) {
			// on ajoute 1 si on a trouvé la destination à un niveau inférieur
			// si previousNode vaut null alors on est a la racine et on incremente pas le distance
			distance++;
		}

		return distance;
	}

	public static Integer getMinDistance(Integer distance, Integer result) {
		if (distance == null && result == null) {
			return null;
		} else if (distance == null && result != null) {
			return result;
		} else if (result == null) {
			return distance;
		}
		return Math.min(distance, result);
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
		result = prime * result + ((caseNode == null) ? 0 : caseNode.hashCode());
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
		if (caseNode == null) {
			if (other.caseNode != null)
				return false;
		} else if (!caseNode.equals(other.caseNode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NodeGraphe [caseNode=" + caseNode + "]";
	}

}
