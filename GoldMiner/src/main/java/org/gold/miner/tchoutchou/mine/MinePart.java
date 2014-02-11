package org.gold.miner.tchoutchou.mine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MinePart {

	private Map<Position, Case> mapCases = new HashMap<Position, Case>();
	private int xMin;
	private int xMax;
	private int yMin;
	private int yMax;

	public MinePart(int xMin, int xMax, int yMin, int yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	public void updateCases(Collection<Case> casesToUpdateMap) {
		for (Case caseUpdate : casesToUpdateMap) {
			Position position = caseUpdate.getPosition();
			int x = position.getPositionX();
			int y = position.getPositionY();

			if ((x >= xMin && x <= xMax) && (y >= yMin && y <= yMax)) {
				mapCases.put(position, caseUpdate);
			}
		}
	}

	public Map<Position, Case> getMapCases() {
		return mapCases;
	}

}
