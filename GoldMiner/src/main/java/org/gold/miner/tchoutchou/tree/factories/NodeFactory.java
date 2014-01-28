package org.gold.miner.tchoutchou.tree.factories;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.tree.Noeud;
import org.gold.miner.tchoutchou.tree.Racine;

public class NodeFactory {

	public static Set<Noeud> constructNodes(Map<Position, Case> casesInMap, Racine racine) {

		int positionX = racine.getCase().getPosition().getPositionX();
		int positionY = racine.getCase().getPosition().getPositionY();

		Set<Noeud> nodes = new HashSet<Noeud>();

		// ajout de la case EST
		Case caseTmp = casesInMap.get(new Position(positionX + 1, positionY));
		Noeud noeud1 = new Noeud(racine, caseTmp != null ? caseTmp : null);
		racine.addNoeudEst(noeud1);
		constructNodesRecursively(casesInMap, nodes, noeud1);

		// ajout de la case SUD
		caseTmp = casesInMap.get(new Position(positionX, positionY + 1));
		Noeud noeud2 = new Noeud(racine, caseTmp != null ? caseTmp : null);
		racine.addNoeudSud(noeud2);
		constructNodesRecursively(casesInMap, nodes, noeud2);

		// ajout de la case OUEST
		caseTmp = casesInMap.get(new Position(positionX - 1, positionY));
		Noeud noeud3 = new Noeud(racine, caseTmp != null ? caseTmp : null);
		racine.addNoeudOuest(noeud3);
		constructNodesRecursively(casesInMap, nodes, noeud3);

		// ajout de la case NORD
		caseTmp = casesInMap.get(new Position(positionX, positionY - 1));
		Noeud noeud4 = new Noeud(racine, caseTmp != null ? caseTmp : null);
		racine.addNoeudNord(noeud4);
		constructNodesRecursively(casesInMap, nodes, noeud4);


		return nodes;
	}

	public static void constructNodesRecursively(Map<Position, Case> casesInMap, Set<Noeud> nodes, Noeud noeud) {

		// position de la case courante
		int positionX = noeud.getCase().getPosition().getPositionX();
		int positionY = noeud.getCase().getPosition().getPositionY();

		// ajout de la case EST
		Case caseTmp = casesInMap.get(new Position(positionX + 1, positionY));
		if (caseTmp != null) {
			Noeud noeudEst = new Noeud(noeud, caseTmp);
			if (!nodes.contains(noeudEst)) {
				nodes.add(noeudEst);
				constructNodesRecursively(casesInMap, nodes, noeudEst);
				noeud.addNoeudEst(noeudEst);
			}
		}

		// ajout de la case SUD
		caseTmp = casesInMap.get(new Position(positionX, positionY + 1));
		if (caseTmp != null) {
			Noeud noeudSud = new Noeud(noeud, caseTmp);
			if (!nodes.contains(noeudSud)) {
				nodes.add(noeudSud);
				constructNodesRecursively(casesInMap, nodes, noeudSud);
				noeud.addNoeudSud(noeudSud);
			}
		}

		// ajout de la case OUEST
		caseTmp = casesInMap.get(new Position(positionX - 1, positionY));
		if (caseTmp != null) {
			Noeud noeudOuest = new Noeud(noeud, caseTmp);
			if (!nodes.contains(noeudOuest)) {
				nodes.add(noeudOuest);
				constructNodesRecursively(casesInMap, nodes, noeudOuest);
				noeud.addNoeudOuest(noeudOuest);
			}
		}

		// ajout de la case NORD
		caseTmp = casesInMap.get(new Position(positionX, positionY - 1));
		if (caseTmp != null) {
			Noeud noeudNord = new Noeud(noeud, caseTmp);
			if (!nodes.contains(noeudNord)) {
				nodes.add(noeudNord);
				constructNodesRecursively(casesInMap, nodes, noeudNord);
				noeud.addNoeudNord(noeudNord);
			}
		}
	}

}
