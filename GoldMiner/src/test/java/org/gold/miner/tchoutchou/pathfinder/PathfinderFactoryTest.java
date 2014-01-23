package org.gold.miner.tchoutchou.pathfinder;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.pathfinder.Pathfinder;
import org.gold.miner.tchoutchou.pathfinder.PathfinderArchetype;
import org.gold.miner.tchoutchou.pathfinder.PathfinderFactory;
import org.gold.miner.tchoutchou.pathfinder.ProtoPathfinder;
import org.gold.miner.tchoutchou.pathfinder.RandomPathfinder;
import org.junit.Test;


public class PathfinderFactoryTest {

	@Test
	public void createRandomPathfinderTest() throws Exception {
		Pathfinder pathfinder = PathfinderFactory.createPathfinder(PathfinderArchetype.RANDOMPATHFINDER, null);
		Assertions.assertThat(pathfinder).isInstanceOf(RandomPathfinder.class);
	}
	
	@Test
	public void createProtoPathfinderTest() throws Exception {
		Pathfinder pathfinder = PathfinderFactory.createPathfinder(PathfinderArchetype.PROTOPATHFINDER, null);
		Assertions.assertThat(pathfinder).isInstanceOf(ProtoPathfinder.class);
	}
	
}
