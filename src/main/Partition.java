package main;


import java.util.*;



public class Partition {

	private ArrayList<Accord> [] jeu;
	private ArrayList<Accord> [] soprano;
	
	public Partition(ArrayList<Accord> [] soprano) {
		this.soprano=soprano;
	}
	public ArrayList<Accord>[] getJeu(){
		return jeu;
	}

	public void generate() {
		// Génère tous les cas possibles de partitions suivant le schéma du
		jeu = new ArrayList[soprano.length];
		generateJeu();
	}
	public int nombre(){
		//Attention, il faut avoir généré la partition!
		Accord pere=new Accord();
		//On crée un sommet virtuel qui a comme fils tous les accords du premier temps
		//Et on lance un parcours dessus
		for(Accord temp:jeu[0])
			pere.addSuivant(temp);
		return 1+parcoursProfondeurComptage(pere);
	}
	
	public Accord[] choixHarmonie(int beaute){
		switch(beaute){
			case 1: return choixPremier();
			case 2: return choixLocal(); //Recherche de la plus grande beauté locale
			case 3: elaguer(); return choixLocal(); //Recherche de la plus grande beauté locale en parcourant de droite a gauche pour faire un premier tri
			case 4: elaguer(); return choixGlobal();//Recherche de la plus grande beauté globale
		}
		return null;
	}
	
	
	private void generateJeu() {
		//Création de la matrice des possibilités
		jeu[0]=Regle.generate(null,soprano[0].get(0));
		for(int i=1;i<soprano.length;i++)
			jeu[i]=Regle.generate(jeu[i-1],soprano[i].get(0));
		
		//Création récurrente des liens père-fils
		Accord accordFils;
		
		for (int i = jeu.length-1; i > 0; i--){
			for(int j=0;j<jeu[i].size();j++){
				accordFils=jeu[i].get(j);
				if((i!=jeu.length-1)&&(accordFils.jeuxSuivants.size()==0)){
					jeu[i].remove(accordFils);
					j--;
					continue;
				}
				for(Accord accordPere : jeu[i-1]){
					if(Regle.enchainementCorrect(accordPere, accordFils)){
						accordPere.addSuivant(accordFils);
					}
				}
			}
		}
		//Nettoyage de la première case de la partition
		for(int j=0;j<jeu[0].size();j++){
			accordFils=jeu[0].get(j);
			if(accordFils.jeuxSuivants.size()==0){
				jeu[0].remove(accordFils);
			}
		}
	}
	private Accord[] choixPremier(){
		Accord[]retour=new Accord[jeu.length];
		retour[0]=jeu[0].get(0);
		for(int i=0;i<retour.length-1;i++){
			retour[i+1]=retour[i].jeuxSuivants.get(0); //On prends le premier chemin
		}
		return retour;
	}
	
	private Accord[] choixLocal(){
		Accord[]retour=new Accord[jeu.length];
		retour[0]=jeu[0].get(0);
		int max;
		for(int i=0;i<retour.length-1;i++){
			max=0;
			for(int j=0;j<retour[i].jeuxSuivants.size();j++){
				if(beaute(retour[i],retour[i].jeuxSuivants.get(j))>beaute(retour[i],retour[i].jeuxSuivants.get(max)));
					max=j;
			}
			retour[i+1]=retour[i].jeuxSuivants.get(max);
		}
		return retour;
	}
	
	private Accord[] choixGlobal(){
		Accord pere=pere();
		ArrayList<Accord> dernier=jeu[jeu.length-1];
		Accord[] retour=new Accord[jeu.length];
		parcoursProfondeurBeaute(pere,0);
		int max=0;
		for(int i=0;i<dernier.size();i++){
			if(dernier.get(i).beaute>dernier.get(max).beaute);
				max=i;
		}
		Accord chemin=dernier.get(max);
		for(int i=dernier.size()-1;i>0;i--){
			retour[i]=chemin;
			chemin=chemin.pere;
		}
		return retour;
	}
	
	private void elaguer(){
		int max;
		ArrayList<Accord> fils;
		ArrayList<Accord> temps;
		for(int i=jeu.length-2;i>0;i--){
			temps=new ArrayList<Accord>();
			for(int j=0;j<jeu[i].size();j++){
				max=0;
				fils=new ArrayList<Accord>();
				for(int k=0;k<jeu[i].get(j).jeuxSuivants.size();k++){
					if(beaute(jeu[i].get(j),jeu[i].get(j).jeuxSuivants.get(k))>beaute(jeu[i].get(j),jeu[i].get(j).jeuxSuivants.get(max)));
						max=k;
				}
				jeu[i].get(j).jeuxSuivants.get(max).pere=jeu[i].get(j);
				fils.add(jeu[i].get(j).jeuxSuivants.get(max));
				temps.add(jeu[i].get(j).jeuxSuivants.get(max));
				jeu[i].get(i).jeuxSuivants=fils;
			}
			jeu[i]=temps;
		}
	}
	
	private int beaute(Accord pere, Accord fils){
		return crit1(pere,fils)+crit2(pere,fils)+crit3(pere,fils);
	}
	
	private int crit1(Accord p,Accord f){
		return 28-Math.abs(Math.abs(p.alto-p.tenor)+Math.abs(f.alto-f.tenor));
	}
	
	private int crit2(Accord p,Accord f){
		return (7-Math.abs(f.alto-p.alto)+(7-Math.abs(f.tenor-p.tenor)));
	}
	
	private int crit3(Accord p,Accord f){
		return 12-Math.abs(p.basse-f.basse);
	}
	
	
	private Accord pere(){
		Accord pere=new Accord();
		for(Accord temp:jeu[0])
			pere.addSuivant(temp);
		return pere;
	}
	
	private int parcoursProfondeurComptage(Accord s){
		int compteur=(s.jeuxSuivants.size());
		if(compteur>0)
			compteur--;
		for(Accord fils:s.jeuxSuivants)
			compteur+=parcoursProfondeurComptage(fils);
		return compteur;
	}
	private void parcoursProfondeurBeaute(Accord pere, int compteur){
		for(Accord fils:pere.jeuxSuivants){
			pere.beaute=(beaute(pere,fils));
			parcoursProfondeurBeaute(fils, pere.beaute);
		}
	}
}
