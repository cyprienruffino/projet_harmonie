package main;

import java.util.Iterator;

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
