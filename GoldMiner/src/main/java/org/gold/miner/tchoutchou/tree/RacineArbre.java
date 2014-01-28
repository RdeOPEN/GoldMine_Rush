package org.gold.miner.tchoutchou.tree;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mineur.MinerAction;

public class RacineArbre extends NoeudArbre {

	public RacineArbre(Case caseNoeud) {
		super(caseNoeud);
	}

	@Override
	public Integer calculateShortWayToDestination(ResultatRecherche resultat, Case destination) {

		Integer distance = new Integer(INTEGER_DISTANCE_INITIALE);

		if (this.noeudEst != null) {
			Integer result = noeudEst.calculateShortWayToDestination(resultat, destination);
			distance = getMinDistance(noeudEst.getCase(), resultat, distance, result, MinerAction.EAST);
		}

		if (this.noeudSud != null) {
			Integer result = noeudSud.calculateShortWayToDestination(resultat, destination);
			distance = getMinDistance(noeudSud.getCase(), resultat, distance, result, MinerAction.SOUTH);
		}

		if (this.noeudOuest != null) {
			Integer result = noeudOuest.calculateShortWayToDestination(resultat, destination);
			distance = getMinDistance(noeudOuest.getCase(), resultat, distance, result, MinerAction.WEST);
		}

		if (this.noeudNord != null) {
			Integer result = noeudNord.calculateShortWayToDestination(resultat, destination);
			distance = getMinDistance(noeudNord.getCase(), resultat, distance, result, MinerAction.NORTH);
		}

		if (distance == INTEGER_DISTANCE_INITIALE) {
			// on remet a null la distance si on a pas trouvé de chemin vers la destination
			distance = null;
		}

		return distance;
	}

	private static Integer getMinDistance(Case currentCase, ResultatRecherche resultat, Integer distance, Integer result, MinerAction minerAction) {
		System.out.println("getMinDistance. distance: "+distance+". result: "+result);
		Integer minDistance = distance;
		if (result != null) {
			minDistance = Math.min(result, distance);
			if (minDistance == result) {
				resultat.setDistance(minDistance);
				resultat.setSelectedCase(currentCase);
				resultat.setMinerAction(minerAction);
			}
		}
		return minDistance;
	}

	@Override
	public String toString() {
		return "Racine [caseNoeud=" + caseNoeud + ", noeudNord=" + noeudNord + ", noeudSud=" + noeudSud + ", noeudEst=" + noeudEst + ", noeudOuest="
				+ noeudOuest + "]";
	}

}
