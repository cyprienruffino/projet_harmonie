package harmonie;

import java.util.*;

public class HarmonyGenerator {

	private static int[][] jeu;
	private static ArrayList<Integer>[][] suivant;

	public static void generate(int[][] s) {
		// Génère tous les cas possibles de partitions suivant le schéma du
		// sujet. Utiliser getJeu et getSuivant pour le récupérer.
		//
		jeu = new int[s.length][16];
		suivant = new ArrayList[s.length][];
		generateJeu(s);
		generateSuivant();
	}

	public static int[][] getJeu() {
		return jeu;
	}

	public static ArrayList<Integer>[][] getSuivant() {
		return suivant;
	}

	// A partir d'ici, tout est privé

	private static void generateJeu(int[][] s) {
		for (int i = 0; i < jeu.length; i++) {
			int[][] temp = generateCombinaisons(s[i][0], i, s[i][8], s[i][1]);
			for (int[] cas : temp) {
				if (Regle.noteCorrect(cas))
					addJeu(i, Encoder.encoder(cas));
			}
		}
	}

	private static void generateSuivant() {
		for (int i = 0; i < jeu.length; i++)
			generateNext(jeu[i], i, getLast(jeu[i]));
	}

	private static int[][] generateCombinaisons(int note, int index,
			int accord, int duree) {
		int[][] ret = new int[4][];
		int[] temp = new int[9];
		int i = 0;
		for (int[] cas : Accords.getCombinaisons(accord)) {
			temp[0] = note;
			temp[1] = duree;
			temp[2] = cas[0];
			temp[3] = duree;
			temp[4] = cas[1];
			temp[5] = duree;
			temp[6] = Accords.getTonique(accord);
			temp[7] = duree;
			temp[8] = accord;
			ret[i] = temp;
			i++;
		}
		return ret;
	}

	private static void generateNext(int[] noteC, int index, int pos) {
		int[][] comb = generateCombinaisons(noteC[0], index, noteC[8], noteC[1]);
		for (int[] noteS : comb)
			if (appartiens(Encoder.encoder(noteS), jeu[index + 1])
					&& Regle.enchainementCorrect(noteC, noteS))
				suivant[index][pos].add(getIndice(Encoder.encoder(noteS),
						jeu[index + 1]));
	}

	private static boolean appartiens(int t, int[] tab) {
		return true;
	}

	private static int getIndice(int t, int[] tab) {
		return 0;
	}

	private static void addJeu(int index, int note) {
		if (getLast(jeu[index]) < jeu[index].length)
			jeu[index][getLast(jeu[index])] = note;
		else {
			int[] temp = new int[jeu[index].length * 2];
			for (int j = 0; j < jeu[index].length; j++) {
				temp[j] = jeu[index][j];
			}
			jeu[index] = temp;
		}
	}

	private static int getLast(int[] tab) {
		int j = 0;
		while (j < tab.length && tab[j] != 0)
			j++;
		return j;
	}
}
