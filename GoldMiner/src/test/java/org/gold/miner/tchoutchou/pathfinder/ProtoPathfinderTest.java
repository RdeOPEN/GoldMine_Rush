package org.gold.miner.tchoutchou.pathfinder;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.junit.Before;
import org.junit.Test;

public class ProtoPathfinderTest {

	private static final String DELIMITER = "\\s";
	private Mine mine;

	// ++ 00 01 02 03 04
	// 00 S--S--S--S--S
	// 01 S--M--S--M--S
	// 02 M--M--E--M--S
	// 03 M--S--S--M--S
	// 04 3--M--M--M--S

	String[] env = new String[] { "S S S S S", "S M S M S", "M M E M S", "M S S M S", "3 M M M S" };

	@Before
	public void setUp() {
		Position startPosition = new Position(2, 2);
		LineSight ligneSight = new LineSight(env, startPosition, DELIMITER);
		mine = new Mine("40 40 50", DELIMITER);
		mine.update(ligneSight);
	}

	@Test
	public void moveTo_must_return_actions_to_go_to_diamonds() throws Exception {
		Position destination = new Position(0, 4);

		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);

		MinerAction minerAction = protoPathfinder.moveTo(new Position(2, 2), destination);
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.WEST);

		minerAction = protoPathfinder.moveTo(new Position(1, 2), destination);
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.WEST);

		minerAction = protoPathfinder.moveTo(new Position(0, 2), destination);
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.SOUTH);

		minerAction = protoPathfinder.moveTo(new Position(0, 3), destination);
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.SOUTH);
	}

}
