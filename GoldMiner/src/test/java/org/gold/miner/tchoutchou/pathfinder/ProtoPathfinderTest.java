package org.gold.miner.tchoutchou.pathfinder;

import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProtoPathfinderTest {

	private static final String DELIMITER = "\\s";
	private static Mine mine;

	@BeforeClass
	public static void setUp() {
		String line = "5 5 3";
		mine = new Mine(line, DELIMITER);

		// + 0 1 2 3 4
		// 0 S S S S S
		// 1 S M S M S
		// 2 M M E M S
		// 3 M S S M S
		// 4 3 M M M S

		List<Case> casesToUpdate = new ArrayList<Case>();

		// colonne 0
		casesToUpdate.add(genCase(0, 0, "S"));
		casesToUpdate.add(genCase(0, 1, "S"));
		casesToUpdate.add(genCase(0, 2, "M"));
		casesToUpdate.add(genCase(0, 3, "M"));
		casesToUpdate.add(genCase(0, 4, "3"));

		// colonne 1
		casesToUpdate.add(genCase(1, 0, "S"));
		casesToUpdate.add(genCase(1, 1, "M"));
		casesToUpdate.add(genCase(1, 2, "M"));
		casesToUpdate.add(genCase(1, 3, "S"));
		casesToUpdate.add(genCase(1, 4, "M"));

		// colonne 2
		casesToUpdate.add(genCase(2, 0, "S"));
		casesToUpdate.add(genCase(2, 1, "S"));
		casesToUpdate.add(genCase(2, 2, "E"));
		casesToUpdate.add(genCase(2, 3, "S"));
		casesToUpdate.add(genCase(2, 4, "M"));

		// colonne 3
		casesToUpdate.add(genCase(3, 0, "S"));
		casesToUpdate.add(genCase(3, 1, "M"));
		casesToUpdate.add(genCase(3, 2, "M"));
		casesToUpdate.add(genCase(3, 3, "M"));
		casesToUpdate.add(genCase(3, 4, "M"));

		// colonne 4
		casesToUpdate.add(genCase(4, 0, "S"));
		casesToUpdate.add(genCase(4, 1, "S"));
		casesToUpdate.add(genCase(4, 2, "S"));
		casesToUpdate.add(genCase(4, 3, "S"));
		casesToUpdate.add(genCase(4, 4, "S"));

		mine.updateCases(casesToUpdate);
	}

	@Test
	public void moveTo_must_return_actions_to_go_to_diamonds() throws Exception {
		Position destination = new Position(0, 4);

		ProtoPathfinder protoPathfinder = new ProtoPathfinder(mine);
		
		MinerAction minerAction = protoPathfinder.moveTo(new Position(2, 2), destination);
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.WEST);
		
		minerAction = protoPathfinder.moveTo(new Position(1, 2), destination);
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.WEST);
		
		minerAction = protoPathfinder.moveTo(new Position(0, 2), destination);
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.SOUTH);
		
		minerAction = protoPathfinder.moveTo(new Position(0, 3), destination);
		Assertions.assertThat(minerAction).isEqualTo(MinerAction.SOUTH);
	}

	private static Case genCase(int x, int y, String typeTerrain) {
		return new Case(new Position(x, y), typeTerrain);
	}

}
