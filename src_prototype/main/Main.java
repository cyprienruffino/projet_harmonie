package main;

import java.io.*;

public class Main {
	public static void main(String[] args) { // Main de test temporaire
		try {
			int[][] partition = Partatab.readChant(new File(
					"./data/AuClairDeLaLune.chant"));
			Partition.generate(partition);
			for (int[] tab : Partition.getJeu())
				System.out.println("Soprano " + tab[0] + ":" + tab[1]
						+ " Alto " + tab[2] + ":" + tab[3] + " Tenor " + tab[4]
						+ ":" + tab[5] + " Basse " + tab[6] + ":" + tab[7]
						+ " Accord " + tab[8]);
		} catch (IOException e) {
			System.out.println("Erreur d'entrée-sortie");
		}
	}
}
