package org.gold.miner.tchoutchou.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class MineTest {

	private static final String DELIMITER = "\\s";

	@Test
	public void instanciateMine() throws Exception {
		String line = "40 25 170";

		Mine mine = new Mine(line, DELIMITER);
		Assertions.assertThat(mine.getLargeur()).isEqualTo(40);
		Assertions.assertThat(mine.getHauteur()).isEqualTo(25);
		Assertions.assertThat(mine.getNbDiamants()).isEqualTo(170);
	}

	@Test
	public void updateCases_must_update_mine_map() throws Exception {
		String line = "40 25 170";
		Mine mine = new Mine(line, DELIMITER);

		List<Case> casesToUpdate = new ArrayList<Case>();
		casesToUpdate.add(genCase(0, 0, "S"));
		casesToUpdate.add(genCase(0, 1, "5"));
		casesToUpdate.add(genCase(0, 2, "E"));
		casesToUpdate.add(genCase(0, 3, "M"));
		casesToUpdate.add(genCase(1, 0, "M"));
		casesToUpdate.add(genCase(1, 1, "S"));
		casesToUpdate.add(genCase(2, 1, "E"));
		casesToUpdate.add(genCase(2, 2, "M"));
		casesToUpdate.add(genCase(39, 24, "S"));
		casesToUpdate.add(genCase(15, 15, "M"));

		mine.updateCases(casesToUpdate);

		Case[][] casesUpdated = mine.getCases();

		Assertions.assertThat(casesUpdated.length).isEqualTo(40);
		Assertions.assertThat(casesUpdated[0].length).isEqualTo(25);
		Assertions.assertThat(casesUpdated[0][0]).isEqualTo(new Case(new Position(0, 0), "S"));
		Assertions.assertThat(casesUpdated[0][1]).isEqualTo(new Case(new Position(0, 1), "5"));
		Assertions.assertThat(casesUpdated[0][2]).isEqualTo(new Case(new Position(0, 2), "E"));
		Assertions.assertThat(casesUpdated[0][3]).isEqualTo(new Case(new Position(0, 3), "M"));
		Assertions.assertThat(casesUpdated[1][0]).isEqualTo(new Case(new Position(1, 0), "M"));
		Assertions.assertThat(casesUpdated[1][1]).isEqualTo(new Case(new Position(1, 1), "S"));
		Assertions.assertThat(casesUpdated[2][1]).isEqualTo(new Case(new Position(2, 1), "E"));
		Assertions.assertThat(casesUpdated[2][2]).isEqualTo(new Case(new Position(2, 2), "M"));
		Assertions.assertThat(casesUpdated[39][24]).isEqualTo(new Case(new Position(39, 24), "S"));
		Assertions.assertThat(casesUpdated[15][15]).isEqualTo(new Case(new Position(15, 15), "M"));
	}

	@Test
	public void getCasesInCollection_test() throws Exception {
		String line = "40 25 170";
		Mine mine = new Mine(line, DELIMITER);

		List<Case> casesToUpdate = new ArrayList<Case>();
		casesToUpdate.add(genCase(0, 0, "S"));
		casesToUpdate.add(genCase(0, 1, "5"));
		casesToUpdate.add(genCase(0, 2, "E"));
		casesToUpdate.add(genCase(0, 3, "M"));
		casesToUpdate.add(genCase(1, 0, "M"));
		casesToUpdate.add(genCase(1, 1, "S"));
		casesToUpdate.add(genCase(2, 1, "E"));
		casesToUpdate.add(genCase(2, 2, "M"));

		mine.updateCases(casesToUpdate);

		Set<Case> casesInCollection = mine.getCasesInCollection();

		Assertions.assertThat(casesInCollection).containsOnly(casesToUpdate.toArray(new Case[casesInCollection.size()]));
	}

	private static Case genCase(int x, int y, String typeTerrain) {
		return new Case(new Position(x, y), typeTerrain);
	}

}
