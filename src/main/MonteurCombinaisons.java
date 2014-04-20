package main;

import java.util.ArrayList;

import monteur.*;

/**
 * Monte l'objet complexe Partition
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 *
 */

public class MonteurCombinaisons {
	private  Monteur monteur;
	
	public void setCombinaison(Accord preced, Accord current){
		if(current.soprano==-1){
			monteur=new MonteurTempsVide(preced,current);
		}else{
			monteur=new MonteurAccord(current);
		}
	}
	
	public ArrayList<Accord> getCombinaison(){
		return monteur.getCombinaisons();
	}
	
	public void construireCombinaison(){
		monteur.monterCombinaisons();
	}
}
