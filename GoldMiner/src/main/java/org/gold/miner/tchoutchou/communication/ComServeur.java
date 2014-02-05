package org.gold.miner.tchoutchou.communication;

import java.io.InputStream;
import java.util.Scanner;

public class ComServeur {

	Scanner scanner;

	public ComServeur() {
		this(System.in);
	}

	public ComServeur(InputStream in) {
		scanner = new Scanner(in);
	}

	public String getNextLine() {
		return scanner.nextLine();
	}

	public void sendCommand(String cmde) {
		System.out.println(cmde);
	}
}
