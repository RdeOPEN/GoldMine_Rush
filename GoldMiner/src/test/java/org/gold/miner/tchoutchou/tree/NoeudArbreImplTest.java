package org.gold.miner.tchoutchou.tree;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mine.TypeTerrain;
import org.junit.Test;

public class NoeudArbreImplTest {

	@Test
	public void isNotCasePere_must_return_false_when_case_is_pere() throws Exception {
		NoeudArbreImpl pere = new NoeudArbreImpl(null, new Case(new Position(15, 16), TypeTerrain.S.name()));

		NoeudArbreImpl noeud = new NoeudArbreImpl(pere, new Case(new Position(15, 15), TypeTerrain.M.name()));

		Assertions.assertThat(noeud.isNotCasePere(pere.getCase())).isFalse();
	}

	@Test
	public void isNotCasePere_must_return_true_when_pere_is_null() throws Exception {
		Case notPere = new Case(new Position(15, 16), TypeTerrain.S.name());

		NoeudArbreImpl noeud = new NoeudArbreImpl(null, new Case(new Position(15, 15), TypeTerrain.M.name()));

		Assertions.assertThat(noeud.isNotCasePere(notPere)).isTrue();
	}

	@Test
	public void isNotCasePere_must_return_true_when_case_is_not_pere() throws Exception {
		Case notPere = new Case(new Position(16, 15), TypeTerrain.S.name());
		NoeudArbreImpl pere = new NoeudArbreImpl(null, new Case(new Position(15, 16), TypeTerrain.S.name()));

		NoeudArbreImpl noeud = new NoeudArbreImpl(pere, new Case(new Position(15, 15), TypeTerrain.M.name()));

		Assertions.assertThat(noeud.isNotCasePere(notPere)).isTrue();
	}

	@Test
	public void parcoursNoeuds_must_return_null_when_destination_was_not_found() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NoeudArbreImpl noeud = new NoeudArbreImpl(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		NoeudArbreImpl noeud2 = new NoeudArbreImpl(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		noeud.addNoeudNord(noeud2);
		
		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isNull();
	}

	@Test
	public void parcoursNoeuds_must_return_1_when_destination_case_was_found() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NoeudArbreImpl noeud = new NoeudArbreImpl(null, new Case(new Position(15, 16), TypeTerrain.M.name()));

		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(1);
	}

	@Test
	public void parcoursNoeuds_must_return_2_when_destination_case_was_found_caseNord() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NoeudArbreImpl noeud = new NoeudArbreImpl(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		NoeudArbreImpl noeudDest = new NoeudArbreImpl(null, new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNoeudNord(noeudDest);

		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void parcoursNoeuds_must_return_2_when_destination_case_was_found_caseEst() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NoeudArbreImpl noeud = new NoeudArbreImpl(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		NoeudArbreImpl noeudDest = new NoeudArbreImpl(null, new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNoeudEst(noeudDest);

		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void parcoursNoeuds_must_return_2_when_destination_case_was_found_caseSud() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NoeudArbreImpl noeud = new NoeudArbreImpl(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		NoeudArbreImpl noeudDest = new NoeudArbreImpl(null, new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNoeudSud(noeudDest);

		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void parcoursNoeuds_must_return_2_when_destination_case_was_found_caseOuest() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NoeudArbreImpl noeud = new NoeudArbreImpl(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		NoeudArbreImpl noeudDest = new NoeudArbreImpl(null, new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNoeudOuest(noeudDest);

		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

}
