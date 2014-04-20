package main;


import java.util.ArrayList;

/**
 * 
 * Objet chaîné représentant un accord dans le graphe de la partition
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 *
 */
public class Accord implements Cloneable{
	
	/**
	 * Valeur de la note soprano
	 */
	public int soprano;
	/**
	 * Valeur de la note alto
	 */
	public int alto;
	/**
	 * Valeur de la note basse
	 */
	public int basse;
	/**
	 * Valeur de la note ténor
	 */
	public int tenor;
	/**
	 * Durée de l'accord
	 */
	public int duree;
	/**
	 * Numéro de l'accord
	 */
	public int type;
	
	Accord pere;
	int beaute;
	
	/**
	 * Liens vers les accords du temps suivant de l'harmonisation
	 */
	public ArrayList<Accord> jeuxSuivants;
	
	/**
	 * Constructeur d'accord
	 * @param soprano
	 * @param alto
	 * @param ténor
	 * @param basse
	 * @param durée
	 * @param type
	 */
	public Accord(int s, int a, int t, int b, int d,int c){
		soprano=s;
		alto=a;
		tenor=t;
		basse=b;
		duree=d;
		type=c;
		jeuxSuivants=new ArrayList<Accord>();
	}
	Accord(){
		jeuxSuivants=new ArrayList<Accord>();
	}

	/**
	 * Egalité de valeur de deux accords
	 * @param ac
	 * @return boolean
	 */
	public boolean equals(Accord ac){
		if(this.soprano==ac.soprano)
			if(this.alto==ac.alto)
				if(this.tenor==ac.tenor)
					if(this.basse==ac.basse)
						if(this.duree==ac.duree)
							return true;
		return false;
	}	
	public Accord clone(){
		Accord ac = new Accord();
		ac.soprano=this.soprano;
		ac.alto=this.alto;
		ac.tenor=this.tenor;
		ac.basse=this.basse;
		ac.duree=this.duree;
		ac.type=this.type;
		return ac;
	}
	public void addSuivant(Accord a){
		this.jeuxSuivants.add(a);
	}	
	public int getTonic(){
		int a = this.type;
		if(a==7){
			a=3;
		}
		return a;
	}
}