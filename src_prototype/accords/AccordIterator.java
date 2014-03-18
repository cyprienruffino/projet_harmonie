package accords;

import java.util.Iterator;

import main.Accord;


public class AccordIterator implements Iterator {

	private Accord accord;
	
	public boolean hasNext() {
		return accord.getSuivant()!=null;
	}

	public Accord next() {
		accord=accord.getSuivant();
		return accord;
	}

	public void remove() {
		accord.setSuivant(accord.getSuivant().getSuivant());
	}

	public AccordIterator(Accord a){
		accord=a;
	}
}
