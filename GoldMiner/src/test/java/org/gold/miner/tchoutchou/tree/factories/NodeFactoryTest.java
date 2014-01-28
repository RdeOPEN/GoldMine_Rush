package org.gold.miner.tchoutchou.tree.factories;

import java.util.Map;
import java.util.Set;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.tree.Noeud;
import org.gold.miner.tchoutchou.tree.Racine;
import org.gold.miner.tchoutchou.tree.ResultatRecherche;
import org.junit.Test;

public class NodeFactoryTest {

	// ++ 08 09 10 11 12
	// 08 S--7--8--S--M
	// 09 S--M--S--S--M
	// 10 S--M--X--M--M
	// 11 S--S--M--M--M
	// 12 S--M--M--S--M

	String[] env = new String[] { "S 7 8 S M", "S M S S M", "S M X M M", "S S M M M", "S M M S M" };
	private static final String DELIMITER = "\\s";

	@Test
	public void constructNodesRecursively() throws Exception {
		Position startPosition = new Position(10, 10);
		LineSight ligneSight = new LineSight(env, startPosition, DELIMITER);
		Mine mine = new Mine("40 40 50", DELIMITER);
		mine.update(ligneSight);

		Map<Position, Case> casesInMap = mine.getCasesInMap();

		Racine racine = new Racine(casesInMap.get(startPosition));
		Set<Noeud> nodes = NodeFactory.constructNodes(casesInMap, racine);

		Assertions.assertThat(nodes).isNotNull();
		Assertions.assertThat(nodes).isNotEmpty();

		Case destination = casesInMap.get(new Position(10, 11));
		ResultatRecherche resultat = extracted(racine, destination);
		Assertions.assertThat(resultat.getDistance()).isEqualTo(1);

		destination = casesInMap.get(new Position(10, 12));
		resultat = extracted(racine, destination);
		Assertions.assertThat(resultat.getDistance()).isEqualTo(2);

		destination = casesInMap.get(new Position(9, 8));
		resultat = extracted(racine, destination);
		Assertions.assertThat(resultat.getDistance()).isEqualTo(3);
	}

	private ResultatRecherche extracted(Racine racine, Case destination) {
		System.out.println("Recherche destination: " + destination);
		ResultatRecherche resultat = new ResultatRecherche();
		racine.calculateShortWayToDestination(resultat, destination);
		System.out.println("Resultat " + destination.getPosition() + " : " + resultat);
		return resultat;
	}

}
