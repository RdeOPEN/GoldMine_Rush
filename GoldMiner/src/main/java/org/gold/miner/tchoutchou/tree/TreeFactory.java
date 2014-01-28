package org.gold.miner.tchoutchou.tree;

import java.util.Map;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;

public class TreeFactory {

	private TreeFactory() {
		// do nothing
	}

	public static Arbre constructTree(Position startPosition, Map<Position, Case> casesInMap) {
		Case caseDepart = casesInMap.get(startPosition);
		Racine noeudRacine = new Racine(caseDepart);

		int positionX = noeudRacine.getCase().getPosition().getPositionX();
		int positionY = noeudRacine.getCase().getPosition().getPositionY();

		// ajout de la case EST
		Noeud noeud1 = new Noeud(noeudRacine, casesInMap.get(new Position(positionX + 1, positionY)));
		// ajout de la case SUD
		Noeud noeud2 = new Noeud(noeudRacine, casesInMap.get(new Position(positionX, positionY + 1)));
		// ajout de la case OUEST
		Noeud noeud3 = new Noeud(noeudRacine, casesInMap.get(new Position(positionX - 1, positionY)));
		// ajout de la case NORD
		Noeud noeud4 = new Noeud(noeudRacine, casesInMap.get(new Position(positionX, positionY - 1)));

		noeudRacine.addNoeudEst(noeud1);
		noeudRacine.addNoeudSud(noeud2);
		noeudRacine.addNoeudOuest(noeud3);
		noeudRacine.addNoeudNord(noeud4);

		addNoeuds(casesInMap, noeud1);
		addNoeuds(casesInMap, noeud2);
		addNoeuds(casesInMap, noeud3);
		addNoeuds(casesInMap, noeud4);

		return new Arbre(noeudRacine);
	}

	private static void addNoeuds(Map<Position, Case> casesInMap, NoeudArbre noeud) {
		int positionX = noeud.getCase().getPosition().getPositionX();
		int positionY = noeud.getCase().getPosition().getPositionY();

		// ajout de la case EST
		Case caseTmp = casesInMap.get(new Position(positionX + 1, positionY));
		Noeud noeud1 = new Noeud(noeud, caseTmp != null ? caseTmp : null);
		// ajout de la case SUD
		caseTmp = casesInMap.get(new Position(positionX, positionY + 1));
		Noeud noeud2 = new Noeud(noeud, caseTmp != null ? caseTmp : null);
		// ajout de la case OUEST
		caseTmp = casesInMap.get(new Position(positionX - 1, positionY));
		Noeud noeud3 = new Noeud(noeud, caseTmp != null ? caseTmp : null);
		// ajout de la case NORD
		caseTmp = casesInMap.get(new Position(positionX, positionY - 1));
		Noeud noeud4 = new Noeud(noeud, caseTmp != null ? caseTmp : null);

		noeud.addNoeudEst(noeud1);
		noeud.addNoeudSud(noeud2);
		noeud.addNoeudOuest(noeud3);
		noeud.addNoeudNord(noeud4);
	}

}
