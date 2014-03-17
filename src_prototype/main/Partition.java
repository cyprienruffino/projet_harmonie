package main;

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
	private void generateFirst(Accord [] s){
		jeu=generateCombinaisons(s[0]);
	}
	
	
	private void generateJeu(Accord[] s) {
		for (int i = 0; i < jeu.length-1; i++) {
			for(Accord accord : s[i]){
				ArrayList<Accord> temp = generateCombinaisons(s[i],s[i+1]);
				for (Accord cas : temp) {
					accord.addJeuSuivant(cas);
					if(s[i+1]==null)
						s[i+1]=cas;
					else s[i+1].addSuivant(cas);
				}
			}	
		}
	}
}
