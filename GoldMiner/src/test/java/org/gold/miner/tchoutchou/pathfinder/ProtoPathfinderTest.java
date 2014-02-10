package org.gold.miner.tchoutchou.pathfinder;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.Miner;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.tree.ResultatRechercheChemin;
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

		Position startPosition = new Position(2, 2);
		String[] env = new String[] { "S S S S S", "S M S M S", "M M E M 2", "M S S M S", "3 M M M S" };
		LineSight ligneSight = new LineSight(env, startPosition, DELIMITER);
		Mine mine = new Mine("40 40 50", DELIMITER);
		mine.updateCases(ligneSight);
		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);
		Miner.trolleyPosition = startPosition;

		MinerAction minerAction = protoPathfinder.searchDiamonds(startPosition).getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.EAST);
	}

	@Test
	public void gotoDiamonds_must_return_action_west_to_go_to_diamonds_2() throws Exception {
		// ++ 00 01 02 03 04
		// 00 S--8--8--S--M
		// 01 S--M--S--S--M
		// 02 S--M--X--M--M
		// 03 S--S--M--M--M
		// 04 3--M--M--S--M

		Position startPosition = new Position(2, 2);
		String[] env = new String[] { "S 8 8 S M", "S M S S M", "S M X M M", "S S M M M", "S M M S M" };
		LineSight ligneSight = new LineSight(env, startPosition, DELIMITER);
		Mine mine = new Mine("40 40 50", DELIMITER);
		mine.updateCases(ligneSight);
		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);

		Position trolleyPosition = new Position(2, 2);
		Miner.trolleyPosition = trolleyPosition;

		ResultatRechercheChemin searchDiamonds = protoPathfinder.searchDiamonds(trolleyPosition);
		MinerAction minerAction = searchDiamonds.getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.WEST);
	}

	@Test
	public void gotoDiamonds_must_return_action_north_to_go_to_diamonds() throws Exception {
		// ++ 08 09 10 11 12
		// 08 S--S--2--S--S
		// 09 S--M--M--S--S
		// 10 M--S--E--M--S
		// 11 M--S--S--M--S
		// 12 3--M--M--M--S

		Position startPosition = new Position(10, 10);
		String[] env = new String[] { "S S 2 S S", "S M M S S", "M S E M S", "M S S M S", "3 M M M S" };
		LineSight ligneSight = new LineSight(env, startPosition, DELIMITER);
		Mine mine = new Mine("40 40 50", DELIMITER);
		mine.updateCases(ligneSight);
		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);

		MinerAction minerAction = protoPathfinder.searchDiamonds(new Position(10, 10)).getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.NORTH);
	}

	@Test
	public void gotoDiamonds_must_return_action_east_to_go_to_diamonds() throws Exception {
		// ++ 00 01 02 03 04 05 06 07 08 09
		// 00 S--S--M--S--S--S--S--M--S--S
		// 01 S--E--M--M--S--M--M--M--S--S
		// 02 M--S--E--M--S--M--S--M--S--S
		// 03 M--S--S--M--S--M--S--M--S--S
		// 04 M--M--M--M--S--M--S--M--M--M
		// 05 S--S--M--S--S--M--S--M--S--M
		// 06 S--M--M--S--S--M--S--S--M--M
		// 07 M--S--E--M--S--M--S--E--M--S
		// 08 M--S--S--M--M--M--S--M--3--S
		// 09 S--M--M--M--S--S--S--M--S--S

		Mine mine = new Mine("40 40 50", DELIMITER);
		// premier carre de 5 par 5 de 0,0 à 4,4
		String[] envNW = new String[] { "S S M S S", "S E M M S", "M S E M S", "M S S M S", "M M M M S" };
		// deuxieme carre de 5 par 5 de 5,0 à 9,4
		String[] envNE = new String[] { "S S M S S", "M M M S S", "M S M S S", "M S M S S", "M S M M M" };
		// troisieme carre de 5 par 5 de 0,5 à 4,9
		String[] envSW = new String[] { "S S M S S", "S M M S S", "M S E M S", "M S S M M", "S M M M S" };
		// quatrieme carre de 5 par 5 de 5,5 à 9,9
		String[] envSE = new String[] { "M S M S M", "M S S M M", "M S E M S", "M S M 3 S", "S S M S S" };

		Position startPosition = new Position(1, 1);
		LineSight ligneSight = new LineSight(envNW, new Position(2, 2), DELIMITER);
		mine.updateCases(ligneSight);
		ligneSight = new LineSight(envNE, new Position(7, 2), DELIMITER);
		mine.updateCases(ligneSight);
		ligneSight = new LineSight(envSW, new Position(2, 7), DELIMITER);
		mine.updateCases(ligneSight);
		ligneSight = new LineSight(envSE, new Position(7, 7), DELIMITER);
		mine.updateCases(ligneSight);

		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);

		MinerAction minerAction = protoPathfinder.searchDiamonds(startPosition).getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.EAST);
	}

	@Test
	public void gotoDiamonds_must_return_action_east_to_go_to_diamonds_when_start_on_trolley_position() throws Exception {
		// ++ 00 01 02 03 04 05 06 07 08 09
		// 00 S--S--M--S--S S--S--M--S--S
		// 01 M--X--M--M--S M--M--M--S--S
		// 02 M--S--E--M--S M--S--M--S--S
		// 03 M--S--S--M--S M--S--M--S--S
		// 04 M--M--M--M--S M--S--M--M--M

		// 05 S--S--M--S--S M--S--M--S--M
		// 06 S--M--M--S--S M--S--S--M--M
		// 07 M--S--E--M--S M--S--E--M--S
		// 08 M--S--S--M--M M--S--M--3--S
		// 09 S--M--M--M--S S--S--M--S--S

		Mine mine = new Mine("40 40 50", DELIMITER);
		// premier carre de 5 par 5 de 0,0 à 4,4
		String[] envNW = new String[] { "S S M S S", "M X M M S", "M S E M S", "M S S M S", "M M M M S" };
		String[] envNE = new String[] { "S S M S S", "M M M S S", "M S M S S", "M S M S S", "M S M M M" };
		String[] envSW = new String[] { "S S M S S", "S M M S S", "M S E M S", "M S S M M", "S M M M S" };
		String[] envSE = new String[] { "M S M S M", "M S S M M", "M S E M S", "M S M 3 S", "S S M S S" };

		Position startPosition = new Position(1, 1);
		LineSight ligneSight = new LineSight(envNW, new Position(2, 2), DELIMITER);
		mine.updateCases(ligneSight);
		ligneSight = new LineSight(envNE, new Position(7, 2), DELIMITER);
		mine.updateCases(ligneSight);
		ligneSight = new LineSight(envSW, new Position(2, 7), DELIMITER);
		mine.updateCases(ligneSight);
		ligneSight = new LineSight(envSE, new Position(7, 7), DELIMITER);
		mine.updateCases(ligneSight);

		// on affecte la position du chariot du joueur pour que le pathfinder le considere comme une case qui peut être traverser.
		Miner.trolleyPosition = startPosition;
		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);

		MinerAction minerAction = protoPathfinder.searchDiamonds(startPosition).getMinerAction();
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.EAST);
	}

}
