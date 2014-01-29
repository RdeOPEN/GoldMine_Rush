package org.gold.miner.tchoutchou.graphe;

import java.util.Map;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mine.TypeTerrain;
import org.junit.Before;
import org.junit.Test;

public class GrapheFactoryTest {

	// ++ 08 09 10 11 12
	// 08 S--7--8--S--M
	// 09 S--M--S--S--M
	// 10 S--M--X--M--M
	// 11 S--S--M--M--M
	// 12 S--M--M--S--M

	private Mine mine;
	String[] env = new String[] { "S 7 8 S M", "S M S S M", "S M X M M", "S S M M M", "S M M S M" };
	private static final String DELIMITER = "\\s";

	@Before
	public void setUp() {
		Position startPosition = new Position(10, 10);
		LineSight ligneSight = new LineSight(env, startPosition, DELIMITER);
		mine = new Mine("40 40 50", DELIMITER);
		mine.update(ligneSight);
	}

	@Test
	public void constructGraphe() throws Exception {
		NodeGraphe node88 = new NodeGraphe(new Case(new Position(8, 8), TypeTerrain.S, 0));
		NodeGraphe node89 = new NodeGraphe(new Case(new Position(9, 8), TypeTerrain.D, 7));
		NodeGraphe node1010 = new NodeGraphe(new Case(new Position(10, 10), TypeTerrain.X, 0));
		NodeGraphe node1212 = new NodeGraphe(new Case(new Position(12, 12), TypeTerrain.M, 0));

		Map<Position, Case> casesInMap = mine.getCasesInMap();
		Map<Position, NodeGraphe> nodes = GrapheFactory.constructGraphe(casesInMap);

		Assertions.assertThat(nodes).isNotNull();
		Assertions.assertThat(nodes.size()).isEqualTo(25);
		Assertions.assertThat(nodes.get(new Position(8, 8)).getCase()).isEqualTo(node88.getCase());
		Assertions.assertThat(nodes.get(new Position(9, 8)).getCase()).isEqualTo(node89.getCase());
		Assertions.assertThat(nodes.get(new Position(10, 10)).getCase()).isEqualTo(node1010.getCase());
		Assertions.assertThat(nodes.get(new Position(12, 12)).getCase()).isEqualTo(node1212.getCase());
	}

}
