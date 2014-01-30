package org.gold.miner.tchoutchou.mine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Mine {

	private int largeur;
	private int hauteur;
	private int nbDiamants;

	private Map<Position, Case> mapCases = new HashMap<Position, Case>();

	public Mine(String mineProperties, String delimiter) {
		String[] envValues = mineProperties.split(delimiter);
		this.largeur = Integer.parseInt(envValues[0]);
		this.hauteur = Integer.parseInt(envValues[1]);
		this.nbDiamants = Integer.parseInt(envValues[2]);
	}

	public void update(LineSight lineSight) {
		this.updateCases(lineSight.getCases());
	}

	public void updateCases(Collection<Case> casesToUpdateMap) {
		for (Case caseUpdate : casesToUpdateMap) {
			mapCases.put(caseUpdate.getPosition(), caseUpdate);
		}
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getNbDiamants() {
		return nbDiamants;
	}

	/**
	 * @return casesSet
	 */
	public Collection<Case> getCasesInCollection() {
		return mapCases.values();
	}

	public Map<Position, Case> getCasesInMap() {
		return mapCases;
	}

}
