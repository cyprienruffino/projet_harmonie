package main;

import java.io.*;
import java.util.ArrayList;

import accords.*;

public class Main {
	public static void main(String[] args) { // Main de test temporaire
		try {
			Partition partition = new Partition(Partatab.readChant(new File("./data/AuClairDeLaLune.chant")));
			partition.generate();
			for(ArrayList<Accord> liste:partition.getJeu())
				System.out.println(" "+liste.get(0).getAccord());
		} catch (IOException e) {
			System.out.println("Erreur d'entrée-sortie");
		}
	}
}
