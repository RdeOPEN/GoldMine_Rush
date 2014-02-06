package org.gold.miner.tchoutchou;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	private static String nomFichier = "E:\\Raf\\Workspaces\\GitHub\\GoldMine_Rush\\GoldMiner\\target\\tracesMineur.txt";

	public static void writeInFile(String content) {
		FileWriter fichier;
		try {
			File file = new File(nomFichier);
			fichier = new FileWriter(file, true);
			fichier.write(content + "\n");
			fichier.flush();
			fichier.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public static void supprFile() {
		File file = new File(nomFichier);
		if (file.exists()) {
			file.delete();
		}
	}

}
