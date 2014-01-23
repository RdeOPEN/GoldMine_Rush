package org.gold.miner.tchoutchou.mine;

public class Case implements Comparable<Case> {

	private Position position;
	private TypeTerrain type;
	private int diamonds;

	public Case(Position position, String type) {
		this.position = position;

		if (isDigits(type)) {
			setTypeAndDiamonds(TypeTerrain.D.toString(), type);
		} else {
			setTypeAndDiamonds(type, "0");
		}
	}

	private void setTypeAndDiamonds(String type, String nbDiamonds) {
		this.type = TypeTerrain.valueOf(type);
		this.diamonds = Integer.parseInt(nbDiamonds);
	}

	private boolean isDigits(String type) {
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
		return type;
	}

	public int getDiamonds() {
		return diamonds;
	}

	@Override
	public String toString() {
		return "Case [position=" + position + ", type=" + type + ", diamonds=" + diamonds + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + diamonds;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public int compareTo(Case c) {
		return position.compareTo(c.position);
	}

}
