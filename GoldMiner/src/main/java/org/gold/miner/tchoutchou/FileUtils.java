package org.gold.miner.tchoutchou;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	public static boolean tracesActivated = true;

	// private static String cheminFichierTraces = "E:\\Raf\\Workspaces\\GitHub\\GoldMine_Rush\\GoldMiner\\target\\tracesMineur.txt";
	// private static String cheminFichierTraces = "C:\\Users\\RDE11587\\Documents\\GitHub\\GoldMine_Rush\\GoldMiner\\target\\tracesMineur.txt";

	private static final String repCourant = System.getProperty("java.io.tmpdir");
	private static final String nomFichierDeSortie = "tracesMineur.txt";
	private static final String cheminFichierTraces = repCourant + nomFichierDeSortie;

	public static void writeInTracesFile(String content) {
		if (tracesActivated) {
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
	}

	public static boolean deleteTracesFile() {
		File file = new File(cheminFichierTraces);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	public static String getCheminFichierTraces() {
		return cheminFichierTraces;
	}
}
