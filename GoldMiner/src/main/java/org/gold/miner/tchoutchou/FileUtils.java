package org.gold.miner.tchoutchou;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	// private static String nomFichierDeSortie = "E:\\Raf\\Workspaces\\GitHub\\GoldMine_Rush\\GoldMiner\\target\\tracesMineur.txt";
	// private static String nomFichierDeSortie = "C:\\Users\\RDE11587\\Documents\\GitHub\\GoldMine_Rush\\GoldMiner\\target\\tracesMineur.txt";

	private static final String repCourant = new java.io.File("").getAbsolutePath();
	private static final char pathseparator = File.separatorChar;
	private static final String nomFichierDeSortie = "tracesMineur.txt";
	private static final String cheminFichierTraces = repCourant + pathseparator + nomFichierDeSortie;

	public static void writeInTracesFile(String content) {
		FileWriter fichier = null;
		try {
			File file = new File(cheminFichierTraces);
			fichier = new FileWriter(file, true);
			fichier.write(content + "\n");
			fichier.flush();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			if (fichier != null) {
				try {
					fichier.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	public static boolean deleteTracesFile() {
		File file = new File(nomFichierDeSortie);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	public static String getCheminFichierTraces() {
		return cheminFichierTraces;
	}
}