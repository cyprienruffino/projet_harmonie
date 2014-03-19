package main;

import java.util.*;
import accords.*;

public class Partition {

	private ArrayList<Accord> [] jeu;

	public void generate(ArrayList<Accord>[] s) {
		// Génère tous les cas possibles de partitions suivant le schéma du
		jeu = new ArrayList[s.length];
		generateJeu(s);
	}
	public int nombre(ArrayList<Accord>[] s){
		generate(s);
		Accord pere=new I();
		//On crée un sommet virtuel qui a comme fils tous les accords du premier temps
		//Et on lance un parcours dessus
		for(Accord temp:jeu[0])
			pere.addSuivant(temp);
		return parcoursProfondeurComptage(pere)+1;
	}
		
	private void generateJeu(ArrayList<Accord>[] partition) {
		//Création de la matrice des possibilités
		for(int i=0;i<partition.length;i++)
			partition[i]=Regle.generateCombinaison(partition[i].get(0));
		
		//Création récurrente des liens père-fils
		for (int i = partition.length-1; i >= 0; i--){
			for(Accord accordFils : partition[i]){
				for(Accord accordPere : partition[i-1]){
					if(Regle.enchainementCorrect(accordPere, accordFils)){
						accordPere.addSuivant(accordFils);
					}
				}
				//Purge dynamique des accords inutiles
				if((i<partition.length-1)&&(accordFils.getJeuxSuivants().size()==0)){
					partition[i].remove(accordFils);
				}
			}
		}
		//Nettoyage du premier temps
		for(Accord accord : partition[0])
			if(accord.getJeuxSuivants().size()==0)
				partition[0].remove(accord);
	}
	
	private int parcoursProfondeurComptage(Accord s){
		s.setMarque(true);
		int compteur=(s.getJeuxSuivants().size())-1;
		for(Accord fils:s.getJeuxSuivants()){
			if(!fils.isMarque()){
				compteur+=parcoursProfondeurComptage(fils);
			}
		}
		return compteur;
	}
}
