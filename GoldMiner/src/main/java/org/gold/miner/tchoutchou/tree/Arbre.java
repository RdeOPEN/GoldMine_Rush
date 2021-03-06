package org.gold.miner.tchoutchou.tree;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mineur.MinerAction;

public class Arbre {

	private RacineArbre noeudRacine;

	public Arbre(RacineArbre racine) {
		this.noeudRacine = racine;
	}

	protected Integer getShortWay(Case destination) {
		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		return noeudRacine.calculateShortWayToDestination(resultat, destination);
	}

	public MinerAction parcoursArbreTo(Case destination) {
		ResultatRechercheChemin resultat = new ResultatRechercheChemin();

		noeudRacine.calculateShortWayToDestination(resultat, destination);

		return resultat.getMinerAction();
	}

	public RacineArbre getNoeudRacine() {
		return noeudRacine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noeudRacine == null) ? 0 : noeudRacine.hashCode());
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
		Arbre other = (Arbre) obj;
		if (noeudRacine == null) {
			if (other.noeudRacine != null)
				return false;
		} else if (!noeudRacine.equals(other.noeudRacine))
			return false;
		return true;
	}

}
