package monteur;

import java.util.ArrayList;
import java.util.Iterator;

import main.Accord;
import main.Regle;

public class MonteurAccord implements Monteur{
	ArrayList<Accord> combinaisons;
	Accord current;
	
	public MonteurAccord(Accord current){
		this.current=current;
		combinaisons=new ArrayList<Accord>();
	}

	public ArrayList<Accord> getCombinaisons() {
		return combinaisons;
	}

	public void monterCombinaisons() {
		int s = current.soprano;
		combinaisons= initAccordcombinaisonsible(s,current.duree);
		int i = combinaisons.size();
		GenBasse(i);
		cleanGen(i);
		i = combinaisons.size();
		GenAlto( i);
		cleanGen( i);
		i = combinaisons.size();
		GenTenor( i);
		cleanGen( i);
		Iterator<Accord> it = combinaisons.iterator();
		Accord ac;
		while (it.hasNext()) {
			ac = it.next();
			if (!Regle.noteCorrect(ac))
				it.remove();
		}
	}
	
	private ArrayList<Accord> initAccordcombinaisonsible(int s,int dur) {
		ArrayList<Accord> combinaisons = new ArrayList<Accord>();
		switch (s % 7) {
		case (0):
			combinaisons.add(new Accord(s, -1, -1, -1, dur,0));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,5));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,3));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,7));
			break;
		case (1):
			combinaisons.add(new Accord(s, -1, -1, -1, dur,1));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,4));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,6));
			break;
		case (2):
			combinaisons.add(new Accord(s, -1, -1, -1, dur,2));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,0));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,4));
			break;
		case (3):
			combinaisons.add(new Accord(s, -1, -1, -1, dur,1));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,3));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,7));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,6));
			break;
		case (4):
			combinaisons.add(new Accord(s, -1, -1, -1, dur,0));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,2));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,4));
			break;
		case (5):
			combinaisons.add(new Accord(s, -1, -1, -1, dur,1));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,3));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,7));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,5));
			break;
		case (6):
			combinaisons.add(new Accord(s, -1, -1, -1, dur,2));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,4));
			combinaisons.add(new Accord(s, -1, -1, -1, dur,6));
			break;
		}
		return combinaisons;
	}
	private void GenBasse(int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 3; i < 16; i++) {
				Accord ac = combinaisons.get(k).clone();
				int a = ac.getTonic();
				if (i % 7 == a) {
					ac.basse=i;
					combinaisons.add(ac);
				}
			}
		}
	}
	private void GenAlto(int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 11; i < Math.min(22, combinaisons.get(k).soprano); i++) {
				Accord ac = combinaisons.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.basse) {
					if (ac.basse % 7 == ac.soprano % 7) {
						if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
							ac.alto=i;
							combinaisons.add(ac);
						}
					} else if (i % 7 == a || i % 7 == (a + 2) % 7
							|| i % 7 == (a + 4) % 7) {
						ac.alto=i;
						combinaisons.add(ac);
					}
				}
			}
		}
	}
	
	private void GenTenor(int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 7; i < Math.min(19, combinaisons.get(k).alto); i++) {
				Accord ac = combinaisons.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.basse) {
					if (ac.soprano % 7 == ac.alto % 7) {
						if (ac.soprano % 7 == (a + 2) % 7) {
							if (i % 7 == a || i % 7 == (a + 4) % 7) {
								ac.tenor=i;
								combinaisons.add(ac);
							}
						} else if (ac.soprano % 7 == (a + 4) % 7) {
							if (i % 7 == a || i % 7 == (a + 2) % 7) {
								ac.tenor=i;
								combinaisons.add(ac);
							}
						}
					} else if (ac.soprano % 7 == ac.basse % 7
							|| ac.alto % 7 == ac.basse % 7) {
						if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
							ac.tenor=i;
							combinaisons.add(ac);
						}
					} else if (i % 7 == a || i % 7 == (a + 2) % 7
							|| i % 7 == (a + 4) % 7) {
						ac.tenor=i;
						combinaisons.add(ac);
					}
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
