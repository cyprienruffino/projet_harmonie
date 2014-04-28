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
	
	public MonteurTempsVide(ArrayList<Accord> preced, Accord current){
		this.preced=preced;
		this.current=current;
		this.combinaisons=new ArrayList<Accord>();
	}
	/**
	 * Retourne les combinaisons d'accords
	 */
	public ArrayList<Accord> getCombinaisons() {
		return combinaisons;
	}
	/**
	 * Crée les combinaisons d'accords sur un silence
	 */

	public void monterCombinaisons() {
		initTpsVide(current.duree);
		int i = combinaisons.size();
		genBasse(i);
		cleanGen(i);
		i = combinaisons.size();
		genAltoVide(i);
		cleanGen(i);
		i = combinaisons.size();
		genTenorVide(i);
		cleanGen(i);
		Iterator<Accord> it = combinaisons.iterator();
		Accord ac;
		while (it.hasNext()) {
			ac = it.next();
			if (!Accord.noteCorrect(ac))
				it.remove();
		}
	}

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

	private void initTpsVide(int dur) {
		Iterator<Accord> it = preced.iterator();
		Accord[] accord = { new Accord(-1, 0, 0, 0, dur,0), new Accord(-1, 0, 0, 0, dur,1),
				new Accord(-1, 0, 0, 0, dur,2), new Accord(-1, 0, 0, 0, dur,3),
				new Accord(-1, 0, 0, 0, dur,7), new Accord(-1, 0, 0, 0, dur,4),
				new Accord(-1, 0, 0, 0, dur,5), new Accord(-1, 0, 0, 0, dur,6) };
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

	private void cleanGen(int i) {
		for (int j = 0; j < i; j++) {
			combinaisons.remove(0);
		}
	}
}
