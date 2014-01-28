package org.gold.miner.tchoutchou.tree.factories;

import java.util.Map;

import org.gold.miner.tchoutchou.mine.Case;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.tree.Arbre;
import org.gold.miner.tchoutchou.tree.NoeudArbre;
import org.gold.miner.tchoutchou.tree.NoeudArbreImpl;
import org.gold.miner.tchoutchou.tree.RacineArbre;

public class TreeFactory {

	private TreeFactory() {
		// do nothing
	}

	public static Arbre constructTree(Position startPosition, Map<Position, Case> casesInMap) {
		Case caseDepart = casesInMap.get(startPosition);
		RacineArbre noeudRacine = new RacineArbre(caseDepart);

		int positionX = noeudRacine.getCase().getPosition().getPositionX();
		int positionY = noeudRacine.getCase().getPosition().getPositionY();

		// ajout de la case EST
		NoeudArbreImpl noeud1 = new NoeudArbreImpl(noeudRacine, casesInMap.get(new Position(positionX + 1, positionY)));
		// ajout de la case SUD
		NoeudArbreImpl noeud2 = new NoeudArbreImpl(noeudRacine, casesInMap.get(new Position(positionX, positionY + 1)));
		// ajout de la case OUEST
		NoeudArbreImpl noeud3 = new NoeudArbreImpl(noeudRacine, casesInMap.get(new Position(positionX - 1, positionY)));
		// ajout de la case NORD
		NoeudArbreImpl noeud4 = new NoeudArbreImpl(noeudRacine, casesInMap.get(new Position(positionX, positionY - 1)));

		noeudRacine.addNoeudEst(noeud1);
		noeudRacine.addNoeudSud(noeud2);
		noeudRacine.addNoeudOuest(noeud3);
		noeudRacine.addNoeudNord(noeud4);

		addNodes(casesInMap, noeud1);
		addNodes(casesInMap, noeud2);
		addNodes(casesInMap, noeud3);
		addNodes(casesInMap, noeud4);

		return new Arbre(noeudRacine);
	}
	
	private static void addNodes(Map<Position, Case> casesInMap, NoeudArbre noeud) {
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
		noeud.addNoeudSud(noeud2);
		noeud.addNoeudOuest(noeud3);
		noeud.addNoeudNord(noeud4);
	}

}
