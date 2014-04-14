package main;


import java.util.ArrayList;

public class Accord implements Cloneable{
	
	private int soprano;
	private int alto;
	private int basse;
	private int tenor;
	private int duree;
	private Accord pere;
	private int beaute;
	private ArrayList<Accord> jeuxSuivants;
	private int type;
		
	public void setJeuxSuivants(ArrayList<Accord> jeuxSuivants) {
		this.jeuxSuivants = jeuxSuivants;
	}
	
	public int getType(){
		return type;
	}
	
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
	
	public void setType(int type){
		this.type=type;
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
	
	public void addSuivant(Accord a){
		this.jeuxSuivants.add(a);
	}
	public ArrayList<Accord> getJeuxSuivants() {
		return jeuxSuivants;
	}
	public boolean isB(){
		return false;
	}
	
	public Accord(int s, int a, int t, int b, int d,int c){
		soprano=s;
		alto=a;
		tenor=t;
		basse=b;
		duree=d;
		type=c;
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
	
	public Accord clone(){
		Accord ac = new Accord();
		ac.setSoprano(this.getSoprano());
		ac.setAlto(this.getAlto());
		ac.setTenor(this.getTenor());
		ac.setBasse(this.getBasse());
		ac.setDuree(this.getDuree());
		ac.setType(this.getType());
		return ac;
		
	}
	
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
	
	public int getTonic(){
		int a = this.getType();
		if(a==7){
			a=3;
		}
		return a;
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