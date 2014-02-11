package org.gold.miner.tchoutchou.mine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MinePart {

	private int xMin;
	private int xMax;
	private int yMin;
	private int yMax;
	private Map<Position, Case> mapCases = new HashMap<Position, Case>();

	public MinePart(int xMin, int xMax, int yMin, int yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	public void updateCases(Collection<Case> casesToUpdate) {
		for (Case caseUpdate : casesToUpdate) {
			Position position = caseUpdate.getPosition();
			int x = position.getPositionX();
			int y = position.getPositionY();

			if ((x >= xMin && x <= xMax) && (y >= yMin && y <= yMax)) {
				mapCases.put(position, caseUpdate);
			}
		}
	}

	public boolean isExplored() {
		boolean explored = false;
		if (!mapCases.isEmpty()) {
			explored = true;
			for (int x = xMin; x <= xMax; x++) {
				for (int y = yMin; y <= yMax; y++) {
					// si une position de la partie de la mine est null alors c'est que l'on a pas exploré toute cette partie
					if (mapCases.get(new Position(x, y)) == null) {
						explored = false;
						break;
					}
				}
			}

		}
		return explored;
	}

	public Map<Position, Case> getMapCases() {
		return mapCases;
	}

	@Override
	public String toString() {
		return "MinePart [xMin=" + xMin + ", xMax=" + xMax + ", yMin=" + yMin + ", yMax=" + yMax + ", mapCases=" + mapCases + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapCases == null) ? 0 : mapCases.hashCode());
		result = prime * result + xMax;
		result = prime * result + xMin;
		result = prime * result + yMax;
		result = prime * result + yMin;
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
		MinePart other = (MinePart) obj;
		if (mapCases == null) {
			if (other.mapCases != null)
				return false;
		} else if (!mapCases.equals(other.mapCases))
			return false;
		if (xMax != other.xMax)
			return false;
		if (xMin != other.xMin)
			return false;
		if (yMax != other.yMax)
			return false;
		if (yMin != other.yMin)
			return false;
		return true;
	}

}
