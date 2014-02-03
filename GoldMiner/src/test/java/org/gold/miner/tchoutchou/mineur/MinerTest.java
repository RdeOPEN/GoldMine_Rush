package org.gold.miner.tchoutchou.mineur;

import java.util.Arrays;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.Miner;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.pathfinder.Pathfinder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MinerTest {

	@Mock
	LineSight lineSight;

	@Test
	public void miner_has_Diamonds_return_true_if_number_of_diamonds_is_equal_to_0() throws Exception {
		Miner miner = createMinerStub(null, null, null, null, null, 0);
		miner.setNbDiamonds(0);
		Assert.assertFalse(miner.hasDiamonds());
	}

	@Test
	public void miner_has_Diamonds_return_true_if_number_of_diamonds_is_upper_to_0() throws Exception {
		Miner miner = createMinerStub(null, null, null, null, null, 0);
		miner.setNbDiamonds(1);
		Assert.assertTrue(miner.hasDiamonds());
	}

	@Test
	public void miner_has_the_maximal_number_of_diamonds() throws Exception {
		Miner miner = createMinerStub(null, null, null, null, null, 0);
		miner.setNbDiamonds(3);
		Assert.assertTrue(miner.isFullDiamonds());
	}

	@Test
	public void miner_has_not_the_maximal_number_of_diamonds() throws Exception {
		Miner miner = createMinerStub(null, null, null, null, null, 0);
		miner.setNbDiamonds(2);
		Assert.assertFalse(miner.isFullDiamonds());
	}

	@Test
	public void miner_pick_the_maximal_number_of_diamonds() throws Exception {
		Mockito.when(lineSight.getDiamondsPositions()).thenReturn(Arrays.asList(new Case(new Position(10, 10), "5")));
		Miner miner = createMinerStub(null, new Position(20, 10), new Position(10, 10), lineSight, null, 0);
		Assertions.assertThat(miner.pickDiamonds()).isEqualTo(3);
	}

	@Test
	public void miner_pick_the_maximal_number_of_diamonds_when_number_of_diamonds_is_lower_than_three() throws Exception {
		Mockito.when(lineSight.getDiamondsPositions()).thenReturn(Arrays.asList(new Case(new Position(10, 10), "2")));
		Miner miner = createMinerStub(null, new Position(20, 10), new Position(10, 10), lineSight, null, 0);
		Assertions.assertThat(miner.pickDiamonds()).isEqualTo(2);
	}

	@Test
	public void miner_pick_the_maximal_number_of_diamonds_when_miner_has_already_diamonds() throws Exception {
		Mockito.when(lineSight.getDiamondsPositions()).thenReturn(Arrays.asList(new Case(new Position(10, 10), "2")));
		Miner miner = createMinerStub(null, new Position(20, 10), new Position(10, 10), lineSight, null, 2);
		Assertions.assertThat(miner.pickDiamonds()).isEqualTo(3);
	}

	@Test
	public void miner_drop_the_maximal_number_of_diamonds() throws Exception {
		Miner miner = createMinerStub(null, new Position(20, 10), new Position(10, 10), null, null, 3);
		Assertions.assertThat(miner.dropDiamonds()).isEqualTo(3);
	}

	private Miner createMinerStub(Pathfinder pathfinder, Position trolleyPosition, Position currentPosition, LineSight lineSight,
			List<Position> positionOpponents, int nbDiamonds) {
		Miner miner = new Miner(pathfinder, trolleyPosition, currentPosition, null, lineSight, positionOpponents, nbDiamonds) {
			@Override
			public MinerAction doAction() {
				return null;
			}
		};
		return miner;
	}

}
