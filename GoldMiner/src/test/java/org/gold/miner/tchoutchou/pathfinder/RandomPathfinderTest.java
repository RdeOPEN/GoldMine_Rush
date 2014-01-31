package org.gold.miner.tchoutchou.pathfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.pathfinder.RandomPathfinder;
import org.junit.Test;

public class RandomPathfinderTest {

	private final long seed = 15151561615055L;

	@Test
				public void testGetMinerActionToMoveTo() throws Exception {
					List<MinerAction> mineurActionsExpected = Arrays.asList(MinerAction.SOUTH, MinerAction.NORTH, MinerAction.SOUTH, MinerAction.NORTH, MinerAction.SOUTH,
							MinerAction.WEST, MinerAction.EAST, MinerAction.NORTH, MinerAction.SOUTH, MinerAction.WEST);
			
					Random random = new Random(seed);
					RandomPathfinder pathFinder = new RandomPathfinder(random);
			
					List<MinerAction> mineurActions = new ArrayList<MinerAction>();
					for (int i = 0; i < 10; i++) {
						mineurActions.add(pathFinder.getMinerActionToMoveTo(null, null));
					}
			
					Assertions.assertThat(mineurActions).containsExactly(mineurActionsExpected.toArray(new MinerAction[] {}));
				}

}
