package org.gold.miner.tchoutchou.tree;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mine.TypeTerrain;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.junit.Test;

public class RacineTest {

	@Test
	public void test_chemin_le_plus_court_profondeur_1() throws Exception {
		Case destination = new Case(new Position(14, 15), TypeTerrain.D.name());

		// creation racine
		Racine noeudRacine = new Racine(new Case(new Position(15, 15), TypeTerrain.E.name()));

		// ajout de la case EST
		Noeud case1 = new Noeud(noeudRacine, new Case(new Position(16, 15), TypeTerrain.M.name()));
		// ajout de la case SUD
		Noeud case2 = new Noeud(noeudRacine, new Case(new Position(15, 16), TypeTerrain.S.name()));
		// ajout de la case OUEST
		Noeud case3 = new Noeud(noeudRacine, destination);
		// ajout de la case OUEST
		Noeud case4 = new Noeud(noeudRacine, new Case(new Position(15, 14), TypeTerrain.E.name()));

		noeudRacine.addNoeudEst(case1);
		noeudRacine.addNoeudSud(case2);
		noeudRacine.addNoeudOuest(case3);
		noeudRacine.addNoeudNord(case4);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer distance = noeudRacine.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(distance).isEqualTo(1);
		Assertions.assertThat(resultat.getSelectedCase()).isEqualTo(case3.getCase());
		Assertions.assertThat(resultat.getMinerAction()).isEqualTo(MinerAction.WEST);
	}

}
