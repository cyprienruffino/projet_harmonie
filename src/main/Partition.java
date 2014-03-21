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
		//Attention, il faut avoir généré la partition!
		Accord pere=new I();
		//On crée un sommet virtuel qui a comme fils tous les accords du premier temps
		//Et on lance un parcours dessus
		for(Accord temp:jeu[0])
			pere.addSuivant(temp);
		return parcoursProfondeurComptage(pere)+1;
	}
	
	public Accord[] choixHarmonie(int beaute){
		//TODO Méthode inopérante
		Accord[]retour=new Accord[jeu.length];
		retour[0]=jeu[0].get(0);
		for(int i=0;i<retour.length-1;i++){
			retour[i+1]=retour[i].getJeuxSuivants().get(0);
		}
		return retour;
	}
	
	public ArrayList<Accord>[] getJeu() {
		return jeu;
	}

	private void generateJeu() {
		//Création de la matrice des possibilités
		for(int i=0;i<soprano.length;i++)
			jeu[i]=Regle.generateCombinaison(soprano[i].get(0));
		
		//Création récurrente des liens père-fils
		Accord temp;
		Accord accordFils;
		Iterator it;
		
		for (int i = jeu.length-1; i > 0; i--){
			it=jeu[i].iterator();
			while(it.hasNext()){
				accordFils=(Accord) it.next();
				if((i!=jeu.length-1)&&(accordFils.getJeuxSuivants().size()==0)){
					it.remove();
				}
				for(Accord accordPere : jeu[i-1]){
					if(Regle.enchainementCorrect(accordPere, accordFils)){
						accordPere.addSuivant(accordFils);
					}
				}
			}
		}
		//Nettoyage de la partition
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
