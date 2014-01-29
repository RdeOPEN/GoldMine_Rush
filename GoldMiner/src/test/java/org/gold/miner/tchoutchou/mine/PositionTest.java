package org.gold.miner.tchoutchou.mine;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class PositionTest {

	@Test
	public void calculatePositionEast() throws Exception {
		Position positionExpected = new Position(11, 10);
		
		Position position = new Position(10, 10);
		Position positionResult = Position.calculatePositionEast(position);
		
		Assertions.assertThat(positionResult).isEqualTo(positionExpected);
	}
	
	@Test
	public void calculatePositionSouth() throws Exception {
		Position positionExpected = new Position(10, 11);
		
		Position position = new Position(10, 10);
		Position positionResult = Position.calculatePositionSouth(position);
		
		Assertions.assertThat(positionResult).isEqualTo(positionExpected);
	}
	
	@Test
	public void calculatePositionWest() throws Exception {
		Position positionExpected = new Position(9, 10);
		
		Position position = new Position(10, 10);
		Position positionResult = Position.calculatePositionWest(position);
		
		Assertions.assertThat(positionResult).isEqualTo(positionExpected);
	}
	
	@Test
	public void calculatePositionNorth() throws Exception {
		Position positionExpected = new Position(10, 9);
		
		Position position = new Position(10, 10);
		Position positionResult = Position.calculatePositionNorth(position);
		
		Assertions.assertThat(positionResult).isEqualTo(positionExpected);
	}

}
