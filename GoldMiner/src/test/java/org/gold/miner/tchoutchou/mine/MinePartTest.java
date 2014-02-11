package org.gold.miner.tchoutchou.mine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class MinePartTest {

	@Test
	public void updateCases_must_add_cases_correctly() throws Exception {
		MinePart mineParts = new MinePart(0, 4, 0, 4);

		List<Case> casesToUpdate = new ArrayList<Case>();
		casesToUpdate.add(genCase(0, 0, "S"));
		casesToUpdate.add(genCase(0, 1, "5"));
		casesToUpdate.add(genCase(0, 2, "E"));
		casesToUpdate.add(genCase(0, 3, "M"));
		casesToUpdate.add(genCase(0, 4, "M"));
		casesToUpdate.add(genCase(1, 0, "M"));
		casesToUpdate.add(genCase(1, 1, "S"));
		casesToUpdate.add(genCase(1, 2, "S"));
		casesToUpdate.add(genCase(1, 3, "S"));
		casesToUpdate.add(genCase(1, 4, "S"));
		casesToUpdate.add(genCase(2, 0, "E"));
		casesToUpdate.add(genCase(2, 1, "E"));
		casesToUpdate.add(genCase(2, 2, "M"));
		casesToUpdate.add(genCase(2, 3, "M"));
		casesToUpdate.add(genCase(2, 4, "M"));
		casesToUpdate.add(genCase(3, 0, "S"));
		casesToUpdate.add(genCase(3, 1, "S"));
		casesToUpdate.add(genCase(3, 2, "4"));
		casesToUpdate.add(genCase(3, 3, "M"));
		casesToUpdate.add(genCase(3, 4, "M"));
		casesToUpdate.add(genCase(4, 0, "S"));
		casesToUpdate.add(genCase(4, 1, "M"));
		casesToUpdate.add(genCase(4, 2, "M"));
		casesToUpdate.add(genCase(4, 3, "S"));
		casesToUpdate.add(genCase(4, 4, "M"));

		List<Case> casesExpected = new ArrayList<Case>(casesToUpdate);
		Collections.sort(casesExpected);

		// cases qui ne doivent pas être ajoutes
		casesToUpdate.add(genCase(15, 15, "3"));
		casesToUpdate.add(genCase(15, 30, "X"));
		casesToUpdate.add(genCase(8, 2, "M"));
		casesToUpdate.add(genCase(2, 6, "1"));
		casesToUpdate.add(genCase(8, 6, "E"));

		// appel methode a tester
		mineParts.updateCases(casesToUpdate);

		// verification
		Map<Position, Case> mapCases = mineParts.getMapCases();
		Assertions.assertThat(mapCases).isNotNull();
		Assertions.assertThat(mapCases).isNotEmpty();
		Assertions.assertThat(mapCases.size()).isEqualTo(25);

		List<Case> values = new ArrayList<Case>(mapCases.values());
		Collections.sort(values);

		Assertions.assertThat(values).containsExactly(casesExpected.toArray(new Case[] {}));
	}

	private static Case genCase(int x, int y, String typeTerrain) {
		return new Case(new Position(x, y), typeTerrain);
	}

}
