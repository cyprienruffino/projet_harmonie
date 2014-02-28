package harmonie;

import java.util.*;

public class HarmonyGenerator { // /!\ Implémentation des enchaînements en cours!! Classe temporaire!!

	private static ArrayList<Integer>[] jeu;
	private static ArrayList<Integer>[][] suivant;
	
	public static void generate(int[][] s) {
		jeu = new ArrayList[s.length];
		suivant=new ArrayList[s.length][];
		for (int i = 0; i < jeu.length; i++)
			jeu[i] = new ArrayList(); // Initialisation de la future partition

		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < 8; j++) { // Création de la liste de notes
				if (Accords.isTierce(s[i][0], j)
						|| Accords.isQuinte(s[i][0], j)) {
					generateJeu(jeu,s[i][0], i, j, s[i][1]);
				}
			}
		}
	}
	public static int[][] generateUseablePartition(int[][]s){
		generate(s);
		int[][] temp=new int[s.length][9];
		for(int i=0;i<s.length;i++){
			temp[i]=Encoder.decode(s[i][0]);
		}
		return temp;
	}

	private static void generateJeu(ArrayList<Integer>[] t, int note, int index, int accord, int duree) {
		int[]temp=new int[9];
		for(int[]cas:Accords.getCombinaisons(accord)){
			temp[0]=note;
			temp[1]=duree;
			temp[2]=cas[0];	
			temp[3]=duree;
			temp[4]=cas[1];
			temp[5]=duree;
			temp[6]=Accords.getTonique(accord);
			temp[7]=duree;
			temp[8]=accord;
			if(Regle.noteCorrect(cas)) t[index].add(Encoder.encoder(cas));
		}
	}
	private static void generateNext(ArrayList<Integer>[] t, int note, int index, int accord, int duree){
		
	}
}
