package org.gold.miner.tchoutchou.mine;

import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
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
		casesToUpdate.add(new Case(new Position(0, 0), "S"));
		casesToUpdate.add(new Case(new Position(1, 0), "M"));
		casesToUpdate.add(new Case(new Position(0, 1), "5"));

		mine.updateCases(casesToUpdate);
		
		Case[][] casesUpdated = mine.getCases();
		
		Assertions.assertThat(casesUpdated[0][0]).isEqualTo(new Case(new Position(0, 0), "S"));
		Assertions.assertThat(casesUpdated[1][0]).isEqualTo(new Case(new Position(1, 0), "M"));
		Assertions.assertThat(casesUpdated[0][1]).isEqualTo(new Case(new Position(0, 1), "5"));
	}
}
