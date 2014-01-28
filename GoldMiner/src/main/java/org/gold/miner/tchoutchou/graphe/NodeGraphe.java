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
	
}
