package org.gold.miner.tchoutchou.mineur.archetypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.Miner;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.mineur.archetypes.ProtoMiner;
import org.gold.miner.tchoutchou.pathfinder.Pathfinder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProtoMinerTest {

	private static final int MAX_DIAMONDS = 3;

	@Mock
	Pathfinder pathfinder;
	@Mock
	LineSight lineSight;

	@Before
	public void setUp() {
		Mockito.when(pathfinder.getMinerActionToMoveTo(null, null)).thenReturn(MinerAction.NORTH);
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
	
}
