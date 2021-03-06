package org.gold.miner.tchoutchou;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gold.miner.tchoutchou.communication.ComServeur;
import org.gold.miner.tchoutchou.mine.LineSight;
import org.gold.miner.tchoutchou.mine.Mine;
import org.gold.miner.tchoutchou.mine.Position;
import org.gold.miner.tchoutchou.mineur.Miner;
import org.gold.miner.tchoutchou.mineur.MinerAction;
import org.gold.miner.tchoutchou.mineur.MinerFactory;
import org.gold.miner.tchoutchou.mineur.archetypes.MinerArchetype;
import org.gold.miner.tchoutchou.pathfinder.Pathfinder;
import org.gold.miner.tchoutchou.pathfinder.PathfinderArchetype;
import org.gold.miner.tchoutchou.pathfinder.PathfinderFactory;

public class Main {

	private static final String DELIMITER_SPACE = "\\s";
	private static MinerArchetype minerArchetype = MinerArchetype.PROTOMINER;
	private static PathfinderArchetype pathfinderArchetype = PathfinderArchetype.PROTOPATHFINDER;

	public static void main(String[] args) {

		FileUtils.tracesActivated = true;

		FileUtils.deleteTracesFile();
		FileUtils.writeInTracesFile("Start Main mineur: " + new Date());

		try {
			ComServeur comServeur = new ComServeur();

			// on transmet notre nom au simu pour initier la communication
			comServeur.sendCommand("Mineur TchouTchou");

			// on reçoit les informations d'initialisation de la mine (largeur, hauteur et nombre total de diamants). ex: 40 25 170
			// le nombre total de diamants disponibles dans la mine (ceux portés par les joueurs ne sont pas comptés mais ceux dans les chariots oui
			String mineProperties = comServeur.getNextLine();
			Mine mine = new Mine(mineProperties, DELIMITER_SPACE);

			FileUtils.writeInTracesFile("mineProperties: " + mineProperties);

			// sauvegarde de la position du chariot
			Position positionChariot = null;
			Pathfinder pathFinder = PathfinderFactory.createPathfinder(pathfinderArchetype, mine);
			Miner miner = MinerFactory.createMiner(minerArchetype, pathFinder, null, null, MinerAction.EAST, null, null, 0);

			int nbTours = 0;
			while (true) {
				nbTours++;
				FileUtils.writeInTracesFile("*********** TOUR " + nbTours + " ***********");
				// - La 1ere ligne indique la position du mineur sur la carte sous la forme 'x y nbAdversaires' (0≤x<largeur et 0≤y<hauteur) et nombre de joueur
				// en vue
				// 1ere ligne: 12 18 0
				// - De la 2nd ligne a la 6ème on a une représentation de la carte autour du mineur sous la forme de 5 caractères (entre chaque un espace fait
				// office de delimiter
				// X: notre mineur, M: Mud (terre), S: Stone (pierre), entier: nombre de diamants présent, E: Empty (vide)
				// 2eme ligne: S 7 7 S M
				// 3eme ligne: S M S S M
				// 4eme ligne: S M X M M
				// 5eme ligne: S S M M M
				// 6eme ligne: S M M S M
				// - 7eme, 8eme et 9eme lignes sont présentes seulement si des mineurs adverses sont en vue, position du mineur sur la carte (0≤x1<largeur et
				// 0≤y1<hauteur)
				// x1 y1
				// x2 y2
				// x3 y3

				// Position du mineur et nombre d'ennemis aux alentours
				String positionAndNbEnnemis = comServeur.getNextLine();
				Position currentPositionMiner = new Position(positionAndNbEnnemis, DELIMITER_SPACE);

				FileUtils.writeInTracesFile("positionAndNbEnnemis: " + positionAndNbEnnemis);

				// on garde la position d'origine du chariot si positionChariot vaut null
				if (positionChariot == null) {
					FileUtils.writeInTracesFile("Affectation position chariot: " + currentPositionMiner);
					positionChariot = currentPositionMiner;
				}

				String[] posValues = positionAndNbEnnemis.split(DELIMITER_SPACE);
				int nbEnnemis = Integer.parseInt(posValues[2]);

				FileUtils.writeInTracesFile("------ lineSightLines ------");
				// on récupère les 5 prochaines lignes décrivant l'environnement immédiat du mineur (ligne de vue)
				String[] lineSightLines = new String[5];
				for (int i = 0; i < 5; i++) {
					lineSightLines[i] = comServeur.getNextLine();
					FileUtils.writeInTracesFile("lineSightLines[" + i + "]: " + lineSightLines[i]);
				}
				FileUtils.writeInTracesFile("----------------------------");

				LineSight lineSight = new LineSight(lineSightLines, currentPositionMiner, DELIMITER_SPACE);

				// recuperation des position des adversaires (optionnel)
				List<Position> positionOpponents = new ArrayList<Position>();
				for (int i = 1; i <= nbEnnemis; i++) {
					positionOpponents.add(new Position(comServeur.getNextLine(), DELIMITER_SPACE));
				}

				// mise à jour de la carte de la mine
				mine.updateCases(lineSight);
				FileUtils.writeInTracesFile("Mise a jour Mine effectuee!");

				// initialisation pathFinder pour le tour courant
				// pathFinder = PathfinderFactory.createPathfinder(pathfinderArchetype, mine);

				// initialisation du mineur pour le tour courant
				miner = MinerFactory.createMiner(minerArchetype, pathFinder, positionChariot, currentPositionMiner, miner.getDirection(), lineSight,
						positionOpponents, miner.getNbDiamonds());

				// send miner's action to goldrush-simu
				String actionToSend = miner.doAction().toString();
				FileUtils.writeInTracesFile("Action prochain tour: " + actionToSend);
				FileUtils.writeInTracesFile("");
				comServeur.sendCommand(actionToSend);
			}
		} catch (Exception e) {
			FileUtils.writeInTracesFile("ERROR: " + e.getMessage());
			FileUtils.writeInTracesFile("CAUSE: " + e.getCause());
		}
	}

}
