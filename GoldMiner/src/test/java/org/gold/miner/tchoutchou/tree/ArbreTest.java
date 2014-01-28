package org.gold.miner.tchoutchou.tree;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mine.TypeTerrain;
import org.junit.Test;

public class ArbreTest {

	@Test
	public void test_chemin_le_plus_court_profondeur_1() throws Exception {

		// creation racine
		RacineArbre noeudRacine = new RacineArbre(new Case(new Position(15, 15), TypeTerrain.E.name()));
		Arbre arbre = new Arbre(noeudRacine);

		Case destination = new Case(new Position(14, 15), TypeTerrain.D.name());

		// ajout de la case EST
		NoeudArbreImpl case1 = new NoeudArbreImpl(noeudRacine, new Case(new Position(16, 15), TypeTerrain.M.name()));
		// ajout de la case SUD
		NoeudArbreImpl case2 = new NoeudArbreImpl(noeudRacine, new Case(new Position(15, 16), TypeTerrain.S.name()));
		// ajout de la case OUEST
		NoeudArbreImpl case3 = new NoeudArbreImpl(noeudRacine, destination);
		// ajout de la case OUEST
		NoeudArbreImpl case4 = new NoeudArbreImpl(noeudRacine, new Case(new Position(15, 14), TypeTerrain.E.name()));

		noeudRacine.addNoeudEst(case1);
		noeudRacine.addNoeudSud(case2);
		noeudRacine.addNoeudOuest(case3);
		noeudRacine.addNoeudNord(case4);

		Integer distance = arbre.getShortWay(destination);
		
		Assertions.assertThat(distance).isEqualTo(1);
	}

	@Test
	public void test_chemin_le_plus_court_profondeur_2() throws Exception {
		Case destination = new Case(new Position(16, 14), TypeTerrain.D.name());

		// creation racine
		RacineArbre noeudRacine = new RacineArbre(new Case(new Position(15, 15), TypeTerrain.E.name()));
		Arbre arbre = new Arbre(noeudRacine);

		// ajout de la case EST
		NoeudArbreImpl caseEst = new NoeudArbreImpl(noeudRacine, new Case(new Position(16, 15), TypeTerrain.M.name()));
		// ajout de la case SUD
		NoeudArbreImpl case2 = new NoeudArbreImpl(noeudRacine, new Case(new Position(15, 16), TypeTerrain.D.name()));
		// ajout de la case OUEST
		NoeudArbreImpl case3 = new NoeudArbreImpl(noeudRacine, new Case(new Position(14, 15), TypeTerrain.M.name()));
		// ajout de la case OUEST
		NoeudArbreImpl case4 = new NoeudArbreImpl(noeudRacine, new Case(new Position(15, 14), TypeTerrain.E.name()));

		noeudRacine.addNoeudEst(caseEst);
		noeudRacine.addNoeudSud(case2);
		noeudRacine.addNoeudOuest(case3);
		noeudRacine.addNoeudNord(case4);

		// ajout de la case EST
		NoeudArbreImpl caseEst1 = new NoeudArbreImpl(caseEst, new Case(new Position(17, 15), TypeTerrain.S.name()));
		// ajout de la case SUD
		NoeudArbreImpl caseEst2 = new NoeudArbreImpl(caseEst, new Case(new Position(16, 16), TypeTerrain.S.name()));
		// ajout de la case OUEST
		NoeudArbreImpl caseEst3 = new NoeudArbreImpl(caseEst, new Case(new Position(15, 15), TypeTerrain.S.name()));
		// ajout de la case NORD
		NoeudArbreImpl caseEst4 = new NoeudArbreImpl(caseEst, destination);

		caseEst.addNoeudEst(caseEst1);
		caseEst.addNoeudSud(caseEst2);
		caseEst.addNoeudOuest(caseEst3);
		caseEst.addNoeudNord(caseEst4);

		Integer distance = arbre.getShortWay(destination);

		// La profondeur de l'arbre est de 3
		Assertions.assertThat(distance).isEqualTo(2);
	}

