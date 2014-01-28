package org.gold.miner.tchoutchou.tree;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mineur.MinerAction;

public class Racine extends NoeudArbre {

	public Racine(Case caseNoeud) {
		super(caseNoeud);
	}

	@Override
	public Integer getDirectionToDestination(ResultatRecherche resultat, Case destination) {

		Integer distance = new Integer(9999);

		if (this.noeudEst != null) {
			Integer result = noeudEst.getDirectionToDestination(resultat, destination);
			distance = getMinDistance(noeudEst.getCase(), resultat, distance, result, MinerAction.EAST);
			System.out.println(this.toString() + " | caseEst: " + result);
		}

		if (this.noeudSud != null) {
			Integer result = noeudSud.getDirectionToDestination(resultat, destination);
			distance = getMinDistance(noeudSud.getCase(), resultat, distance, result, MinerAction.SOUTH);
			System.out.println(this.toString() + " | caseSud: " + result);
		}

		if (this.noeudOuest != null) {
			Integer result = noeudOuest.getDirectionToDestination(resultat, destination);
			distance = getMinDistance(noeudOuest.getCase(), resultat, distance, result, MinerAction.WEST);
			System.out.println(this.toString() + " | caseOuest: " + result);
		}

		if (this.noeudNord != null) {
			Integer result = noeudNord.getDirectionToDestination(resultat, destination);
			distance = getMinDistance(noeudNord.getCase(), resultat, distance, result, MinerAction.NORTH);
			System.out.println(this.toString() + " | caseNord: " + result);
		}

		return distance;
	}

	private static Integer getMinDistance(Case currentCase, ResultatRecherche resultat, Integer distance, Integer result, MinerAction minerAction) {
		Integer minDistance = distance;
		if (result != null) {
			minDistance = Math.min(result, distance);
			if (minDistance == result) {
				System.out.println("Distance mini trouvée pour la case : " + currentCase);
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
