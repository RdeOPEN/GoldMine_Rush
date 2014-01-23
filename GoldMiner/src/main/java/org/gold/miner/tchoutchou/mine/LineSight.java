package org.gold.miner.tchoutchou.mine;

import java.util.ArrayList;
import java.util.List;

public class LineSight {

	private Position centralPosition;
	private List<Case> cases;

	public LineSight(String[] lineSight, Position positionCentrale, String delimiter) {
		this.centralPosition = positionCentrale;
		cases = new ArrayList<Case>();

		// calcul des coordonnées absolues en fonction de la position centrale
		int coordonneeY = centralPosition.getPositionY() - 2;

		for (int numLigne = 0; numLigne < 5; numLigne++) {
			int coordonneeX = centralPosition.getPositionX() - 2;
			String[] ligne = lineSight[numLigne].split(delimiter);

			for (int numColonne = 0; numColonne < 5; numColonne++) {
				Position position = new Position(coordonneeX, coordonneeY);
				String typeTerrain = ligne[numColonne];
				cases.add(new Case(position, typeTerrain));
				coordonneeX++;
			}
			coordonneeY++;
		}

	}

	public List<Case> getDiamondsPositions() {
		List<Case> diamondsPositions = new ArrayList<Case>();
		for (Case caseMine : cases) {
			if (TypeTerrain.D.equals(caseMine.getType())) {
				diamondsPositions.add(caseMine);
			}
		}
		return diamondsPositions;
	}

	public List<Case> getCases() {
		return cases;
	}

}
