package org.gold.miner.tchoutchou.tree;

import org.gold.miner.tchoutchou.mine.Case;

public abstract class NoeudArbre {

	protected static final int INTEGER_DISTANCE_INITIALE = 9999;
	
	protected Case caseNoeud;
	protected NoeudArbreImpl noeudNord = null;
	protected NoeudArbreImpl noeudSud = null;
	protected NoeudArbreImpl noeudEst = null;
	protected NoeudArbreImpl noeudOuest = null;

	public NoeudArbre(Case caseNoeud) {
		this.caseNoeud = caseNoeud;
	}

	abstract Integer calculateShortWayToDestination(ResultatRechercheChemin resultat, Case destination);

	public Case getCase() {
		return this.caseNoeud;
	}

	public void addNoeudNord(NoeudArbreImpl caseNord) {
		this.noeudNord = caseNord;
	}

	public void addNoeudSud(NoeudArbreImpl caseSud) {
		this.noeudSud = caseSud;
	}

	public void addNoeudEst(NoeudArbreImpl caseEst) {
		this.noeudEst = caseEst;
	}

	public void addNoeudOuest(NoeudArbreImpl caseOuest) {
		this.noeudOuest = caseOuest;
	}

	public boolean isLeafOrDeadEnd() {
		return noeudEst == null && noeudSud == null && noeudOuest == null && noeudNord == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noeudEst == null) ? 0 : noeudEst.hashCode());
		result = prime * result + ((caseNoeud == null) ? 0 : caseNoeud.hashCode());
		result = prime * result + ((noeudNord == null) ? 0 : noeudNord.hashCode());
		result = prime * result + ((noeudOuest == null) ? 0 : noeudOuest.hashCode());
		result = prime * result + ((noeudSud == null) ? 0 : noeudSud.hashCode());
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
		NoeudArbre other = (NoeudArbre) obj;
		if (noeudEst == null) {
			if (other.noeudEst != null)
				return false;
		} else if (!noeudEst.equals(other.noeudEst))
			return false;
		if (caseNoeud == null) {
			if (other.caseNoeud != null)
				return false;
		} else if (!caseNoeud.equals(other.caseNoeud))
			return false;
		if (noeudNord == null) {
			if (other.noeudNord != null)
				return false;
		} else if (!noeudNord.equals(other.noeudNord))
			return false;
		if (noeudOuest == null) {
			if (other.noeudOuest != null)
				return false;
		} else if (!noeudOuest.equals(other.noeudOuest))
			return false;
		if (noeudSud == null) {
			if (other.noeudSud != null)
				return false;
		} else if (!noeudSud.equals(other.noeudSud))
			return false;
		return true;
	}

}
