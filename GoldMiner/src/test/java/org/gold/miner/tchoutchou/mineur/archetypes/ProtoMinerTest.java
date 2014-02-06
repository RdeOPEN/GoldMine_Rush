package org.gold.miner.tchoutchou.mineur.archetypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.Miner;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.mineur.MinerFactory;
import org.gold.miner.tchoutchou.mineur.archetypes.ProtoMiner;
import org.gold.miner.tchoutchou.pathfinder.Pathfinder;
import org.gold.miner.tchoutchou.pathfinder.ProtoPathfinder;
import org.gold.miner.tchoutchou.tree.ResultatRechercheChemin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProtoMinerTest {

	private static final String DELIMITER = "\\s";
	private static final int MAX_DIAMONDS = 3;

	@Mock
	Pathfinder pathfinder;
	@Mock
	LineSight lineSight;

	@Before
	public void setUp() {
		Mockito.when(pathfinder.exploreTo(Matchers.any(Position.class), Matchers.any(Position.class))).thenReturn(
				new ResultatRechercheChemin(null, MinerAction.NORTH, null));
		Mockito.when(pathfinder.searchDiamonds(Matchers.any(Position.class))).thenReturn(new ResultatRechercheChemin(null, MinerAction.NORTH, null));
		Mockito.when(lineSight.getDiamondsPositions()).thenReturn(Arrays.asList(new Case(new Position(10, 10), "5")));
	}

	@Test
	public void when_miner_is_in_same_position_as_a_diamond_then_pick_action_must_be_selected() throws Exception {
		MinerAction actionExpected = MinerAction.PICK;
		Miner miner = new ProtoMiner(null, new Position(20, 10), new Position(10, 10), null, lineSight, null, 0);

		MinerAction action = miner.doAction();
		Assertions.assertThat(action).isEqualTo(actionExpected);
	}

	@Test
	public void when_miner_has_the_maximal_number_of_diamonds_then_pick_action_must_not_be_selected() throws Exception {
		MinerAction actionExpected = MinerAction.PICK;
		ProtoMiner miner = new ProtoMiner(pathfinder, new Position(20, 10), new Position(10, 10), null, lineSight, null, MAX_DIAMONDS);

		MinerAction action = miner.doAction();
		Assertions.assertThat(action).isNotEqualTo(actionExpected);
	}

	@Test
	public void when_miner_is_on_trolley_and_it_has_no_diamond_then_drop_action_must_not_be_selected() throws Exception {
		MinerAction actionExpected = MinerAction.DROP;
		ProtoMiner miner = new ProtoMiner(pathfinder, new Position(10, 10), new Position(10, 10), null, lineSight, null, 0);

		MinerAction action = miner.doAction();
		Assertions.assertThat(action).isNotEqualTo(actionExpected);
	}

	@Test
	public void when_miner_is_on_trolley_and_it_has_diamonds_then_drop_action_must_be_selected() throws Exception {
		MinerAction actionExpected = MinerAction.DROP;
		ProtoMiner miner = new ProtoMiner(null, new Position(10, 10), new Position(10, 10), null, lineSight, null, MAX_DIAMONDS);

		MinerAction action = miner.doAction();
		Assertions.assertThat(action).isEqualTo(actionExpected);
	}

	@Test
	public void move_action_must_be_selected_by_default() throws Exception {
		List<MinerAction> actionsExpected = Arrays.asList(MinerAction.EAST, MinerAction.WEST, MinerAction.NORTH, MinerAction.SOUTH);
		ProtoMiner miner = new ProtoMiner(pathfinder, new Position(20, 10), new Position(10, 10), null, lineSight, null, 0);

		Mockito.when(lineSight.getDiamondsPositions()).thenReturn(new ArrayList<Case>());

		MinerAction action = miner.doAction();
		Assertions.assertThat(action).isIn(actionsExpected);
	}

	@Test
	public void gotoDiamonds_must_return_actions_to_go_to_diamonds() throws Exception {
		// ++ 20 21 22 23 24
		// 08 M--S--M--M--M
		// 09 S--M--M--S--S
		// 10 7--M--X--S--S
		// 11 S--M--S--M--S
		// 12 M--S--S--M--M

		Position chariotPosition = new Position(22, 10);

		Mine mine = new Mine("40 25 50", DELIMITER);
		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);

		// premier tour, le mineur se trouve sur son chariot
		Position minerPosition = new Position(22, 10);
		String[] env = new String[] { "M S M M M", "S M M S S", "7 M X S S", "S M S M S", "M S S M M" };
		LineSight ligneSight = new LineSight(env, minerPosition, DELIMITER);
		mine.updateCases(ligneSight);

		Miner protoMiner = MinerFactory.createMiner(MinerArchetype.PROTOMINER, protoPathfinder, chariotPosition, minerPosition, MinerAction.EAST, ligneSight,
				new ArrayList<Position>(), 0);

		MinerAction doAction = protoMiner.doAction();
		Assertions.assertThat(doAction).isEqualTo(MinerAction.WEST);

		// second tour, le mineur s'est déplacé vers l'Ouest
		
		// ++ 19 20 21 22 23
		// 08 S--M--S--M--M
		// 09 M--S--M--M--S
		// 10 S--7--M--X--S
		// 11 M--S--M--S--M
		// 12 M--M--S--S--M
		
		minerPosition = new Position(minerPosition.getPositionX() - 1, minerPosition.getPositionY());
		env = new String[] { "S M S M M", "M S M M S", "S 7 M X S", "M S M S M", "M M S S M" };
		ligneSight = new LineSight(env, minerPosition, DELIMITER);
		mine.updateCases(ligneSight);

		protoMiner = MinerFactory.createMiner(MinerArchetype.PROTOMINER, protoPathfinder, chariotPosition, minerPosition, MinerAction.WEST, ligneSight,
				new ArrayList<Position>(), 0);

		doAction = protoMiner.doAction();
		Assertions.assertThat(doAction).isEqualTo(MinerAction.WEST);
	}

}
