package org.gold.miner.tchoutchou.tree;

import org.gold.miner.tchoutchou.mine.Case;

public class Noeud {

	public static Noeud racine;
	private Case caseNoeud;
	private Case pere = null;
	private Noeud caseNord = null;
	private Noeud caseSud = null;
	private Noeud caseEst = null;
	private Noeud caseOuest = null;

	public Noeud(Case caseNoeud) {
		this.caseNoeud = caseNoeud;
	}

	public static Noeud getRacine() {
		return Noeud.racine;
	}

	public static void setRacine(Noeud r) {
		Noeud.racine = r;
	}

	public Case getCase() {
		return caseNoeud;
	}

	public void setPere(Case pere) {
		this.pere = pere;
	}

	public void addCaseNord(Noeud caseNord) {
		if (isNotCasePere(caseNord.getCase())) {
			this.caseNord = caseNord;
		}
	}

	public void addCaseSud(Noeud caseSud) {
		if (isNotCasePere(caseSud.getCase())) {
			this.caseSud = caseSud;
		}
	}

	public void addCaseEst(Noeud caseEst) {
		if (isNotCasePere(caseEst.getCase())) {
			this.caseEst = caseEst;
		}
	}

	public void addCaseOuest(Noeud caseOuest) {
		if (isNotCasePere(caseOuest.getCase())) {
			this.caseOuest = caseOuest;
		}
	}

	public boolean isNotCasePere(Case casePere) {
		if (pere == null || casePere == null) {
			return true;
		}

		return pere.getPosition().equals(casePere.getPosition()) ? false : true;
	}

	// methode qui affiche un Noeud et ses descendants
	public Integer parcoursNoeuds(Case destination) {
		Integer distanceToDestination = 1;
		if (!destination.equals(caseNoeud) && caseEst == null && caseSud == null && caseOuest == null && caseNord == null) {
			return null;
		}

		Integer distanceResult = null;
		if (this.caseEst != null) {
			distanceResult = caseEst.parcoursNoeuds(destination);
		}
		if (this.caseSud != null) {
			distanceResult = caseSud.parcoursNoeuds(destination);
		}
		if (this.caseOuest != null) {
			distanceResult = caseOuest.parcoursNoeuds(destination);
		}
		if (this.caseNord != null) {
			distanceResult = caseNord.parcoursNoeuds(destination);
		}

		if (distanceResult != null) {
			distanceToDestination += distanceResult;
		}

		System.out.println(this.toString() + " | Distance: " + distanceToDestination);

		return distanceToDestination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caseEst == null) ? 0 : caseEst.hashCode());
		result = prime * result + ((caseNoeud == null) ? 0 : caseNoeud.hashCode());
		result = prime * result + ((caseNord == null) ? 0 : caseNord.hashCode());
		result = prime * result + ((caseOuest == null) ? 0 : caseOuest.hashCode());
		result = prime * result + ((caseSud == null) ? 0 : caseSud.hashCode());
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
		Noeud other = (Noeud) obj;
		if (caseEst == null) {
			if (other.caseEst != null)
				return false;
		} else if (!caseEst.equals(other.caseEst))
			return false;
		if (caseNoeud == null) {
			if (other.caseNoeud != null)
				return false;
		} else if (!caseNoeud.equals(other.caseNoeud))
			return false;
		if (caseNord == null) {
			if (other.caseNord != null)
				return false;
		} else if (!caseNord.equals(other.caseNord))
			return false;
		if (caseOuest == null) {
			if (other.caseOuest != null)
				return false;
		} else if (!caseOuest.equals(other.caseOuest))
			return false;
		if (caseSud == null) {
			if (other.caseSud != null)
				return false;
		} else if (!caseSud.equals(other.caseSud))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Noeud [caseNoeud=" + caseNoeud + ", caseNord=" + caseNord + ", caseSud=" + caseSud + ", caseEst=" + caseEst + ", caseOuest=" + caseOuest + "]";
	}

}
