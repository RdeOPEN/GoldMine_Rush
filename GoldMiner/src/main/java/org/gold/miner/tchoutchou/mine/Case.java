package org.gold.miner.tchoutchou.mine;

import org.gold.miner.tchoutchou.mineur.Miner;

public class Case implements Comparable<Case> {

	private Position position;
	private TypeTerrain typeTerrain;
	private int diamonds;

	public Case(Position position, TypeTerrain typeTerrain, int diamonds) {
		this.position = position;
		if (TypeTerrain.D.equals(typeTerrain)) {
			setTypeAndDiamonds(TypeTerrain.D, diamonds);
		} else {
			setTypeAndDiamonds(typeTerrain, 0);
		}
	}

	public Case(Position position, String typeTerrainStr) {
		this.position = position;

		if (isDigits(typeTerrainStr)) {
			setTypeAndDiamonds(TypeTerrain.D.toString(), typeTerrainStr);
		} else {
			setTypeAndDiamonds(typeTerrainStr, "0");
		}
	}

	private void setTypeAndDiamonds(String type, String nbDiamonds) {
		setTypeAndDiamonds(TypeTerrain.valueOf(type), Integer.parseInt(nbDiamonds));
	}

	private void setTypeAndDiamonds(TypeTerrain typeTerrain, int diamonds) {
		this.typeTerrain = typeTerrain;
		this.diamonds = diamonds;
	}

	public boolean canPass() {
		if (TypeTerrain.S.equals(typeTerrain)) {
			return false;
		}

		if (TypeTerrain.X.equals(typeTerrain)) {
			if (!position.equals(Miner.trolleyPosition)) {
				return false;
			}
		}

		return true;
	}

	private static boolean isDigits(String type) {
		boolean result = false;
		for (Character typeChar : type.toCharArray()) {
			if (Character.isDigit(typeChar)) {
				result = true;
			}
		}
		return result;
	}

	public Position getPosition() {
		return position;
	}

	public TypeTerrain getType() {
		return typeTerrain;
	}

	public int getDiamonds() {
		return diamonds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + diamonds;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((typeTerrain == null) ? 0 : typeTerrain.hashCode());
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
		Case other = (Case) obj;
		if (diamonds != other.diamonds)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (typeTerrain != other.typeTerrain)
			return false;
		return true;
	}

	@Override
	public int compareTo(Case c) {
		return position.compareTo(c.position);
	}

	@Override
	public String toString() {
		return "Case [position=" + position + ", typeTerrain=" + typeTerrain + ", diamonds=" + diamonds + "]";
	}

}
