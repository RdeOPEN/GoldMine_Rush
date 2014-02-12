package org.gold.miner.tchoutchou.mine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class MineTest {

	private static final String DELIMITER = "\\s";

	@Test
	public void instanciateMine() throws Exception {
		final String line = "40 25 170";

		Mine mine = new Mine(line, DELIMITER);
		Assertions.assertThat(mine.getLargeur()).isEqualTo(40);
		Assertions.assertThat(mine.getMaxXPosition()).isEqualTo(39);
		Assertions.assertThat(mine.getHauteur()).isEqualTo(25);
		Assertions.assertThat(mine.getMaxYPosition()).isEqualTo(24);
		Assertions.assertThat(mine.getNbDiamants()).isEqualTo(170);
		Assertions.assertThat(mine.getMineParts().size()).isEqualTo(40);
	}

	@Test
	public void instanciate_little_Mine() throws Exception {
		final String line = "5 5 2";

		Mine mine = new Mine(line, DELIMITER);
		Assertions.assertThat(mine.getLargeur()).isEqualTo(5);
		Assertions.assertThat(mine.getMaxXPosition()).isEqualTo(4);
		Assertions.assertThat(mine.getHauteur()).isEqualTo(5);
		Assertions.assertThat(mine.getMaxYPosition()).isEqualTo(4);
		Assertions.assertThat(mine.getNbDiamants()).isEqualTo(2);
		Assertions.assertThat(mine.getMineParts().size()).isEqualTo(1);
	}

	@Test
	public void instanciateMine_test_limites_X() throws Exception {
		final String line = "9 5 10";

		Mine mine = new Mine(line, DELIMITER);
		Assertions.assertThat(mine.getLargeur()).isEqualTo(9);
		Assertions.assertThat(mine.getMaxXPosition()).isEqualTo(8);
		Assertions.assertThat(mine.getHauteur()).isEqualTo(5);
		Assertions.assertThat(mine.getMaxYPosition()).isEqualTo(4);
		Assertions.assertThat(mine.getNbDiamants()).isEqualTo(10);

		Collection<MinePart> mineParts = mine.getMineParts();
		Assertions.assertThat(mineParts.size()).isEqualTo(2);

		Collection<MinePart> minePartsExpected = new HashSet<MinePart>();
		MinePart minePart1 = new MinePart(0, 4, 0, 4);
		MinePart minePart2 = new MinePart(5, 8, 0, 4);

		minePartsExpected.add(minePart1);
		minePartsExpected.add(minePart2);

		Assertions.assertThat(mineParts).containsOnly(minePartsExpected.toArray(new MinePart[minePartsExpected.size()]));
	}

	@Test
	public void instanciateMine_test_limites_X_2() throws Exception {
		final String line = "41 25 10";

		Mine mine = new Mine(line, DELIMITER);
		Assertions.assertThat(mine.getLargeur()).isEqualTo(41);
		Assertions.assertThat(mine.getMaxXPosition()).isEqualTo(40);
		Assertions.assertThat(mine.getHauteur()).isEqualTo(25);
		Assertions.assertThat(mine.getMaxYPosition()).isEqualTo(24);
		Assertions.assertThat(mine.getNbDiamants()).isEqualTo(10);

		Collection<MinePart> mineParts = mine.getMineParts();
		Assertions.assertThat(mineParts.size()).isEqualTo(45);
	}

	@Test
	public void instanciateMine_test_limites_Y() throws Exception {
		final String line = "5 9 10";

		Mine mine = new Mine(line, DELIMITER);
		Assertions.assertThat(mine.getLargeur()).isEqualTo(5);
		Assertions.assertThat(mine.getMaxXPosition()).isEqualTo(4);
		Assertions.assertThat(mine.getHauteur()).isEqualTo(9);
		Assertions.assertThat(mine.getMaxYPosition()).isEqualTo(8);
		Assertions.assertThat(mine.getNbDiamants()).isEqualTo(10);

		Collection<MinePart> mineParts = mine.getMineParts();
		Assertions.assertThat(mineParts.size()).isEqualTo(2);

		Set<MinePart> minePartsExpected = new HashSet<MinePart>();
		MinePart minePart1 = new MinePart(0, 4, 0, 4);
		MinePart minePart2 = new MinePart(0, 4, 5, 8);

		minePartsExpected.add(minePart1);
		minePartsExpected.add(minePart2);

		Assertions.assertThat(mineParts).containsOnly(minePartsExpected.toArray(new MinePart[minePartsExpected.size()]));
	}

	@Test
	public void instanciateMine_test_limites_Y_2() throws Exception {
		final String line = "25 56 10";

		Mine mine = new Mine(line, DELIMITER);
		Assertions.assertThat(mine.getLargeur()).isEqualTo(25);
		Assertions.assertThat(mine.getMaxXPosition()).isEqualTo(24);
		Assertions.assertThat(mine.getHauteur()).isEqualTo(56);
		Assertions.assertThat(mine.getMaxYPosition()).isEqualTo(55);
		Assertions.assertThat(mine.getNbDiamants()).isEqualTo(10);

		Collection<MinePart> mineParts = mine.getMineParts();
		Assertions.assertThat(mineParts.size()).isEqualTo(60);
	}

	@Test
	public void updateCases_must_update_mine_map() throws Exception {
		final String line = "40 25 170";
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

		Map<Position, Case> casesUpdated = mine.getCasesInMap();

		Assertions.assertThat(casesUpdated.size()).isEqualTo(casesToUpdate.size());
		Assertions.assertThat(casesUpdated.get(new Position(0, 0))).isEqualTo(new Case(new Position(0, 0), "S"));
		Assertions.assertThat(casesUpdated.get(new Position(0, 1))).isEqualTo(new Case(new Position(0, 1), "5"));
		Assertions.assertThat(casesUpdated.get(new Position(0, 2))).isEqualTo(new Case(new Position(0, 2), "E"));
		Assertions.assertThat(casesUpdated.get(new Position(0, 3))).isEqualTo(new Case(new Position(0, 3), "M"));
		Assertions.assertThat(casesUpdated.get(new Position(1, 0))).isEqualTo(new Case(new Position(1, 0), "M"));
		Assertions.assertThat(casesUpdated.get(new Position(1, 1))).isEqualTo(new Case(new Position(1, 1), "S"));
		Assertions.assertThat(casesUpdated.get(new Position(2, 1))).isEqualTo(new Case(new Position(2, 1), "E"));
		Assertions.assertThat(casesUpdated.get(new Position(2, 2))).isEqualTo(new Case(new Position(2, 2), "M"));
		Assertions.assertThat(casesUpdated.get(new Position(39, 24))).isEqualTo(new Case(new Position(39, 24), "S"));
		Assertions.assertThat(casesUpdated.get(new Position(15, 15))).isEqualTo(new Case(new Position(15, 15), "M"));

		Assertions.assertThat(mine.getCasesInMap()).isEqualTo(mine.getCasesInMap());
		Assertions.assertThat(mine.getCasesInMap().get(new Position(2, 2))).isEqualTo(mine.getCasesInMap().get(new Position(2, 2)));
	}

	@Test
	public void getCasesInCollection_test() throws Exception {
		final String line = "40 25 170";
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

		Collection<Case> casesInCollection = mine.getCasesInCollection();

		Assertions.assertThat(casesInCollection).containsOnly(casesToUpdate.toArray(new Case[casesInCollection.size()]));
	}

	@Test
	public void getDiamondsPositions_must_return_diamonds_positions_in_mine() throws Exception {
		final String line = "40 25 170";
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

		casesToUpdate.add(genCase(15, 15, "3"));
		casesToUpdate.add(genCase(8, 2, "2"));
		casesToUpdate.add(genCase(2, 6, "1"));

		mine.updateCases(casesToUpdate);

		Collection<Case> casesExpected = Arrays.asList(genCase(0, 1, "5"), genCase(15, 15, "3"), genCase(8, 2, "2"), genCase(2, 6, "1"));

		Collection<Case> diamondsPositions = mine.getDiamondsPositions();

		Assertions.assertThat(diamondsPositions).containsOnly(casesExpected.toArray(new Case[casesExpected.size()]));
	}

	private static Case genCase(int x, int y, String typeTerrain) {
		return new Case(new Position(x, y), typeTerrain);
	}

}
