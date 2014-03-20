package main;

import java.util.*;
import accords.*;

public class Partition {

	private ArrayList<Accord> [] jeu;
	private ArrayList<Accord> [] soprano;
	
	public Partition(ArrayList<Accord> [] soprano) {
		this.soprano=soprano;
	}

	public void generate() {
		// Génère tous les cas possibles de partitions suivant le schéma du
		jeu = new ArrayList[soprano.length];
		generateJeu();
	}
	public int nombre(){
		generate();
		Accord pere=new I();
		//On crée un sommet virtuel qui a comme fils tous les accords du premier temps
		//Et on lance un parcours dessus
		for(Accord temp:jeu[0])
			pere.addSuivant(temp);
		return parcoursProfondeurComptage(pere)+1;
	}
		
	public ArrayList<Accord>[] getJeu() {
		return jeu;
	}

	private void generateJeu() {
		//Création de la matrice des possibilités
		for(int i=0;i<soprano.length;i++)
			jeu[i]=Regle.generateCombinaison(soprano[i].get(0));
		
		//Création récurrente des liens père-fils
		for (int i = jeu.length-1; i > 0; i--){
			for(Accord accordFils : jeu[i]){
				for(Accord accordPere : jeu[i-1]){
					//if(Regle.enchainementCorrect(accordPere, accordFils)){
						accordPere.addSuivant(accordFils);
					//}
				}
			}
		}
		//Nettoyage de la partition
		Accord temp;
		Iterator it;
		for(ArrayList<Accord>liste:jeu){
			it=liste.iterator();
				while(it.hasNext()){
					temp=(Accord) it.next();
					if(it.hasNext()&&temp.getJeuxSuivants().size()==0)
						it.remove();
				}
		}
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
