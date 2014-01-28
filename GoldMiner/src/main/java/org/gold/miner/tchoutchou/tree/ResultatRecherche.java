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

	public void setDistance(Integer distance) {
		this.distance = distance;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((minerAction == null) ? 0 : minerAction.hashCode());
		result = prime * result + ((selectedCase == null) ? 0 : selectedCase.hashCode());
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
		ResultatRecherche other = (ResultatRecherche) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (minerAction != other.minerAction)
			return false;
		if (selectedCase == null) {
			if (other.selectedCase != null)
				return false;
		} else if (!selectedCase.equals(other.selectedCase))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResultatRecherche [distance=" + distance + ", selectedCase=" + selectedCase + ", minerAction=" + minerAction + "]";
	}

}
