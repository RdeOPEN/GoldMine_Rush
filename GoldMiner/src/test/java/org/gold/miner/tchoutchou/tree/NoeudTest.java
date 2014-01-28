package org.gold.miner.tchoutchou.tree;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mine.TypeTerrain;
import org.junit.Test;

public class NoeudTest {

	@Test
	public void isNotCasePere_must_return_false_when_case_is_pere() throws Exception {
		Noeud pere = new Noeud(null, new Case(new Position(15, 16), TypeTerrain.S.name()));

		Noeud noeud = new Noeud(pere, new Case(new Position(15, 15), TypeTerrain.M.name()));

		Assertions.assertThat(noeud.isNotCasePere(pere.getCase())).isFalse();
	}

	@Test
	public void isNotCasePere_must_return_true_when_pere_is_null() throws Exception {
		Case notPere = new Case(new Position(15, 16), TypeTerrain.S.name());

		Noeud noeud = new Noeud(null, new Case(new Position(15, 15), TypeTerrain.M.name()));

		Assertions.assertThat(noeud.isNotCasePere(notPere)).isTrue();
	}

	@Test
	public void isNotCasePere_must_return_true_when_case_is_not_pere() throws Exception {
		Case notPere = new Case(new Position(16, 15), TypeTerrain.S.name());
		Noeud pere = new Noeud(null, new Case(new Position(15, 16), TypeTerrain.S.name()));

		Noeud noeud = new Noeud(pere, new Case(new Position(15, 15), TypeTerrain.M.name()));

		Assertions.assertThat(noeud.isNotCasePere(notPere)).isTrue();
	}

	@Test
	public void parcoursNoeuds_must_return_null_when_destination_was_not_found() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		Noeud noeud = new Noeud(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		Noeud noeud2 = new Noeud(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		noeud.addNoeudNord(noeud2);
		
		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isNull();
	}

	@Test
	public void parcoursNoeuds_must_return_1_when_destination_case_was_found() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		Noeud noeud = new Noeud(null, new Case(new Position(15, 16), TypeTerrain.M.name()));

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(1);
	}

	@Test
	public void parcoursNoeuds_must_return_2_when_destination_case_was_found_caseNord() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		Noeud noeud = new Noeud(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		Noeud noeudDest = new Noeud(null, new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNoeudNord(noeudDest);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void parcoursNoeuds_must_return_2_when_destination_case_was_found_caseEst() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		Noeud noeud = new Noeud(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		Noeud noeudDest = new Noeud(null, new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNoeudEst(noeudDest);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void parcoursNoeuds_must_return_2_when_destination_case_was_found_caseSud() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		Noeud noeud = new Noeud(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		Noeud noeudDest = new Noeud(null, new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNoeudSud(noeudDest);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void parcoursNoeuds_must_return_2_when_destination_case_was_found_caseOuest() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		Noeud noeud = new Noeud(null, new Case(new Position(15, 15), TypeTerrain.M.name()));
		Noeud noeudDest = new Noeud(null, new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNoeudOuest(noeudDest);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

}
