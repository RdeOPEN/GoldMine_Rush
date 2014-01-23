package org.gold.miner.tchoutchou.mine;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.TypeTerrain;
import org.junit.Test;


public class CaseTest {

	@Test
	public void instanciate_case_diamond() throws Exception {
		Case caseMine = new Case(null, "10");
		
		Assertions.assertThat(caseMine.getPosition()).isEqualTo(null);
		Assertions.assertThat(caseMine.getType()).isEqualTo(TypeTerrain.D);
		Assertions.assertThat(caseMine.getDiamonds()).isEqualTo(10);
	}
	
	@Test
	public void instanciate_case_mud() throws Exception {
		Case caseMine = new Case(null, "M");
		
		Assertions.assertThat(caseMine.getPosition()).isEqualTo(null);
		Assertions.assertThat(caseMine.getType()).isEqualTo(TypeTerrain.M);
		Assertions.assertThat(caseMine.getDiamonds()).isEqualTo(0);
	}
	
}
