package monteur;

import java.util.ArrayList;
import java.util.Iterator;

import main.*;

/**
 * Monteur de combinaisons d'accords sur un silence
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 * 
 */
public class MonteurTempsVide implements Monteur {
	ArrayList<Accord> combinaisons;
	ArrayList<Accord> preced;
	Accord current;

	/**
	 * Constructeur
	 * 
	 * @param current
	 * @param preced
	 */

	public MonteurTempsVide(ArrayList<Accord> preced, Accord current) {
		this.preced = preced;
		this.current = current;
		this.combinaisons = new ArrayList<Accord>();
	}

	/**
	 * Retourne les combinaisons d'accords
	 */
	public ArrayList<Accord> getCombinaisons() {
		return combinaisons;
	}

	/**
	 * Créer tout les accords possibles quand la soprano est in silence Crée les
	 * accords en générant les notes champ par champ
	 */

	public void monterCombinaisons() {
		initTpsVide(current.duree);// Initialise combinaison avec des accords ne
									// contenant que la durée et le type en
									// fonction des accords du temps precédent
		int i = combinaisons.size();
		genBasse(i);// Génére toute les possibilité de Basse en fonction des
					// accords séléctionés a l'initialisation

		cleanGen(i);// Suprimme les accords crées par l'initialisation
		i = combinaisons.size();
		genAltoVide(i);// Génére les possibilité d'alto en fonction des accords
						// généré precedement

		cleanGen(i);// Suprimmes les accords ajoutés lors de la génération de la
					// basse
		i = combinaisons.size();
		genTenorVide(i);// Génére les possibilité de tenor en fonction des
						// accords généré precedement
		
		cleanGen(i);//Suprimme les accords ajoutés lors de la génération de l'alto
		Iterator<Accord> it = combinaisons.iterator();
		Accord ac;
		while (it.hasNext()) {//Vérifie si les accords son correct
			ac = it.next();
			if (!Accord.noteCorrect(ac))
				it.remove();
		}
	}

	/*
	 * Génére toutes les possibilité en fonction de la basse
	 */
	private void genAltoVide(int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 11; i < 22; i++) {
				Accord ac = combinaisons.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.basse) {
					if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
						ac.alto = i;
						combinaisons.add(ac);
					}
				}
			}
		}
	}

	/*
	 * Génére toutes les possibilité en fonction de la basse et de l'alto
	 */
	private void genTenorVide(int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 7; i < Math.min(19, combinaisons.get(k).alto); i++) {
				Accord ac = combinaisons.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.basse) {
					if (ac.alto % 7 == (a + 2) % 7) {
						if (i == (a + 4) % 7) {
							ac.tenor = i;
							combinaisons.add(ac);
						}
					} else if (ac.alto % 7 == (a + 4) % 7) {
						if (i % 7 == (a + 2) % 7) {
							ac.tenor = i;
							combinaisons.add(ac);
						}
					}

				}
			}
		}
	}

	/*
	 * Initialise l'Arrayliste des possibilité avec des accord vide, en fonction
	 * des Accord du temps precedent
	 */
	private void initTpsVide(int dur) {
		Iterator<Accord> it = preced.iterator();
		Accord[] accord = { new Accord(-1, 0, 0, 0, dur, 0),
				new Accord(-1, 0, 0, 0, dur, 1),
				new Accord(-1, 0, 0, 0, dur, 2),
				new Accord(-1, 0, 0, 0, dur, 3),
				new Accord(-1, 0, 0, 0, dur, 7),
				new Accord(-1, 0, 0, 0, dur, 4),
				new Accord(-1, 0, 0, 0, dur, 5),
				new Accord(-1, 0, 0, 0, dur, 6) };
		while (it.hasNext()) {
			Accord ac = it.next();
			for (int i = 0; i < accord.length; i++) {
				if (Accord.regle5(ac, accord[i])) {
					if (!contient(accord[i])) {
						combinaisons.add(accord[i]);
					}
				}
			}
		}
	}

	private boolean contient(Accord ac) {
		int a = ac.type;
		Iterator<Accord> it = combinaisons.iterator();
		while (it.hasNext()) {
			Accord acb = it.next();
			if (acb.type == a) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Genere toutes les possibilitée de basse en fonction de l'accord
	 */
	private void genBasse(int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 3; i < 16; i++) {
				Accord ac = combinaisons.get(k).clone();
				int a = ac.getTonic();
				if (i % 7 == a) {
					ac.basse = i;
					combinaisons.add(ac);
				}
			}
		}
	}

	/*
	 * suprimme les possibilité créer a l'étape precedente
	 */
	private void cleanGen(int i) {
		for (int j = 0; j < i; j++) {
			combinaisons.remove(0);
		}
	}
}
