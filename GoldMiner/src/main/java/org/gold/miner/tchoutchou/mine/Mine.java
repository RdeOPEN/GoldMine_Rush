package org.gold.miner.tchoutchou.mine;

import java.util.Collection;

public class Mine {

	private int largeur;
	private int hauteur;
	private int nbDiamants;

	private Case[][] cases;

	public Mine(String mineProperties, String delimiter) {
		String[] envValues = mineProperties.split(delimiter);
		this.largeur = Integer.parseInt(envValues[0]);
		this.hauteur = Integer.parseInt(envValues[1]);
		this.nbDiamants = Integer.parseInt(envValues[2]);
		cases = new Case[largeur][hauteur];
	}

	public void updateCases(LineSight lineSight) {
		this.updateCases(lineSight.getCases());
	}

	public void updateCases(Collection<Case> casesToUpdateMap) {
		for(Case caseUpdate : casesToUpdateMap) {
			Position position = caseUpdate.getPosition();
			cases[position.getPositionX()][position.getPositionY()] = caseUpdate;
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

	public Case[][] getCases() {
		return cases;
	}

	@Override
	public String toString() {
		return "Mine [largeur=" + largeur + ", hauteur=" + hauteur + ", nbDiamants=" + nbDiamants + "]";
	}

}
