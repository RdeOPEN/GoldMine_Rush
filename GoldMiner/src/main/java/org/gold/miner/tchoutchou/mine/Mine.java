package org.gold.miner.tchoutchou.mine;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class Mine {

	private int largeur;
	private int hauteur;
	private int nbDiamants;

	private Map<Position, Case> mapCases = new HashMap<Position, Case>();

	/**
	 * @param mineProperties
	 * @param delimiter
	 */
	public Mine(String mineProperties, String delimiter) {
		String[] envValues = mineProperties.split(delimiter);
		this.largeur = Integer.parseInt(envValues[0]);
		this.hauteur = Integer.parseInt(envValues[1]);
		this.nbDiamants = Integer.parseInt(envValues[2]);
	}

	/**
	 * @param lineSight
	 */
	public void updateCases(LineSight lineSight) {
		this.updateCases(lineSight.getCases());
	}

	/**
	 * @param casesToUpdateMap
	 */
	public void updateCases(Collection<Case> casesToUpdateMap) {
		for (Case caseUpdate : casesToUpdateMap) {
			mapCases.put(caseUpdate.getPosition(), caseUpdate);
		}
	}

	public Collection<Case> getDiamondsPositions() {
		Collection<Case> cases = new HashSet<Case>();
		for (Entry<Position, Case> entry : mapCases.entrySet()) {
			Case value = entry.getValue();
			if (value.getDiamonds() > 0) {
				cases.add(value);
			}
		}
		return cases;
	}

	/**
	 * @return largeur
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * @return hauteur
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * @return nbDiamants
	 */
	public int getNbDiamants() {
		return nbDiamants;
	}

	/**
	 * @return casesSet
	 */
	public Collection<Case> getCasesInCollection() {
		return mapCases.values();
	}

	/**
	 * @return mapCases
	 */
	public Map<Position, Case> getCasesInMap() {
		return mapCases;
	}

}
