package main;


import java.util.ArrayList;

public abstract class Accord implements Cloneable{
	
	private int soprano;
	private int alto;
	private int basse;
	private int tenor;
	private int duree;
	private Accord pere;
	private int beaute;

	public void setJeuxSuivants(ArrayList<Accord> jeuxSuivants) {
		this.jeuxSuivants = jeuxSuivants;
	}

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
	public abstract int getAccord();
	
	public void addSuivant(Accord a){
		this.jeuxSuivants.add(a);
	}
	public ArrayList<Accord> getJeuxSuivants() {
		return jeuxSuivants;
	}
	public boolean isB(){
		return false;
	}
	
	public Accord(int s, int a, int t, int b, int d){
		soprano=s;
		alto=a;
		tenor=t;
		basse=b;
		duree=d;
		jeuxSuivants=new ArrayList<Accord>();
	}
	public Accord(){
		jeuxSuivants=new ArrayList<Accord>();
	}

	public boolean equals(Accord ac){
		if(this.soprano==ac.soprano)
			if(this.alto==ac.alto)
				if(this.tenor==ac.tenor)
					if(this.basse==ac.basse)
						if(this.duree==ac.duree)
							return true;
	return false;
	}
	
	public abstract Accord clone();
	
	public Accord getPere() {
		return pere;
	}
	public void setPere(Accord pere) {
		this.pere = pere;
	}
	public int getBeaute() {
		return beaute;
	}
	public void setBeaute(int beaute) {
		this.beaute = beaute;
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