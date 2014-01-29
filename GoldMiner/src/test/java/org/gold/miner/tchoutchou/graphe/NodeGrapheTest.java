package org.gold.miner.tchoutchou.graphe;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mine.TypeTerrain;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.tree.ResultatRecherche;
import org.junit.Test;

public class NodeGrapheTest {

	@Test
	public void test_chemin_le_plus_court_profondeur_1() throws Exception {
		Case destination = new Case(new Position(14, 15), TypeTerrain.D.name());

		// creation racine
		NodeGraphe noeudRacine = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.E.name()));

		// ajout de la case EST
		NodeGraphe case1 = new NodeGraphe(new Case(new Position(16, 15), TypeTerrain.M.name()));
		// ajout de la case SUD
		NodeGraphe case2 = new NodeGraphe(new Case(new Position(15, 16), TypeTerrain.S.name()));
		// ajout de la case OUEST
		NodeGraphe case3 = new NodeGraphe(destination);
		// ajout de la case OUEST
		NodeGraphe case4 = new NodeGraphe(new Case(new Position(15, 14), TypeTerrain.E.name()));

		noeudRacine.addNodeEast(case1);
		noeudRacine.addNodeSouth(case2);
		noeudRacine.addNodeWest(case3);
		noeudRacine.addNodeNorth(case4);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer distance = noeudRacine.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(distance).isEqualTo(1);
		Assertions.assertThat(resultat.getSelectedCase()).isEqualTo(case3.getCase());
		Assertions.assertThat(resultat.getMinerAction()).isEqualTo(MinerAction.WEST);
	}

	@Test
	public void calculateShortWayToDestination_must_return_null_distance_when_destination_was_not_found() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.M.name()));
		NodeGraphe noeud2 = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.M.name()));
		noeud.addNodeNorth(noeud2);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isNull();
	}

	@Test
	public void calculateShortWayToDestination_must_return_1_when_destination_case_was_found() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(15, 16), TypeTerrain.M.name()));

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(1);
	}

	@Test
	public void calculateShortWayToDestination_must_return_2_when_destination_case_was_found_inNorth() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.M.name()));
		NodeGraphe noeudDest = new NodeGraphe(new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNodeNorth(noeudDest);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void calculateShortWayToDestination_must_return_2_when_destination_case_was_found_inEast() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.M.name()));
		NodeGraphe noeudDest = new NodeGraphe(new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNodeEast(noeudDest);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void calculateShortWayToDestination_must_return_2_when_destination_case_was_found_inSouth() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.M.name()));
		NodeGraphe noeudDest = new NodeGraphe(new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNodeSouth(noeudDest);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void calculateShortWayToDestination_must_return_2_when_destination_case_was_found_inWest() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.M.name()));
		NodeGraphe noeudDest = new NodeGraphe(new Case(new Position(15, 16), TypeTerrain.M.name()));
		noeud.addNodeWest(noeudDest);

		ResultatRecherche resultat = new ResultatRecherche();
		Integer result = noeud.calculateShortWayToDestination(resultat, destination);

		Assertions.assertThat(result).isEqualTo(2);
	}
}