package org.gold.miner.tchoutchou.mine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Position;
import org.junit.Before;
import org.junit.Test;

public class LineSightTest {

	private static final String DELIMITER = "\\s";
	String[] env = new String[] { "S 7 8 S M", "S M S S M", "S M X M M", "S S M M M", "S M M S M" };
	private List<Case> environmentTemoin;

	@Before
	public void setUp() {
		environmentTemoin = Arrays.asList(new Case(new Position(8, 8), "S"), new Case(new Position(9, 8), "7"), new Case(new Position(10, 8), "8"), new Case(
				new Position(11, 8), "S"), new Case(new Position(12, 8), "M"), new Case(new Position(8, 9), "S"), new Case(new Position(9, 9), "M"), new Case(
				new Position(10, 9), "S"), new Case(new Position(11, 9), "S"), new Case(new Position(12, 9), "M"), new Case(new Position(8, 10), "S"),
				new Case(new Position(9, 10), "M"), new Case(new Position(10, 10), "X"), new Case(new Position(11, 10), "M"), new Case(new Position(12, 10),
						"M"), new Case(new Position(8, 11), "S"), new Case(new Position(9, 11), "S"), new Case(new Position(10, 11), "M"), new Case(
						new Position(11, 11), "M"), new Case(new Position(12, 11), "M"), new Case(new Position(8, 12), "S"),
				new Case(new Position(9, 12), "M"), new Case(new Position(10, 12), "M"), new Case(new Position(11, 12), "S"), new Case(new Position(12, 12),
						"M"));
		Collections.sort(environmentTemoin);
	}

	@Test
	public void init_environment_test() throws Exception {
		String[] env = new String[] { "S 7 8 S M", "S M S S M", "S M X M M", "S S M M M", "S M M S M" };

		LineSight ligneSight = new LineSight(env, new Position(10, 10), DELIMITER);
		Collections.sort(ligneSight.getCases());
		Assertions.assertThat(ligneSight.getCases()).containsOnly(environmentTemoin.toArray(new Case[] {}));
	}

	@Test
	public void getPositionsOfDimaonds_test() throws Exception {
		int positionCentraleX = 20;
		int positionCentraleY = 20;
		Case case1 = new Case(new Position(positionCentraleX - 1, positionCentraleY - 2), "7");
		Case case2 = new Case(new Position(positionCentraleX, positionCentraleY - 2), "8");
		List<Case> diamondsPositionsExpected = Arrays.asList(case1, case2);

		LineSight ligneSight = new LineSight(env, new Position(positionCentraleX, positionCentraleY), DELIMITER);
		List<Case> diamondsPositions = ligneSight.getDiamondsPositions();

		Collections.sort(diamondsPositions);
		Collections.sort(diamondsPositionsExpected);

		Assertions.assertThat(diamondsPositions).containsExactly(diamondsPositionsExpected.toArray(new Case[] {}));
	}

}
