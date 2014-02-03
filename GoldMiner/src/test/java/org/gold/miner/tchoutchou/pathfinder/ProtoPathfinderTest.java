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
		mine.updateCases(ligneSight);
	}

	@Test
	public void exploreTo_must_return_actions_to_go_to_position() throws Exception {
		Position destination = new Position(0, 4);

		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);

		MinerAction minerAction = protoPathfinder.exploreTo(new Position(2, 2), destination).getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.WEST);

		minerAction = protoPathfinder.exploreTo(new Position(1, 2), destination).getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.WEST);

		minerAction = protoPathfinder.exploreTo(new Position(0, 2), destination).getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.SOUTH);

		minerAction = protoPathfinder.exploreTo(new Position(0, 3), destination).getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.SOUTH);
	}

	@Test
	public void gotoDiamonds_must_return_action_west_to_go_to_diamonds() throws Exception {
		// ++ 00 01 02 03 04
		// 00 S--S--S--S--S
		// 01 S--M--S--M--S
		// 02 M--M--E--M--2
		// 03 M--S--S--M--S
		// 04 3--M--M--M--S
		
		Position startPosition = new Position(10, 10);
		String[] env = new String[] { "S S S S S", "S M S M S", "M M E M 2", "M S S M S", "3 M M M S" };
		LineSight ligneSight = new LineSight(env, startPosition, DELIMITER);
		Mine mine = new Mine("40 40 50", DELIMITER);
		mine.updateCases(ligneSight);
		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);

		MinerAction minerAction = protoPathfinder.gotoDiamonds(new Position(10, 10)).getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.EAST);
	}

	@Test
	public void gotoDiamonds_must_return_action_north_to_go_to_diamonds() throws Exception {
		// ++ 00 01 02 03 04
		// 00 S--S--2--S--S
		// 01 S--M--M--S--S
		// 02 M--S--E--M--S
		// 03 M--S--S--M--S
		// 04 3--M--M--M--S
		
		Position startPosition = new Position(10, 10);
		String[] env = new String[] { "S S 2 S S", "S M M S S", "M S E M S", "M S S M S", "3 M M M S" };
		LineSight ligneSight = new LineSight(env, startPosition, DELIMITER);
		Mine mine = new Mine("40 40 50", DELIMITER);
		mine.updateCases(ligneSight);
		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);

		MinerAction minerAction = protoPathfinder.gotoDiamonds(new Position(10, 10)).getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.NORTH);
	}

}
