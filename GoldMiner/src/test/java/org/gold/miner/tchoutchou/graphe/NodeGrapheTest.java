package org.gold.miner.tchoutchou.graphe;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mine.TypeTerrain;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.tree.ResultatRechercheChemin;
import org.junit.Test;

public class NodeGrapheTest {

	@Test
	public void test_chemin_le_plus_court_profondeur_1() throws Exception {
		Case destination = new Case(new Position(14, 15), "5");

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

		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer distance = noeudRacine.calculateShortWayToDestination(resultat, null, destination);

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

		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, null, destination);

		Assertions.assertThat(result).isNull();
	}

	@Test
	public void calculateShortWayToDestination_must_return_1_when_destination_case_was_found() throws Exception {
		Case destination = new Case(new Position(5, 5), TypeTerrain.M.name());
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(5, 5), TypeTerrain.M.name()));

		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, null, destination);

		Assertions.assertThat(result).isEqualTo(1);
		Assertions.assertThat(resultat.getMinerAction()).isEqualTo(null);
	}

	@Test
	public void calculateShortWayToDestination_must_return_2_when_destination_case_was_found_inNorth() throws Exception {
		Case destination = new Case(new Position(15, 14), TypeTerrain.M.name());
		
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.M.name()));
		NodeGraphe noeudEast = new NodeGraphe(new Case(new Position(16, 15), TypeTerrain.M.name()));
		NodeGraphe noeudDest = new NodeGraphe(destination);
		noeud.addNodeNorth(noeudDest);
		noeud.addNodeEast(noeudEast);
		
		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, null, destination);

		Assertions.assertThat(result).isEqualTo(1);
		Assertions.assertThat(resultat.getMinerAction()).isEqualTo(MinerAction.NORTH);
	}

	@Test
	public void calculateShortWayToDestination_must_return_2_when_destination_case_was_found_inEast() throws Exception {
		Case destination = new Case(new Position(16, 16), TypeTerrain.M.name());
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.M.name()));
		NodeGraphe noeudDest = new NodeGraphe(destination);
		noeud.addNodeEast(noeudDest);

		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, null, destination);

		Assertions.assertThat(result).isEqualTo(1);
		Assertions.assertThat(resultat.getMinerAction()).isEqualTo(MinerAction.EAST);
	}

	@Test
	public void calculateShortWayToDestination_must_return_2_when_destination_case_was_found_inSouth() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.M.name()));
		NodeGraphe noeudEast = new NodeGraphe(new Case(new Position(16, 15), TypeTerrain.M.name()));
		NodeGraphe noeudWest = new NodeGraphe(new Case(new Position(14, 15), TypeTerrain.M.name()));
		NodeGraphe noeudSouth = new NodeGraphe(destination);
		noeud.addNodeSouth(noeudSouth);
		noeud.addNodeEast(noeudEast);
		noeud.addNodeWest(noeudWest);
		
		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, null, destination);

		Assertions.assertThat(result).isEqualTo(1);
		Assertions.assertThat(resultat.getMinerAction()).isEqualTo(MinerAction.SOUTH);

	}

	@Test
	public void calculateShortWayToDestination_must_return_2_when_destination_case_was_found_inWest() throws Exception {
		Case destination = new Case(new Position(15, 16), TypeTerrain.M.name());
		NodeGraphe noeud = new NodeGraphe(new Case(new Position(15, 15), TypeTerrain.M.name()));
		NodeGraphe noeudDest = new NodeGraphe(destination);
		noeud.addNodeWest(noeudDest);

		ResultatRechercheChemin resultat = new ResultatRechercheChemin();
		Integer result = noeud.calculateShortWayToDestination(resultat, null, destination);

		Assertions.assertThat(result).isEqualTo(1);
		Assertions.assertThat(resultat.getMinerAction()).isEqualTo(MinerAction.WEST);

	}

	@Test
	public void getMinDistance_must_return_null_when_distance_and_result_are_null() throws Exception {
		Integer minDistance = NodeGraphe.getMinDistance(null, null);
		Assertions.assertThat(minDistance).isNull();
	}

	@Test
	public void getMinDistance_must_return_1_when_distance_is_null_and_result_is_equal_to_1() throws Exception {
		Integer minDistance = NodeGraphe.getMinDistance(null, 1);
		Assertions.assertThat(minDistance).isEqualTo(new Integer(1));
	}

	@Test
	public void getMinDistance_must_return_1_when_distance_is_equal_to_1_and_result_is_null() throws Exception {
		Integer minDistance = NodeGraphe.getMinDistance(1, null);
		Assertions.assertThat(minDistance).isEqualTo(new Integer(1));
	}

	@Test
	public void getMinDistance_must_return_min_distance_when_distance_and_result_is_not_null() throws Exception {
		Integer minDistance = NodeGraphe.getMinDistance(4, 2);
		Assertions.assertThat(minDistance).isEqualTo(new Integer(2));
		minDistance = NodeGraphe.getMinDistance(2, 4);
		Assertions.assertThat(minDistance).isEqualTo(new Integer(2));
	}

}
