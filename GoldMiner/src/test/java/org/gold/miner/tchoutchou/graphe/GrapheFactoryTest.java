package org.gold.miner.tchoutchou.graphe;

import java.util.Map;
import java.util.Set;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.junit.Test;

public class GrapheFactoryTest {

	// ++ 08 09 10 11 12
	// 08 S--7--8--S--M
	// 09 S--M--S--S--M
	// 10 S--M--X--M--M
	// 11 S--S--M--M--M
	// 12 S--M--M--S--M

	String[] env = new String[] { "S 7 8 S M", "S M S S M", "S M X M M", "S S M M M", "S M M S M" };
	private static final String DELIMITER = "\\s";

	@Test
	public void constructGraphe() throws Exception {
		Position startPosition = new Position(10, 10);
		LineSight ligneSight = new LineSight(env, startPosition, DELIMITER);
		Mine mine = new Mine("40 40 50", DELIMITER);
		mine.update(ligneSight);

		Map<Position, Case> casesInMap = mine.getCasesInMap();
		
		Set<Case> casesInSet = mine.getCasesInCollection();
		
		RacineGraphe racineGraphe = GrapheFactory.constructGraphe(casesInSet);

		Assertions.assertThat(racineGraphe).isNotNull();
	}

}
