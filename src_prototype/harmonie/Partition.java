package harmonie;

import java.util.*;

public class Partition {

	private ArrayList<Accord> [] jeu;
	
	

	public void generate(Accord[] s) {
		// Génère tous les cas possibles de partitions suivant le schéma du
		jeu = new ArrayList [s.length];
		generateFirst(s);
		generateJeu(s);
	}

	/*public Accord [] getJeu() {
		return;
	}*/

	// A partir d'ici, tout est privé
	
	
	
	private void generateJeu(Accord[] s) {
		for (int i = 0; i < jeu.length-1; i++) {
			Accord[] temp = generateCombinaisons();
			for (Accord cas : temp) {
				if (Regle.noteCorrect(cas)){
					
				}
			}
		}
	}
}
