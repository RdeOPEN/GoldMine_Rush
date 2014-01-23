package org.gold.miner.tchoutchou.mineur.archetypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.mineur.archetypes.RandomMiner;
import org.junit.Test;

public class RandomMinerTest {

	private final long seed = 15151561615055L;

	@Test
	public void testDoAction() throws Exception {
		List<MinerAction> mineurActionsExpected = Arrays.asList(MinerAction.NORTH, MinerAction.WEST, MinerAction.SOUTH, MinerAction.WEST, MinerAction.PICK,
				MinerAction.DROP, MinerAction.EAST, MinerAction.SHOOT, MinerAction.PICK, MinerAction.NORTH);

		Random random = new Random(seed);
		RandomMiner mineur = new RandomMiner(random);

		List<MinerAction> mineurActions = new ArrayList<MinerAction>();
		for (int i = 0; i < 10; i++) {
			mineurActions.add(mineur.doAction());
		}

		Assertions.assertThat(mineurActions).containsExactly(mineurActionsExpected.toArray(new MinerAction[] {}));
	}

}
