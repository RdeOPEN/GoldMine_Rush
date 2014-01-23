package org.gold.miner.tchoutchou.mineur;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mineur.Miner;
import org.gold.miner.tchoutchou.mineur.MinerFactory;
import org.gold.miner.tchoutchou.mineur.archetypes.MinerArchetype;
import org.gold.miner.tchoutchou.mineur.archetypes.ProtoMiner;
import org.gold.miner.tchoutchou.mineur.archetypes.RandomMiner;
import org.junit.Test;


public class MinerFactoryTest {

	@Test
	public void createRandomPathfinderTest() throws Exception {
		Miner miner = MinerFactory.createMiner(MinerArchetype.RANDOM_MINER, null, null, null, null, null, null, 0);
		Assertions.assertThat(miner).isInstanceOf(RandomMiner.class);
	}
	
	@Test
	public void createProtoPathfinderTest() throws Exception {
		Miner miner = MinerFactory.createMiner(MinerArchetype.PROTOMINER, null, null, null, null, null, null, 0);
		Assertions.assertThat(miner).isInstanceOf(ProtoMiner.class);
	}
	
}
