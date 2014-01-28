package org.gold.miner.tchoutchou.mine;

import org.fest.assertions.api.Assertions;
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

	@Test
	public void testCanPass() throws Exception {
		Assertions.assertThat(new Case(new Position(15, 16), TypeTerrain.S.name()).canPass()).isFalse();
		Assertions.assertThat(new Case(new Position(15, 16), TypeTerrain.D.name()).canPass()).isTrue();
		Assertions.assertThat(new Case(new Position(15, 16), TypeTerrain.E.name()).canPass()).isTrue();
		Assertions.assertThat(new Case(new Position(15, 16), TypeTerrain.M.name()).canPass()).isTrue();
		Assertions.assertThat(new Case(new Position(15, 16), TypeTerrain.X.name()).canPass()).isFalse();
	}
}