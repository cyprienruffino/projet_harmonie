package main;


import java.io.*;
import java.util.ArrayList;

import main.Accord;

import accords.*;

public class Partatab {
	// pour c'est variable voir le commentaire du tableau list
	static final int DO = 0;
	static final int RE = 1;
	static final int MI = 2;
	static final int FA = 3;
	static final int SOL = 4;
	static final int LA = 5;
	static final int SI = 6;

	public static ArrayList<Accord>[] readChant(File f) throws IOException {
		// lecture fichier
		BufferedReader entre = new BufferedReader(new FileReader(f));
		// je ne sais pas aller direrctement a la deuxieme ligne ((((((^_^;)
		entre.readLine();

		String patt = entre.readLine();
		int n = nbnote(patt);
		// le tableau d'accords que je vai renvoié
		// si vous avez un nom de tableau plus cool il peut etre changer j'ai
		// juste mi se a quoi je pancer
		ArrayList<Accord>[] skunk = new ArrayList[n];

		/*
		 * comme il y avait un pb avec l'ancien version je recre le tableau qu
		 * il y a sur la copie est j'irais chercher les valeur les variable
		 * statique sont donc la pour facilite la methode remplire je suis
		 * concien qu'il y a plus classe mais bon le tableau n'est que
		 * temporaire et de tout façon c'est que le prototype
		 */
		int[][] liste = new int[4][7];
		remplire(liste);
		/*
		 * comme le il peut y avoir des sol qui on + caractere que les autre je
		 * fait une methode qui me returne un tableau de toute les substring
		 */
		String[] isol = new String[n];
		isolement(patt, isol);
		// le plus lourd est enfin passer
		for (int i = 0; i < n; i++) {
			skunk[i]= new ArrayList<Accord>();
			skunk[i].add(new VI(petitenote(isol[i], liste), 0, 0, 0, 0));
		}
		return skunk;
	}

	public static void isolement(String s, String[] si) {
		int case_du_tableau = 0; 
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)==':'){
				if(s.charAt(i-1)=='-')
					si[case_du_tableau]=s.substring(i-1, i+2);
				else if(s.charAt(i-2)=='l')
					si[case_du_tableau]=s.substring(i-4, i+2);
				else
					si[case_du_tableau]=s.substring(i-3, i+2);
				case_du_tableau++;
			}
		}
	}

	// recopie le tableau du projé
	public static void remplire(int[][] tab) {
		int note = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				tab[i][j] = note;
				note++;
			}
		}
	}

	// combien de note contien la partition sachent que ' ' est le separateur de
	// note ? vous avez 3min
	public static int nbnote(String d) {
		int x = 1;
		for (int i = 0; i < d.length(); i++) {
			if (d.charAt(i) == ' ')
				x++;
		}
		return x;
	}

	// va chercher les valeur dans le tableau
	public static int petitenote(String s, int[][] tab) {
		String note = s.substring(0, 2);
		if (note.equals("so"))
			return tab[s.charAt(3) - 49][SOL];
		else if (note.equals("si"))
			return tab[s.charAt(2) - 49][SI];
		else if (note.equals("fa"))
			return tab[s.charAt(2) - 49][FA];
		else if (note.equals("do"))
			return tab[s.charAt(2) - 49][DO];
		else if (note.equals("re"))
			return tab[s.charAt(2) - 49][RE];
		else if (note.equals("mi"))
			return tab[s.charAt(2) - 49][MI];
		else if (note.equals("la"))
			return tab[s.charAt(2) - 49][LA];
		else
			return -1;
	}
}