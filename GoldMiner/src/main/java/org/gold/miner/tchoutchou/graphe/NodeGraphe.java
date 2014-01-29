package org.gold.miner.tchoutchou.graphe;

import org.gold.miner.tchoutchou.mine.Case;

public class NodeGraphe {

	private Case caseNode;

	private NodeGraphe nodeEst;
	private NodeGraphe nodeSud;
	private NodeGraphe nodeOuest;
	private NodeGraphe nodeNord;

	public NodeGraphe(Case caseTmp) {
		caseNode = caseTmp;
	}

	public Case getCase() {
		return caseNode;
	}

	public void addNodeEst(NodeGraphe node) {
		this.nodeEst = node;
	}

	public void addNodeSud(NodeGraphe node) {
		this.nodeSud = node;
	}

	public void addNodeOuest(NodeGraphe node) {
		this.nodeOuest = node;
	}

	public void addNodeNord(NodeGraphe node) {
		this.nodeNord = node;
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
		result = prime * result + ((nodeEst == null) ? 0 : nodeEst.hashCode());
		result = prime * result + ((nodeNord == null) ? 0 : nodeNord.hashCode());
		result = prime * result + ((nodeOuest == null) ? 0 : nodeOuest.hashCode());
		result = prime * result + ((nodeSud == null) ? 0 : nodeSud.hashCode());
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
		if (nodeEst == null) {
			if (other.nodeEst != null)
				return false;
		} else if (!nodeEst.equals(other.nodeEst))
			return false;
		if (nodeNord == null) {
			if (other.nodeNord != null)
				return false;
		} else if (!nodeNord.equals(other.nodeNord))
			return false;
		if (nodeOuest == null) {
			if (other.nodeOuest != null)
				return false;
		} else if (!nodeOuest.equals(other.nodeOuest))
			return false;
		if (nodeSud == null) {
			if (other.nodeSud != null)
				return false;
		} else if (!nodeSud.equals(other.nodeSud))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NodeGraphe [caseNode=" + caseNode + ", nodeEst=" + nodeEst + ", nodeSud=" + nodeSud + ", nodeOuest=" + nodeOuest + ", nodeNord=" + nodeNord
				+ "]";
	}

}
