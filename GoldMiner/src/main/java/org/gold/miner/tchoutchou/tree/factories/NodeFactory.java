package org.gold.miner.tchoutchou.tree.factories;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.tree.NoeudArbreImpl;
import org.gold.miner.tchoutchou.tree.RacineArbre;

public class NodeFactory {

	public static Set<NoeudArbreImpl> constructNodes(Map<Position, Case> casesInMap, RacineArbre racine) {

		int positionX = racine.getCase().getPosition().getPositionX();
		int positionY = racine.getCase().getPosition().getPositionY();

		Set<NoeudArbreImpl> nodes = new HashSet<NoeudArbreImpl>();

		// ajout de la case EST
		Case caseTmp = casesInMap.get(new Position(positionX + 1, positionY));
		NoeudArbreImpl noeud1 = new NoeudArbreImpl(racine, caseTmp != null ? caseTmp : null);
		racine.addNoeudEst(noeud1);
		constructNodesRecursively(casesInMap, nodes, noeud1);

		// ajout de la case SUD
		caseTmp = casesInMap.get(new Position(positionX, positionY + 1));
		NoeudArbreImpl noeud2 = new NoeudArbreImpl(racine, caseTmp != null ? caseTmp : null);
		racine.addNoeudSud(noeud2);
		constructNodesRecursively(casesInMap, nodes, noeud2);

		// ajout de la case OUEST
		caseTmp = casesInMap.get(new Position(positionX - 1, positionY));
		NoeudArbreImpl noeud3 = new NoeudArbreImpl(racine, caseTmp != null ? caseTmp : null);
		racine.addNoeudOuest(noeud3);
		constructNodesRecursively(casesInMap, nodes, noeud3);

		// ajout de la case NORD
		caseTmp = casesInMap.get(new Position(positionX, positionY - 1));
		NoeudArbreImpl noeud4 = new NoeudArbreImpl(racine, caseTmp != null ? caseTmp : null);
		racine.addNoeudNord(noeud4);
		constructNodesRecursively(casesInMap, nodes, noeud4);


		return nodes;
	}

	public static void constructNodesRecursively(Map<Position, Case> casesInMap, Set<NoeudArbreImpl> nodes, NoeudArbreImpl noeud) {

		// position de la case courante
		int positionX = noeud.getCase().getPosition().getPositionX();
		int positionY = noeud.getCase().getPosition().getPositionY();

		// ajout de la case EST
		Case caseTmp = casesInMap.get(new Position(positionX + 1, positionY));
		if (caseTmp != null) {
			NoeudArbreImpl noeudEst = new NoeudArbreImpl(noeud, caseTmp);
			if (!nodes.contains(noeudEst)) {
				nodes.add(noeudEst);
				constructNodesRecursively(casesInMap, nodes, noeudEst);
				noeud.addNoeudEst(noeudEst);
			}
		}

		// ajout de la case SUD
		caseTmp = casesInMap.get(new Position(positionX, positionY + 1));
		if (caseTmp != null) {
			NoeudArbreImpl noeudSud = new NoeudArbreImpl(noeud, caseTmp);
			if (!nodes.contains(noeudSud)) {
				nodes.add(noeudSud);
				constructNodesRecursively(casesInMap, nodes, noeudSud);
				noeud.addNoeudSud(noeudSud);
			}
		}

		// ajout de la case OUEST
		caseTmp = casesInMap.get(new Position(positionX - 1, positionY));
		if (caseTmp != null) {
			NoeudArbreImpl noeudOuest = new NoeudArbreImpl(noeud, caseTmp);
			if (!nodes.contains(noeudOuest)) {
				nodes.add(noeudOuest);
				constructNodesRecursively(casesInMap, nodes, noeudOuest);
				noeud.addNoeudOuest(noeudOuest);
			}
		}

		// ajout de la case NORD
		caseTmp = casesInMap.get(new Position(positionX, positionY - 1));
		if (caseTmp != null) {
			NoeudArbreImpl noeudNord = new NoeudArbreImpl(noeud, caseTmp);
			if (!nodes.contains(noeudNord)) {
				nodes.add(noeudNord);
				constructNodesRecursively(casesInMap, nodes, noeudNord);
				noeud.addNoeudNord(noeudNord);
			}
		}
	}

}
