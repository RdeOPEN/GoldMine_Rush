package org.gold.miner.tchoutchou.tree;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.junit.Test;

public class ResultatRechercheCheminTest {

	@Test
	public void compareTo_must_return_sort_ResultatRechercheChemin_list_correctly() throws Exception {
		ResultatRechercheChemin resultat = new ResultatRechercheChemin(new Case(new Position(1, 1), "M"), MinerAction.NORTH, 5);

		Assertions.assertThat(resultat.isCompleted()).isTrue();
	}

	@Test
	public void isCompleted_must_return_true_when_all_elements_are_not_null() throws Exception {
		ResultatRechercheChemin resultat = new ResultatRechercheChemin(new Case(new Position(1, 1), "M"), MinerAction.NORTH, 5);
		resultat.setDestinationCase(new Case(new Position(1, 1), "M"));

		Assertions.assertThat(resultat.isCompleted()).isTrue();
	}

	@Test
	public void isCompleted_must_return_false_when_selectedCase_is_null() throws Exception {
		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		resultat.setDestinationCase(new Case(new Position(1, 1), "M"));
		resultat.setDistance(5);
		resultat.setSelectedCase(null);
		resultat.setMinerAction(MinerAction.NORTH);

		Assertions.assertThat(resultat.isCompleted()).isFalse();
	}

	@Test
	public void isCompleted_must_return_false_when_destinationCase_is_null() throws Exception {
		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		resultat.setDestinationCase(null);
		resultat.setDistance(5);
		resultat.setSelectedCase(new Case(new Position(1, 1), "M"));
		resultat.setMinerAction(MinerAction.NORTH);

		Assertions.assertThat(resultat.isCompleted()).isFalse();
	}

	@Test
	public void isCompleted_must_return_false_when_distance_is_null() throws Exception {
		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		resultat.setDestinationCase(new Case(new Position(1, 1), "M"));
		resultat.setDistance(null);
		resultat.setSelectedCase(new Case(new Position(1, 1), "M"));
		resultat.setMinerAction(MinerAction.NORTH);

		Assertions.assertThat(resultat.isCompleted()).isFalse();
	}

	@Test
	public void isCompleted_must_return_false_when_minerAction_is_null() throws Exception {
		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		resultat.setDestinationCase(new Case(new Position(1, 1), "M"));
		resultat.setDistance(5);
		resultat.setSelectedCase(new Case(new Position(1, 1), "M"));
		resultat.setMinerAction(null);

		Assertions.assertThat(resultat.isCompleted()).isFalse();
	}

}
