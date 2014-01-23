package org.gold.miner.tchoutchou.mine;

public class Position implements Comparable<Position> {

	private int positionX;
	private int positionY;

	public Position(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public Position(String position, String delimiter) {
		parsePosition(position, delimiter);
	}

	private void parsePosition(String initPosition, String delimiter) {
		String[] posValues = initPosition.split(delimiter);
		positionX = Integer.parseInt(posValues[0]);
		positionY = Integer.parseInt(posValues[1]);
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	@Override
	public String toString() {
		return "Position [positionX=" + positionX + ", positionY=" + positionY + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + positionX;
		result = prime * result + positionY;
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
		Position other = (Position) obj;
		if (positionX != other.positionX)
			return false;
		if (positionY != other.positionY)
			return false;
		return true;
	}

	@Override
	public int compareTo(Position pos) {
		int result = Integer.valueOf(positionX).compareTo(Integer.valueOf(pos.positionX));
		if(result == 0) {
			result = Integer.valueOf(positionY).compareTo(Integer.valueOf(pos.positionY));
		}
		return result;
	}

}
