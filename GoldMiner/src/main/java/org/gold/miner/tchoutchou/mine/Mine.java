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
	private Map<Integer, MinePart> mapMineParts = new HashMap<Integer, MinePart>();
	private int maxYPosition;
	private int maxXPosition;

	/**
	 * @param mineProperties
	 * @param delimiter
	 */
	public Mine(String mineProperties, String delimiter) {
		String[] envValues = mineProperties.split(delimiter);
		this.largeur = Integer.parseInt(envValues[0]);
		maxXPosition = largeur - 1;
		this.hauteur = Integer.parseInt(envValues[1]);
		maxYPosition = hauteur - 1;

		this.nbDiamants = Integer.parseInt(envValues[2]);
		FileUtils.writeInTracesFile("Initialisation de la mine : " + this.toString());

		int numeroParts = 0;

		// creation des parties de la mine
		for (int x = 0; x < largeur; x = x + 5) {
			int xMin = x;
			int xMax = x + 4;

			for (int y = 0; y < hauteur; y = y + 5) {
				int yMin = y;
				int yMax = y + 4;
				// System.out.println("xMin: " + xMin + " | xMax: " + xMax + " | yMin: " + yMin + " | yMax: " + yMax);

				// si le xMax ou le yMax depasse des limites de la mine on recadre les positions
				if (xMax > maxXPosition) {
					// System.out.println("On a dépassé la largeur limite de la mine! xMax: " + xMax + " | largeur: " + largeur + " | maxXPosition: " +
					// maxXPosition +
					// ". On ramene a " + maxXPosition);
					xMax = maxXPosition;
				}

				if (yMax > maxYPosition) {
					// System.out.println("On a dépassé la hauteur limite de la mine! yMax: " + yMax + " | hauteur: " + hauteur + " | maxYPosition: " +
					// maxYPosition +
					// ". On ramene a " + maxYPosition);
					yMax = maxYPosition;
				}

				mapMineParts.put(++numeroParts, new MinePart(xMin, xMax, yMin, yMax));
			}
		}
	}

	/**
	 * @param lineSight
	 */
	public void updateCases(LineSight lineSight) {
		FileUtils.writeInTracesFile("Mise a jour de la mine en cours...");
		this.updateCases(lineSight.getCases());
		FileUtils.writeInTracesFile("Fin mise a jour de la mine.");
	}

	/**
	 * @param casesToUpdateMap
	 */
	public void updateCases(Collection<Case> casesToUpdateMap) {
		// FileUtils.writeInTracesFile("Etat de la mine avant mise à jour :");
		for (Case caseUpdate : casesToUpdateMap) {
			mapCases.put(caseUpdate.getPosition(), caseUpdate);
		}
		FileUtils.writeInTracesFile("Etat de la mine apres mise a jour :");
		printMine();

		// mise a jour des parties de la mine
		for (MinePart minePart : mapMineParts.values()) {
			minePart.updateCases(casesToUpdateMap);
		}
	}

	/**
	 * @return cases
	 */
	public Collection<Case> getDiamondsPositions() {
		FileUtils.writeInTracesFile("Récuperation des positions des diamants...");
		Collection<Case> cases = new HashSet<Case>();
		for (Entry<Position, Case> entry : mapCases.entrySet()) {
			Case value = entry.getValue();
			if (value.getDiamonds() > 0) {
				cases.add(value);
				FileUtils.writeInTracesFile("Position diamants: " + value);
			}
		}
		return cases;
	}

	/**
	 * 
	 */
	public void printMine() {
		FileUtils.writeInTracesFile("+++++++++ Début impression plan de la mine +++++++++");
		StringBuilder stringbuilder = new StringBuilder();

		stringbuilder.append(" +");
		for (int x = 0; x < largeur; x++) {
			if (x < 10) {
				stringbuilder.append("0");
			}
			stringbuilder.append(x);
			stringbuilder.append(" ");
		}
		stringbuilder.append("\n");

		for (int y = 0; y < hauteur; y++) {
			if (y < 10) {
				stringbuilder.append("0");
			}
			stringbuilder.append(y);
			stringbuilder.append(" ");
			for (int x = 0; x < largeur; x++) {
				Case currCase = mapCases.get(new Position(x, y));
				String type = null;
				if (currCase == null) {
					type = ".";
				} else {
					if (TypeTerrain.D.equals(currCase.getType())) {
						type = String.valueOf(currCase.getDiamonds());
					} else {
						type = currCase.getType().name();
					}
				}
				stringbuilder.append(type).append("  ");
			}
			stringbuilder.append("\n");
		}
		FileUtils.writeInTracesFile(stringbuilder.toString());
		FileUtils.writeInTracesFile("+++++++++ Fin impression plan de la mine +++++++++\n");
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

	/**
	 * @return maxYPosition
	 */
	public int getMaxYPosition() {
		return maxYPosition;
	}

	/**
	 * @return maxXPosition
	 */
	public int getMaxXPosition() {
		return maxXPosition;
	}

	/**
	 * @return mineParts
	 */
	public Collection<MinePart> getMineParts() {
		return mapMineParts.values();
	}

	public Map<Integer, MinePart> getMapMineParts() {
		return mapMineParts;
	}

	@Override
	public String toString() {
		return "Mine [largeur=" + largeur + ", hauteur=" + hauteur + ", nbDiamants=" + nbDiamants + ", number mapCases=" + mapCases.size() + "]";
	}

}
