package org.gold.miner.tchoutchou.tree;

import org.gold.miner.tchoutchou.mine.Case;

public class NoeudArbreImpl extends NoeudArbre {

	private Case pere = null;

	public NoeudArbreImpl(NoeudArbre pere, Case caseNoeud) {
		super(caseNoeud);
		if (pere != null) {
			this.pere = pere.getCase();
		}
	}

	@Override
	public void addNoeudNord(NoeudArbreImpl caseNord) {
		if (isNotCasePere(caseNord.getCase())) {
			this.noeudNord = caseNord;
		}
	}

	@Override
	public void addNoeudSud(NoeudArbreImpl caseSud) {
		if (isNotCasePere(caseSud.getCase())) {
			this.noeudSud = caseSud;
		}
	}

	@Override
	public void addNoeudEst(NoeudArbreImpl caseEst) {
		if (isNotCasePere(caseEst.getCase())) {
			this.noeudEst = caseEst;
		}
	}

	@Override
	public void addNoeudOuest(NoeudArbreImpl caseOuest) {
		if (isNotCasePere(caseOuest.getCase())) {
			this.noeudOuest = caseOuest;
		}
	}

	public boolean isNotCasePere(Case casePere) {
		if (pere == null || casePere == null) {
			return true;
		}

		return pere.getPosition().equals(casePere.getPosition()) ? false : true;
	}

	/**
	 * Parcours un Noeud et ses fils
	 * 
	 * @param destination
	 * @return
	 */
	@Override
	public Integer calculateShortWayToDestination(ResultatRecherche resultat, Case destination) {

		// si la case actuelle est la destination on retourne 1 et si c'est une feuille on retourne null.
		if (destination.equals(caseNoeud)) {
			return 1;
		} else if (isLeafOrDeadEnd() || !caseNoeud.canPass()) {
			return null;
		}

		Integer distance = new Integer(INTEGER_DISTANCE_INITIALE);

		if (this.noeudEst != null) {
			Integer result = noeudEst.calculateShortWayToDestination(resultat, destination);
			if (result != null) {
				distance = Math.min(result, distance);

				if (distance == result)
					resultat.setSelectedCase(caseNoeud);
			}
		}

		if (this.noeudSud != null) {
			Integer result = noeudSud.calculateShortWayToDestination(resultat, destination);
			if (result != null) {
				distance = Math.min(result, distance);
			}
		}

		if (this.noeudOuest != null) {
			Integer result = noeudOuest.calculateShortWayToDestination(resultat, destination);
			if (result != null) {
				distance = Math.min(result, distance);
			}
		}

		if (this.noeudNord != null) {
			Integer result = noeudNord.calculateShortWayToDestination(resultat, destination);
			if (result != null) {
				distance = Math.min(result, distance);
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

	@Override
	public String toString() {
		return "Noeud [pere=" + pere + ", caseNoeud=" + caseNoeud + ", noeudNord=" + noeudNord + ", noeudSud=" + noeudSud + ", noeudEst=" + noeudEst
				+ ", noeudOuest=" + noeudOuest + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pere == null) ? 0 : pere.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoeudArbreImpl other = (NoeudArbreImpl) obj;
		if (pere == null) {
			if (other.pere != null)
				return false;
		} else if (!pere.equals(other.pere))
			return false;
		return true;
	}

}
