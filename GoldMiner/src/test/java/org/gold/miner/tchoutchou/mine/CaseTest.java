package org.gold.miner.tchoutchou.mine;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mineur.Miner;
import org.junit.Test;

public class CaseTest {

	@Test
	public void instanciate_case_diamond() throws Exception {
		Case caseMine = new Case(new Position(10, 10), "10");

		Assertions.assertThat(caseMine.getPosition()).isEqualTo(new Position(10, 10));
		Assertions.assertThat(caseMine.getType()).isEqualTo(TypeTerrain.D);
		Assertions.assertThat(caseMine.getDiamonds()).isEqualTo(10);
	}

	@Test
	public void instanciate_case_diamond2() throws Exception {
		Case caseMine = new Case(new Position(10, 10), TypeTerrain.D, 10);

		Assertions.assertThat(caseMine.getPosition()).isEqualTo(new Position(10, 10));
		Assertions.assertThat(caseMine.getType()).isEqualTo(TypeTerrain.D);
		Assertions.assertThat(caseMine.getDiamonds()).isEqualTo(10);
	}

	@Test
	public void instanciate_case_diamond3() throws Exception {
		Case caseMine = new Case(new Position(10, 10), TypeTerrain.M, 10);

		Assertions.assertThat(caseMine.getPosition()).isEqualTo(new Position(10, 10));
		Assertions.assertThat(caseMine.getType()).isEqualTo(TypeTerrain.M);
		Assertions.assertThat(caseMine.getDiamonds()).isEqualTo(0);
	}

	@Test
	public void instanciate_case_diamond4() throws Exception {
		Case caseMine = new Case(new Position(10, 10), TypeTerrain.S, 0);

		Assertions.assertThat(caseMine.getPosition()).isEqualTo(new Position(10, 10));
		Assertions.assertThat(caseMine.getType()).isEqualTo(TypeTerrain.S);
		Assertions.assertThat(caseMine.getDiamonds()).isEqualTo(0);
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

	@Test
	public void testCanPass_trolleyPosition() throws Exception {
		Miner.trolleyPosition = new Position(15, 15);
		Assertions.assertThat(new Case(new Position(15, 15), TypeTerrain.X.name()).canPass()).isTrue();
	}
}
