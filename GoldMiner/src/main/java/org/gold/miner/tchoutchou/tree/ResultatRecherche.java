package org.gold.miner.tchoutchou.tree;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mineur.MinerAction;

public class ResultatRecherche {

	private Integer distance;
	private Case selectedCase;
	private MinerAction minerAction;

	public Integer getDistance() {
		return distance;
	}

	public void incrementeDistance() {
		this.distance++;
	}

	public Case getSelectedCase() {
		return selectedCase;
	}

	public void setSelectedCase(Case currentCase) {
		this.selectedCase = currentCase;
	}

	public MinerAction getMinerAction() {
		return minerAction;
	}

	public void setMinerAction(MinerAction minerAction) {
		this.minerAction = minerAction;
	}

}
