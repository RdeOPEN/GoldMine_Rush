package org.gold.miner.tchoutchou.mine;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.gold.miner.tchoutchou.FileUtils;

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
		FileUtils.writeInFile("Initialisation de la mine : " + this.toString());
	}

	/**
	 * @param lineSight
	 */
	public void updateCases(LineSight lineSight) {
		FileUtils.writeInFile("Mise à jour de la mine en cours...");
		this.updateCases(lineSight.getCases());
		FileUtils.writeInFile("Fin mise à jour de la mine.");
	}

	/**
	 * @param casesToUpdateMap
	 */
	public void updateCases(Collection<Case> casesToUpdateMap) {
		FileUtils.writeInFile("Etat de la mine avant mise à jour :");
		printMine();
		for (Case caseUpdate : casesToUpdateMap) {
			mapCases.put(caseUpdate.getPosition(), caseUpdate);
		}
		printMine();
		FileUtils.writeInFile("Etat de la mine après mise à jour :");
	}

	public Collection<Case> getDiamondsPositions() {
		FileUtils.writeInFile("Récuperation des positions des diamants...");
		Collection<Case> cases = new HashSet<Case>();
		for (Entry<Position, Case> entry : mapCases.entrySet()) {
			Case value = entry.getValue();
			if (value.getDiamonds() > 0) {
				cases.add(value);
				FileUtils.writeInFile("Position diamants: " + value);
			}
		}
		return cases;
	}

	public void printMine() {
		FileUtils.writeInFile("+++++++++ Début impression plan de la mine +++++++++");
		StringBuilder stringbuilder = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) {
				Case currCase = mapCases.get(new Position(x, y));
				String type = null;
				if (currCase == null) {
					type = "n";
				} else {
					if (TypeTerrain.D.equals(currCase.getType())) {
						type = String.valueOf(currCase.getDiamonds());
					} else {
						type = currCase.getType().name();
					}
				}
				stringbuilder.append(type).append(" ");
			}
			stringbuilder.append("\n");
		}
		FileUtils.writeInFile(stringbuilder.toString());
		FileUtils.writeInFile("+++++++++ Fin impression plan de la mine +++++++++");
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

	@Override
	public String toString() {
		return "Mine [largeur=" + largeur + ", hauteur=" + hauteur + ", nbDiamants=" + nbDiamants + ", number mapCases=" + mapCases.size() + "]";
	}

}
