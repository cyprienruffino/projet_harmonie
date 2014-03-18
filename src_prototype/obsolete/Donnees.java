package obsolete;

<<<<<<< HEAD:src/harmonie/Accords.java
public class Accords {
	public static int[][] accords = { { 0, 2, 4 }, { 1, 3, 5 }, { 2, 4, 6 },
			{ 3, 5, 0 }, { 3, 5, 0 }, { 4, 6, 1 }, { 5, 0, 2 }, { 6, 1, 3 } };

	// la méthode nature de la classe regle fait la même chose que les trois
	// methode suivante
	public static boolean isTonique(int note, int accord) {
		return accords[accord][0] == (note % 7);
=======
public class Donnees {
	public static int[][] accords={{0,2,4},{1,3,5},{2,4,6},{3,5,0},{3,5,0},{4,6,1},{5,0,2},{6,1,3}};
	
	//la méthode nature de la classe regle fait la même chose que les trois methode suivante
	public static boolean isTonique(int note, int accord){
		return accords[accord][0]==(note%7);
>>>>>>> 1f1caafb71df33197161d83076483ae9541c9574:src_prototype/obsolete/Donnees.java
	}

	public static boolean isTierce(int note, int accord) {
		return accords[accord][1] == (note % 7);
	}

	public static boolean isQuinte(int note, int accord) {
		return accords[accord][2] == (note % 7);
	}

	public static int getTonique(int accord) {
		return accords[accord][0];
	}

	public static int getTierce(int accord) {
		return accords[accord][1];
	}

	public static int getQuinte(int accord) {
		return accords[accord][2];
	}

	public static int[][] getCombinaisons(int accord) {
		int[][] tab = new int[16][2]; // Toutes les combinaisons ne sont pas
										// générées, mais seulement octave par
										// octave...
		for (int i = 0; i < 16; i = i + 4) {
			tab[i][0] = accords[accord][1] + (i / 4) * 7;
			tab[i][1] = accords[accord][1] + (i / 4) * 7;
			tab[i + 1][0] = accords[accord][1] + (i / 4) * 7;
			tab[i + 1][1] = accords[accord][2] + (i / 4) * 7;
			tab[i + 2][0] = accords[accord][2] + (i / 4) * 7;
			tab[i + 2][1] = accords[accord][1] + (i / 4) * 7;
			tab[i + 3][0] = accords[accord][2] + (i / 4) * 7;
			tab[i + 3][1] = accords[accord][2] + (i / 4) * 7;
		}
		return tab;
	}
}