	@Test
	public void test_chemin_le_plus_court_profondeur_3() throws Exception {
		Case destination = new Case(new Position(16, 13), TypeTerrain.M.name());

		// creation racine
		RacineArbre noeudRacine = new RacineArbre(new Case(new Position(15, 15), TypeTerrain.E.name()));
		Arbre arbre = new Arbre(noeudRacine);

		// positionX = 15 | positionY = 15
		int positionX = noeudRacine.getCase().getPosition().getPositionX();
		int positionY = noeudRacine.getCase().getPosition().getPositionY();

		// ajout de la case EST
		NoeudArbreImpl caseEst = new NoeudArbreImpl(noeudRacine, new Case(new Position(positionX + 1, positionY), TypeTerrain.M.name()));
		// ajout de la case SUD
		NoeudArbreImpl case2 = new NoeudArbreImpl(noeudRacine, new Case(new Position(positionX, positionY + 1), TypeTerrain.D.name()));
		// ajout de la case OUEST
		NoeudArbreImpl case3 = new NoeudArbreImpl(noeudRacine, new Case(new Position(positionX - 1, positionY), TypeTerrain.M.name()));
		// ajout de la case OUEST
		NoeudArbreImpl case4 = new NoeudArbreImpl(noeudRacine, new Case(new Position(positionX, positionY - 1), TypeTerrain.E.name()));

		noeudRacine.addNoeudEst(caseEst);
		noeudRacine.addNoeudSud(case2);
		noeudRacine.addNoeudOuest(case3);
		noeudRacine.addNoeudNord(case4);

		// positionX = 16 | positionY = 15
		positionX = caseEst.getCase().getPosition().getPositionX();
		positionY = caseEst.getCase().getPosition().getPositionY();

		// ajout de la case EST
		NoeudArbreImpl caseEst1 = new NoeudArbreImpl(caseEst, new Case(new Position(positionX + 1, positionY), TypeTerrain.M.name()));
		// ajout de la case SUD
		NoeudArbreImpl caseEst2 = new NoeudArbreImpl(caseEst, new Case(new Position(positionX, positionY + 1), TypeTerrain.S.name()));
		// ajout de la case OUEST
		NoeudArbreImpl caseEst3 = new NoeudArbreImpl(caseEst, new Case(new Position(positionX - 1, positionY), TypeTerrain.E.name()));
		// ajout de la case NORD
		NoeudArbreImpl caseEst4 = new NoeudArbreImpl(caseEst, new Case(new Position(positionX, positionY - 1), TypeTerrain.M.name()));

		caseEst.addNoeudEst(caseEst1);
		caseEst.addNoeudSud(caseEst2);
		caseEst.addNoeudOuest(caseEst3);
		caseEst.addNoeudNord(caseEst4);

		// positionX = 16 | positionY = 14
		positionX = caseEst4.getCase().getPosition().getPositionX();
		positionY = caseEst4.getCase().getPosition().getPositionY();

		// ajout de la case EST
		NoeudArbreImpl caseNord1 = new NoeudArbreImpl(caseEst4, new Case(new Position(positionX + 1, positionY), TypeTerrain.E.name()));
		// ajout de la case SUD
		NoeudArbreImpl caseNord2 = new NoeudArbreImpl(caseEst4, new Case(new Position(positionX, positionY + 1), TypeTerrain.S.name()));
		// ajout de la case OUEST
		NoeudArbreImpl caseNord3 = new NoeudArbreImpl(caseEst4, new Case(new Position(positionX - 1, positionY), TypeTerrain.E.name()));
		// ajout de la case NORD
		NoeudArbreImpl caseNord4 = new NoeudArbreImpl(caseEst4, destination);

		caseEst4.addNoeudEst(caseNord1);
		caseEst4.addNoeudSud(caseNord2);
		caseEst4.addNoeudOuest(caseNord3);
		caseEst4.addNoeudNord(caseNord4);

		Integer distance = arbre.getShortWay(destination);

		// La profondeur de l'arbre est de 3
		Assertions.assertThat(distance).isEqualTo(3);
	}

