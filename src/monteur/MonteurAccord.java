package monteur;

import java.util.ArrayList;
import java.util.Iterator;

import main.*;

/**
 * Monteur de combinaisons d'accords sur une note
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 * 
 */
public class MonteurAccord implements Monteur {
	ArrayList<Accord> combinaisons;
	Accord current;

	/**
	 * Constructeur
	 * 
	 * @param current
	 */
	public MonteurAccord(Accord current) {
		this.current = current;
		combinaisons = new ArrayList<Accord>();
	}

	/**
	 * Retourne les combinaisons d'accords sur la note
	 */
	public ArrayList<Accord> getCombinaisons() {
		return combinaisons;
	}

	/**
	 * Créer tout les accords possibles en fonction de la note soprano Crée les
	 * accords en générant les notes champ par champ
	 */
	public void monterCombinaisons() {
		int s = current.soprano;
		combinaisons = initAccordcombinaisonsible(s, current.duree);
		// Ajoute les accord possible avec la note soprano
		int i = combinaisons.size();
		genBasse(i);// Génére la basse en fonction des accords crées a
					// l'initialisation

		cleanGen(i);// suprimme les accord crée lors de l'initialisation
		i = combinaisons.size();
		genAlto(i);// Génére les possibilité d'alto en fonction des accords
					// généré precedement

		cleanGen(i);// Suprimmes les accords ajoutés lors de la génération de la
					// basse
		i = combinaisons.size();
		genTenor(i);// Génére les possibilité de tenor en fonction des accords
					// généré precedement

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
	 * Initialise les accord possible en fonction de la soprano
	 */

	private ArrayList<Accord> initAccordcombinaisonsible(int s, int dur) {
		ArrayList<Accord> combinaisons = new ArrayList<Accord>();
		switch (s % 7) {
		case (0):
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 0));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 5));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 3));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 7));
			break;
		case (1):
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 1));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 4));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 6));
			break;
		case (2):
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 2));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 0));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 4));
			break;
		case (3):
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 1));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 3));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 7));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 6));
			break;
		case (4):
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 0));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 2));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 4));
			break;
		case (5):
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 1));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 3));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 7));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 5));
			break;
		case (6):
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 2));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 4));
			combinaisons.add(new Accord(s, -1, -1, -1, dur, 6));
			break;
		}
		return combinaisons;
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
 * Génére toutes les possibilité d'alto en fonction de la basse et de la soprano
 */
	private void genAlto(int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 11; i < Math.min(22, combinaisons.get(k).soprano); i++) {
				Accord ac = combinaisons.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.basse) {
					if (ac.basse % 7 == ac.soprano % 7) {
						if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
							ac.alto = i;
							combinaisons.add(ac);
						}
					} else if (i % 7 == a || i % 7 == (a + 2) % 7
							|| i % 7 == (a + 4) % 7) {
						ac.alto = i;
						combinaisons.add(ac);
					}
				}
			}
		}
	}
	/*
	 * Génére toutes les possibilité de note Tenor en fonction des autres notes
	 */
	private void genTenor(int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 7; i < Math.min(19, combinaisons.get(k).alto); i++) {
				Accord ac = combinaisons.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.basse) {
					if (ac.soprano % 7 == ac.alto % 7) {
						if (ac.soprano % 7 == (a + 2) % 7) {
							if (i % 7 == a || i % 7 == (a + 4) % 7) {
								ac.tenor = i;
								combinaisons.add(ac);
							}
						} else if (ac.soprano % 7 == (a + 4) % 7) {
							if (i % 7 == a || i % 7 == (a + 2) % 7) {
								ac.tenor = i;
								combinaisons.add(ac);
							}
						}
					} else if (ac.soprano % 7 == ac.basse % 7
							|| ac.alto % 7 == ac.basse % 7) {
						if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
							ac.tenor = i;
							combinaisons.add(ac);
						}
					} else if (i % 7 == a || i % 7 == (a + 2) % 7
							|| i % 7 == (a + 4) % 7) {
						ac.tenor = i;
						combinaisons.add(ac);
					}
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
