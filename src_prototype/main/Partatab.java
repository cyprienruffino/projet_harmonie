package main;

import java.io.*;

public class Partatab {
	// pour c'est variable voir le commentaire du tableau list
	static int DO = 0;
	static int RE = 1;
	static int MI = 2;
	static int FA = 3;
	static int SOL = 4;
	static int LA = 5;
	static int SI = 6;

	public static Accord[] readChant(File f) throws IOException {
		// lecture fichier
		BufferedReader entre = new BufferedReader(new FileReader(f));
		// je ne sais pas aller direrctement a la deuxieme ligne ((((((^_^;)
		entre.readLine();

		String patt = entre.readLine();
		int n = nbnote(patt);
		// le tableau d'accords que je vai renvoié
		// si vous avez un nom de tableau plus cool il peut etre changer j'ai
		// juste mi se a quoi je pancer
		Accord[] skunk = new Accord[n];

		/*
		 * comme il y avait un pb avec l'ancien version je recre le tableau qu
		 * il y a sur la copie est j'irais chercher les valeur les variable
		 * statique sont donc la pour facilite la methode remplire je suis
		 * concien qu'il y a plus classe mais bon le tableau n'est que
		 * temporaire et de tout façon c'est que le prototype
		 */
		int[][] liste = new int[7][4];
		remplire(liste);
		/*
		 * comme le il peut y avoir des sol qui on + caractere que les autre je
		 * fait une methode qui me returne un tableau de toute les substring
		 */
		String[] isol = new String[n];
		isolement(patt,isol);
		//le plus lourd et enfin passer
		for (int i = 0; i < n; i++) {

		}
		return skunk;
	}
	
	public static void isolement(String s, String[] si) {
		int nbdesol = 0;
		int nbtmpmort = 0;
		for (int i = 0; i < si.length; i++) {
			//explication dans le comentaire suivant
			int premier = (i + 1) * (i * 6)+1 + nbdesol - nbtmpmort;
			int deuxiem = ((i + 1) * (i * 6)+1 + 6 - nbdesol - nbtmpmort);
			/*
			 * pour le if et le else if :
			 * le +1 et le -1 vien du decalage cree par le caracter en moin ou en plus
			 * pour le esle :
			 * une note est composer de 5 caracte 6 avec l'espace a la fin que
			 * je compte cepandent les temps mor le s'on de 5 tjr en comptant l'
			 * espace  et les sol de 7 dans les meme condition se que creer
			 * des decalage je suis donc obliger de les prendre en compte c'est
			 * pour sa qu'il y a le +nbdesol donc le nb de decalage de un que
			 * cree les sol de meme pour les temps mort .La iem note commence
			 * normalement au caractere en position i*6 (le nb de caractere que
			 * prene les autre note) + 1 pour avoir son premier charactere
			 */
			if(s.substring(premier,premier+1)=="sol"){
				si[i] = s.substring(premier,deuxiem+1);
				nbdesol++;
			}
			else if(s.charAt(premier)=='-'){
				si[i] = s.substring(premier,deuxiem-1);
				nbtmpmort++;
			}
			else
				si[i] = s.substring(premier,deuxiem);
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
		String note = s.substring(0, 1);
		if (note == "so")
			return tab[SOL][note.charAt(4)];
		else if (note == "si")
			return tab[SI][note.charAt(3)];
		else if (note == "fa")
			return tab[FA][note.charAt(3)];
		else if (note == "do")
			return tab[DO][note.charAt(3)];
		else if (note == "re")
			return tab[RE][note.charAt(3)];
		else if (note == "mi")
			return tab[MI][note.charAt(3)];
		else
			return tab[LA][note.charAt(3)];
	}
}