package org.gold.miner.tchoutchou.tree;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.junit.Test;

public class NoeudTest {

	@Test
	public void isNotCasePere_must_return_false_when_case_is_pere() throws Exception {
		Noeud noeud = new Noeud(new Case(new Position(15, 15), "M"));
		Case pere = new Case(new Position(15, 16), "S");
		noeud.setPere(pere);

		Assertions.assertThat(noeud.isNotCasePere(pere)).isFalse();
	}

	@Test
	public void isNotCasePere_must_return_true_when_pere_is_null() throws Exception {
		Noeud noeud = new Noeud(new Case(new Position(15, 15), "M"));
		Case notPere = new Case(new Position(15, 16), "S");
		noeud.setPere(null);

		Assertions.assertThat(noeud.isNotCasePere(notPere)).isTrue();
	}

	@Test
	public void isNotCasePere_must_return_true_when_case_is_not_pere() throws Exception {
		Noeud noeud = new Noeud(new Case(new Position(15, 15), "M"));
		Case pere = new Case(new Position(15, 16), "S");
		noeud.setPere(pere);

		Case notPere = new Case(new Position(16, 15), "S");

		Assertions.assertThat(noeud.isNotCasePere(notPere)).isTrue();
	}

	@Test
	public void testNoeuds() throws Exception {
		// creation racine
		Noeud noeudRacine = new Noeud(new Case(new Position(15, 15), "M"));

		Case destination = new Case(new Position(14, 15), "4");

		// ajout de la case EST
		Noeud case1 = new Noeud(new Case(new Position(16, 15), "M"));
		// ajout de la case SUD
		Noeud case2 = new Noeud(new Case(new Position(15, 16), "S"));
		// ajout de la case OUEST
		Noeud case3 = new Noeud(destination);
		// ajout de la case OUEST
		Noeud case4 = new Noeud(new Case(new Position(15, 14), "E"));

		noeudRacine.addCaseEst(case1);
		noeudRacine.addCaseSud(case2);
		noeudRacine.addCaseOuest(case3);
		noeudRacine.addCaseNord(case4);

		Integer distance = noeudRacine.parcoursNoeuds(destination);

		Assertions.assertThat(distance).isEqualTo(1);
	}

	@Test
	public void testNoeuds2() throws Exception {
		Case destination = new Case(new Position(16, 14), "S");
		
		// creation racine
		Noeud noeudRacine = new Noeud(new Case(new Position(15, 15), "M"));

		// ajout de la case EST
		Noeud case1 = new Noeud(new Case(new Position(16, 15), "M"));
		// ajout de la case SUD
		Noeud case2 = new Noeud(new Case(new Position(15, 16), "4"));
		// ajout de la case OUEST
		Noeud case3 = new Noeud(new Case(new Position(14, 15), "M"));
		// ajout de la case OUEST
		Noeud case4 = new Noeud(new Case(new Position(15, 14), "E"));

		noeudRacine.addCaseEst(case1);
		noeudRacine.addCaseSud(case2);
		noeudRacine.addCaseOuest(case3);
		noeudRacine.addCaseNord(case4);

		// ajout de la case EST
		Noeud caseEst1 = new Noeud(new Case(new Position(17, 15), "S"));
		// ajout de la case SUD
		Noeud caseEst2 = new Noeud(new Case(new Position(16, 14), "S"));
		// ajout de la case OUEST
		Noeud caseEst3 = new Noeud(new Case(new Position(15, 15), "S"));
		// ajout de la case OUEST
		Noeud caseEst4 = new Noeud(destination);

		case1.addCaseEst(caseEst1);
		case1.addCaseSud(caseEst2);
		case1.addCaseOuest(caseEst3);
		case1.addCaseNord(caseEst4);

		Integer distance = noeudRacine.parcoursNoeuds(destination);

		Assertions.assertThat(distance).isEqualTo(2);
	}

}
