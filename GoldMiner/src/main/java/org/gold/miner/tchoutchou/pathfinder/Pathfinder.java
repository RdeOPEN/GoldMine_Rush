package org.gold.miner.tchoutchou.pathfinder;

import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.tree.ResultatRechercheChemin;

public interface Pathfinder {

	ResultatRechercheChemin exploreTo(Position currentPosition, Position destination);

	ResultatRechercheChemin gotoDiamonds(Position currentPosition);

}
