package org.gold.miner.tchoutchou.mineur;

import java.util.List;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.pathfinder.Pathfinder;

public abstract class Miner {

	private static final int NUMBER_MAX_DIAMONDS = 3;

	protected Pathfinder pathfinder;
	public static Position trolleyPosition;
	protected Position currentPosition;
	protected MinerAction direction;
	protected LineSight lineSight;
	protected List<Position> positionOpponents;
	protected int nbDiamonds;

	/**
	 * @param pathfinder
	 * @param trolleyPosition
	 * @param currentPosition
	 * @param direction
	 *            TODO
	 * @param lineSight
	 * @param positionOpponents
	 * @param nbDiamonds
	 */
	public Miner(Pathfinder pathfinder, Position trolleyPosition, Position currentPosition, MinerAction direction, LineSight lineSight,
			List<Position> positionOpponents, int nbDiamonds) {
		this.pathfinder = pathfinder;
		Miner.trolleyPosition = trolleyPosition; // maj de la position du chariot du joueur (ne devrait pas changer)
		this.currentPosition = currentPosition;
		this.direction = direction;
		this.positionOpponents = positionOpponents;
		this.nbDiamonds = nbDiamonds;
		this.lineSight = lineSight;
	}

	/**
	 * @return MinerAction
	 */
	public abstract MinerAction doAction();

	/**
	 * @return MinerAction
	 */
	public abstract MinerAction move();

	public int getNbDiamonds() {
		return nbDiamonds;
	}

	public void setNbDiamonds(int nbDiamonds) {
		this.nbDiamonds = nbDiamonds;
	}

	/**
	 * Retourne true si le mineur porte au moins 1 diamant
	 * 
	 * @return
	 */
	protected boolean hasDiamonds() {
		return nbDiamonds > 0;
	}

	/**
	 * Retourne true si le mineur porte le nombre maximal de diamants sur lui.
	 * 
	 * @return
	 */
	public boolean isFullDiamonds() {
		return nbDiamonds == NUMBER_MAX_DIAMONDS;
	}

	/**
	 * Retourne le nombre de diamants récupérés sur la position courante du mineur
	 * 
	 * @return diamondsPicked
	 */
	public int pickDiamonds() {
		int diamondsPicked = 0;
		List<Case> diamondsPositions = lineSight.getDiamondsPositions();
		for (Case caseDiamonds : diamondsPositions) {
			if (currentPosition.equals(caseDiamonds.getPosition())) {
				diamondsPicked = getNbDiamonds() + caseDiamonds.getDiamonds();
				break;
			}
		}

		// si on a recupere plus de diamants que l'on ne peut en porter on ramene le nombre de diamants récupérés à 3.
		if (diamondsPicked > NUMBER_MAX_DIAMONDS) {
			diamondsPicked = NUMBER_MAX_DIAMONDS;
		}

		return diamondsPicked;
	}

	/**
	 * Retourne le nombre de diamants déposés sur la position courante du mineur
	 * 
	 * @return diamondsDroped
	 */
	public int dropDiamonds() {
		int diamondsDroped = nbDiamonds;
		nbDiamonds = 0;
		return diamondsDroped;
	}

	public MinerAction getDirection() {
		return direction;
	}

	@Override
	public String toString() {
		return "Miner [pathfinder=" + pathfinder + ", trolleyPosition=" + trolleyPosition + ", currentPosition=" + currentPosition + ", direction=" + direction
				+ ", lineSight=" + lineSight + ", positionOpponents=" + positionOpponents + ", nbDiamonds=" + nbDiamonds + "]";
	}

}
