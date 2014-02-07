package org.gold.miner.tchoutchou.mineur.archetypes;

import org.gold.miner.tchoutchou.mineur.MinerAction;

public class EvaluationAction {

	private int poidsAction;
	private MinerAction minerAction;

	public EvaluationAction(int poidsAction, MinerAction minerAction) {
		this.poidsAction = poidsAction;
		this.minerAction = minerAction;
	}

	public int getPoidsAction() {
		return poidsAction;
	}

	public MinerAction getMinerAction() {
		return minerAction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((minerAction == null) ? 0 : minerAction.hashCode());
		result = prime * result + poidsAction;
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
		EvaluationAction other = (EvaluationAction) obj;
		if (minerAction != other.minerAction)
			return false;
		if (poidsAction != other.poidsAction)
			return false;
		return true;
	}

}