	@Test
	public void test_chemin_le_plus_court_profondeur_4() throws Exception {
		Case destination = new Case(new Position(16, 12), TypeTerrain.M.name());

		// creation racine
		RacineArbre noeudRacine = new RacineArbre(new Case(new Position(15, 15), TypeTerrain.E.name()));
		Arbre arbre = new Arbre(noeudRacine);

		// niveau 1

		// positionX = 15 | positionY = 15
		int positionX = noeudRacine.getCase().getPosition().getPositionX();
		int positionY = noeudRacine.getCase().getPosition().getPositionY();

		// ajout de la case EST (16,15)
		NoeudArbreImpl caseEst = new NoeudArbreImpl(noeudRacine, new Case(new Position(positionX + 1, positionY), TypeTerrain.M.name()));
		// ajout de la case SUD (15,16)
		NoeudArbreImpl case2 = new NoeudArbreImpl(noeudRacine, new Case(new Position(positionX, positionY + 1), TypeTerrain.D.name()));
		// ajout de la case OUEST (14,15)
		NoeudArbreImpl case3 = new NoeudArbreImpl(noeudRacine, new Case(new Position(positionX - 1, positionY), TypeTerrain.M.name()));
		// ajout de la case OUEST (15,14)
		NoeudArbreImpl case4 = new NoeudArbreImpl(noeudRacine, new Case(new Position(positionX, positionY - 1), TypeTerrain.E.name()));

		noeudRacine.addNoeudEst(caseEst);
		noeudRacine.addNoeudSud(case2);
		noeudRacine.addNoeudOuest(case3);
		noeudRacine.addNoeudNord(case4);

		// niveau 2

		// positionX = 16 | positionY = 15
		positionX = caseEst.getCase().getPosition().getPositionX();
		positionY = caseEst.getCase().getPosition().getPositionY();

		// ajout de la case EST (17,15)
		NoeudArbreImpl caseEst1 = new NoeudArbreImpl(caseEst, new Case(new Position(positionX + 1, positionY), TypeTerrain.M.name()));
		// ajout de la case SUD (16,16)
		NoeudArbreImpl caseEst2 = new NoeudArbreImpl(caseEst, new Case(new Position(positionX, positionY + 1), TypeTerrain.S.name()));
		// ajout de la case OUEST (15,15)
		NoeudArbreImpl caseEst3 = new NoeudArbreImpl(caseEst, new Case(new Position(positionX - 1, positionY), TypeTerrain.E.name()));
		// ajout de la case NORD (16,14)
		NoeudArbreImpl caseEst4 = new NoeudArbreImpl(caseEst, new Case(new Position(positionX, positionY - 1), TypeTerrain.M.name()));

		caseEst.addNoeudEst(caseEst1);
		caseEst.addNoeudSud(caseEst2);
		caseEst.addNoeudOuest(caseEst3);
		caseEst.addNoeudNord(caseEst4);

		// niveau 3

		// positionX = 16 | positionY = 14
		positionX = caseEst4.getCase().getPosition().getPositionX();
		positionY = caseEst4.getCase().getPosition().getPositionY();

		// ajout de la case EST (17,14)
		NoeudArbreImpl caseNord1 = new NoeudArbreImpl(caseEst4, new Case(new Position(positionX + 1, positionY), TypeTerrain.E.name()));
		// ajout de la case SUD (16,15)
		NoeudArbreImpl caseNord2 = new NoeudArbreImpl(caseEst4, new Case(new Position(positionX, positionY + 1), TypeTerrain.M.name()));
		// ajout de la case OUEST (15,14)
		NoeudArbreImpl caseNord3 = new NoeudArbreImpl(caseEst4, new Case(new Position(positionX - 1, positionY), TypeTerrain.E.name()));
		// ajout de la case NORD (16,13)
		NoeudArbreImpl caseNord4 = new NoeudArbreImpl(caseEst4, new Case(new Position(positionX, positionY - 1), TypeTerrain.M.name()));

		caseEst4.addNoeudEst(caseNord1);
		caseEst4.addNoeudSud(caseNord2);
		caseEst4.addNoeudOuest(caseNord3);
		caseEst4.addNoeudNord(caseNord4);

		// niveau 4

		// positionX = 16 | positionY = 13
		positionX = caseNord4.getCase().getPosition().getPositionX();
		positionY = caseNord4.getCase().getPosition().getPositionY();

		// ajout de la case EST (17,13)
		NoeudArbreImpl caseSud1 = new NoeudArbreImpl(caseNord4, new Case(new Position(positionX + 1, positionY), TypeTerrain.E.name()));
		// ajout de la case SUD (16,14)
		NoeudArbreImpl caseSud2 = new NoeudArbreImpl(caseNord4, new Case(new Position(positionX, positionY + 1), TypeTerrain.S.name()));
		// ajout de la case OUEST (15,13)
		NoeudArbreImpl caseSud3 = new NoeudArbreImpl(caseNord4, new Case(new Position(positionX - 1, positionY), TypeTerrain.E.name()));
		// ajout de la case NORD (16,12)
		NoeudArbreImpl caseSud4 = new NoeudArbreImpl(caseNord4, new Case(new Position(positionX, positionY - 1), TypeTerrain.M.name()));
		// Noeud caseSud4 = new Noeud(caseNord2, destination);

		caseNord4.addNoeudEst(caseSud1);
		caseNord4.addNoeudSud(caseSud2);
		caseNord4.addNoeudOuest(caseSud3);
		caseNord4.addNoeudNord(caseSud4);

		Integer distance = arbre.getShortWay(destination);

		// La profondeur de l'arbre est de 4
		Assertions.assertThat(distance).isEqualTo(4);
	}

}
