package harmonie;

public class Accords {
	public static int[][] accords={{0,2,4},{1,3,5},{2,4,6},{3,5,0},{3,5,0},{4,6,1},{5,0,2},{6,1,3}};
	
	//la méthode nature de la classe regle fait la même chose que les trois methode suivante
	public static boolean isTonique(int note, int accord){
		return accords[accord][0]==(note%7);
	}
	public static boolean isTierce(int note, int accord){
		return accords[accord][1]==(note%7);
	}
	public static boolean isQuinte(int note, int accord){
		return accords[accord][2]==(note%7);
	}
	
	public static int getTonique(int accord){
		return accords[accord][0];
	}
	public static int getTierce(int accord){
		return accords[accord][1];
	}
	public static int getQuinte(int accord){
		return accords[accord][2];
	}
	
	public static int[][] getCombinaisons(int accord){
		int[][] tab={{accords[accord][1],accords[accord][1]},{accords[accord][1],accords[accord][2]},{accords[accord][2],accords[accord][1]},{accords[accord][2],accords[accord][2]}};
		return tab;
	}
}
