package org.gold.miner.tchoutchou.tree.factories;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.fest.assertions.api.Assertions;
import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.tree.Arbre;
import org.gold.miner.tchoutchou.tree.NoeudArbre;
import org.gold.miner.tchoutchou.tree.NoeudArbreImpl;
import org.gold.miner.tchoutchou.tree.RacineArbre;
import org.junit.Test;

public class TreeFactoryTest {

	// ++ 08 09 10 11 12
	// 08 S--7--8--S--M
	// 09 S--M--S--S--M
	// 10 S--M--X--M--M
	// 11 S--S--M--M--M
	// 12 S--M--M--S--M

	String[] env = new String[] { "S 7 8 S M", "S M S S M", "S M X M M", "S S M M M", "S M M S M" };
	private static final String DELIMITER = "\\s";

	@Test
	public void constructTree() throws Exception {
		Position startPosition = new Position(10, 10);
		LineSight ligneSight = new LineSight(env, startPosition, DELIMITER);
		Mine mine = new Mine("40 40 50", DELIMITER);
		mine.updateCases(ligneSight);

		Map<Position, Case> casesInMap = mine.getCasesInMap();

		RacineArbre racine = new RacineArbre(casesInMap.get(startPosition));
		// ajout de la case EST
		NoeudArbreImpl noeud1 = new NoeudArbreImpl(racine, casesInMap.get(new Position(11, 10)));
		// ajout de la case SUD
		NoeudArbreImpl noeud2 = new NoeudArbreImpl(racine, casesInMap.get(new Position(10, 11)));
		// ajout de la case OUEST
		NoeudArbreImpl noeud3 = new NoeudArbreImpl(racine, casesInMap.get(new Position(9, 10)));
		// ajout de la case NORD
		NoeudArbreImpl noeud4 = new NoeudArbreImpl(racine, casesInMap.get(new Position(10, 9)));

		racine.addNoeudEst(noeud1);
		racine.addNoeudSud(noeud2);
		racine.addNoeudOuest(noeud3);
		racine.addNoeudNord(noeud4);

		addNoeuds(casesInMap, noeud1);
		addNoeuds(casesInMap, noeud2);
		addNoeuds(casesInMap, noeud3);
		addNoeuds(casesInMap, noeud4);

		Arbre arbre = TreeFactory.constructTree(startPosition, casesInMap);

		Assertions.assertThat(arbre.getNoeudRacine()).isEqualTo(racine);
	}

	private static Set<NoeudArbreImpl> addNoeuds(Map<Position, Case> casesInMap, NoeudArbre noeud) {
		Set<NoeudArbreImpl> casesInSet = new HashSet<NoeudArbreImpl>();

		int positionX = noeud.getCase().getPosition().getPositionX();
		int positionY = noeud.getCase().getPosition().getPositionY();

		// ajout de la case EST
		Case caseTmp = casesInMap.get(new Position(positionX + 1, positionY));
		NoeudArbreImpl noeud1 = new NoeudArbreImpl(noeud, caseTmp != null ? caseTmp : null);
		// ajout de la case SUD
		caseTmp = casesInMap.get(new Position(positionX, positionY + 1));
		NoeudArbreImpl noeud2 = new NoeudArbreImpl(noeud, caseTmp != null ? caseTmp : null);
		// ajout de la case OUEST
		caseTmp = casesInMap.get(new Position(positionX - 1, positionY));
		NoeudArbreImpl noeud3 = new NoeudArbreImpl(noeud, caseTmp != null ? caseTmp : null);
		// ajout de la case NORD
		caseTmp = casesInMap.get(new Position(positionX, positionY - 1));
		NoeudArbreImpl noeud4 = new NoeudArbreImpl(noeud, caseTmp != null ? caseTmp : null);

		noeud.addNoeudEst(noeud1);
		casesInSet.add(noeud1);
		noeud.addNoeudSud(noeud2);
		casesInSet.add(noeud2);
		noeud.addNoeudOuest(noeud3);
		casesInSet.add(noeud3);
		noeud.addNoeudNord(noeud4);
		casesInSet.add(noeud4);

		return casesInSet;
	}
	
}
