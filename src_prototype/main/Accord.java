package main;

import java.util.ArrayList;

import accords.AccordIterator;

public abstract class Accord implements Iterable<Accord>{
	
	private int soprano;
	private int alto;
	private int basse;
	private int tenor;
	private int duree;
	
	private Accord suivant;
	private ArrayList<Accord> jeuxSuivants;
	
	public int getDuree() {
		return duree;
	}
	public int getSoprano() {
		return soprano;
	}
	public int getAlto() {
		return alto;
	}
	public int getBasse() {
		return basse;
	}
	public int getTenor() {
		return tenor;
	}
	public Accord getSuivant() {
		return suivant;
	}
	public void setSuivant(Accord suivant) {
		this.suivant = suivant;
	}
	
	public void addSuivant(Accord a){
		this.suivant=(a);
	}
	public void addJeuSuivant(Accord a){
		this.jeuxSuivants.add(a);
	}
	
	public void setSoprano(int soprano) {
		this.soprano = soprano;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public void setBasse(int basse) {
		this.basse = basse;
	}
	public void setTenor(int tenor) {
		this.tenor = tenor;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public Accord(int s, int a, int b, int t, int d){
		soprano=s;
		alto=s;
		tenor=t;
		basse=b;
		duree=d;
	}
	public ArrayList<Accord> getJeuxSuivants() {
		return jeuxSuivants;
	}
	
	public AccordIterator iterator(){
		return new AccordIterator(this);
	}
	
	/*public boolean verifAccord(Accord a){
		return verifAccordLocal() && verifAccordEnchainement(a);
	}
	private boolean verifAccordLocal(){
		return Regle.verifNote(this);
	}
	private boolean verifAccordEnchainement(a){
		
	}*/
}