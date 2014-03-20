package main;


import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import writer.*;
/*
 * - 1 methode from .chant to .mid beaute random
 * - 1 methode from .chant to .ly beaute random
 * - 1 methode from .chant to .mid beaute predefinie
 * - 1 methode from .chant to .ly beaute predefinie
 * - 1 methode from .chant retourne nombre d'harmonisation totale effectuée
 */

/**
 * Analyse les options et leurs parametres dans le tableau d'arguments et execute le code adequate.
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 *
 */
public class GestionCommande {
	//J'ajoute ici les méthodes demandées
	private static void harmonieSansBeaute(File fichier, Writer writer) throws IOException, InvalidMidiDataException{
		Partition partition = new Partition(Partatab.readChant(fichier));
		partition.generate();
		writer.addPartition(partition.choixHarmonie(1));
		writer.ecrirePartition();
	}
	private static void harmonieAvecBeaute(File fichier, Writer writer, int beaute) throws IOException, InvalidMidiDataException{
		Partition partition = new Partition(Partatab.readChant(fichier));
		partition.generate();
		writer.addPartition(partition.choixHarmonie(beaute));
		writer.ecrirePartition();
	}
	private static void nombreHarmonisation(File fichier) throws IOException{
		Partition partition = new Partition(Partatab.readChant(fichier));
		partition.generate();
		partition.nombre();
	}
	
	/**
	 * Parcours le tableau d'argument et redirige vers l'execution adequate du programme. 
	 * @param String [] args
	 * @throws IOException
	 */
	public static void readCommand (String [] args){ //faire boolean pour chaque commande --> non prise en compte de l'ordre.
		boolean name=false,help=false,midi=false,lilypond=false,nombre=false,beaute=false,w=false;
		int a=0,b=0,c=0,d=0,e=0;
		
		for (int i=0;i<args.length;i++){
			if (args[i]!=null){
				if (args[i].equals("-name")){
					name=true;
				}
				if (args[i].equals("-h")){
					help=true;
				}
				if (args[i].equals("-midi")){
					midi=true;
					a=i;
				}
				if (args[i].equals("-ly")){
					lilypond=true;
					b=i;
				}
				if (args[i].equals("-nombre")){
					nombre=true;
					c=i;
				}
				if (args[i].equals("beaute")){
					beaute=true;
					d=i;
				}
				if (args[i].equals("-w")){
					w=true;
					e=i;
				}
			}
		}
		if (name){
			name();
		}
		if (help){
			help();
		}
		if (midi && beaute==false && lilypond==false){
			launchHarmonisationMidi(args,a);
		}
		if (lilypond && beaute==false && midi==false){
			launchHarmonisationLilyPond(args,b);
		}
		if (nombre){
			launchNombreHarmonisation(args,c);
		}
		if (midi && beaute){
			launchMidiBeauty(args,a,d);
		}
		if (lilypond && beaute){
			launchLilyBeauty(args,b,d);
		}
		if (w){
			launchW(args,e);
		}
	}

	/**
	 * Affiche les noms des eleves.
	 */
	public static void name(){
		System.out.println("RUFFINO Cyprien");
		System.out.println("SERRETTE Nicolas");
		System.out.println("COLOMIER Corentin");
		System.out.println("DALBIS Paul-Arthur");
	}

	/**
	 * Affiche l'aide.
	 */
	public static void help(){
		System.out.println("-h : rapelle la liste des option du programme.");
		System.out.println("-name : affiche vos noms et prenom");
		System.out.println("-midi fichier1.chant fichier2.mid : donne une harmonisation du chant contenu dans fichier1.chant sous la forme d’un fichier midi fichier2.mid.");
		System.out.println("-ly fichier1.chant fichier2.mid : donne une harmonisation du chant contenu dans fichier1.chant sous la forme d’un fichier LiLyPond fichier2.ly.");
		System.out.println("-nombre fichier1.chant : ecrit le nombre d’harmonisations du chant contenu dans fichier1.chant.");
		System.out.println("-beaute k -midi fichier1.chant fichier2.mid : donne une plus belle harmonisation du chant contenu dans fichier1.chant suivant le critere de beaute numero k (k=1..4) sous la forme d’un fichier midi fichier2.mid.");
		System.out.println("-beaute k -ly fichier1.chant fichier2.mid : donne une plus belle harmonisation du chant contenu dans fichier1.chant suivant le critere de beaute numero k (k=1..4) sous la forme d’un fichier LiLyPond fichier2.ly.");
		System.out.println("-w dossier1 dossier2 : donne le resultat des calculs demandes precedents (3 à 7) appliques a tous les fichiers de chant du dossier dossier1 sous la forme d’un dossier dossier2 contenant les fichiers midi et LiLyPond calcules et une page html produisant un tableau qui, pour chaque chant, donne le nom, le nombre d’harmonisations et les liens sur les fichiers calcules.");
	}

	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la methode harmonisationMidi avec les parametre adequates.
	 * @param String[] args
	 * @param int a
	 */
	public static void launchHarmonisationMidi(String []args,int a){
		String nomFichierUn=args[a+1];
		String nomFichierDeux=args[a+2];
		if (nomFichierUn.endsWith(".chant") && nomFichierDeux.endsWith(".mid")){
			try {
				File f=new File(nomFichierUn);
				harmonieSansBeaute(f,new MidiWriter(nomFichierDeux,nomFichierDeux.substring(0,nomFichierDeux.length()-4)));
			} catch (IOException e) {
				System.out.println("Fichier invalide");
			} catch (InvalidMidiDataException e) {
				System.out.println("Fichier invalide");
			}
		}else{
			System.out.println("Extension invalide");
		}
	}
	
	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la methode HarmonisationLilyPond avec les parametres adequates.
	 * @param String[]args
	 * @param int b
	 * @throws InvalidMidiDataException 
	 */
	public static void launchHarmonisationLilyPond(String []args,int b){
		String nomFichierUn=args[b+1];
		String nomFichierDeux=args[b+2];
		if (nomFichierUn.endsWith(".chant") && nomFichierDeux.endsWith(".ly")){
			try {
				File f=new File(nomFichierUn);
				harmonieSansBeaute(f,new LilypondWriter(nomFichierDeux,nomFichierDeux.substring(0,nomFichierDeux.length()-3)));
			} catch (IOException e) {
				System.out.println("Fichier invalide");
			} catch (InvalidMidiDataException e) {
				System.out.println("Fichier invalide");
			}
		}else{
			System.out.println("Extension Invialide");
		}
	}
	
	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la methode nombreHarmonisation avec les parametres adequates.
	 * @param String[]args
	 * @param int c
	 */
	public static void launchNombreHarmonisation(String []args,int c){ //Compter le nombre d'harmonisation a la fin de la partition
		String nomFichier=args[c+1];
		if (nomFichier.endsWith(".chant")){
			try {
				File f=new File(nomFichier);
				nombreHarmonisation(f);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Fichier invalide");
			}
		}else{
			System.out.println("Extension invalide");
		}
	}
	
	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la methode nombreHarmonisation avec les parametres adequates.
	 * @param String[]args
	 * @param int a
	 * @param int d 
	 */
	public static void launchMidiBeauty(String []args,int a,int d){
		String nomFichierUn=args[a+1];
		String nomFichierDeux=args[a+2];
		try{
			d=Integer.parseInt(args[d+1]);
		}catch(Exception e){
			System.out.println("Ceci n'est pas un nombre");
		}
		if (nomFichierUn.endsWith(".chant")){
			File f=new File(nomFichierUn);
			try {
				harmonieAvecBeaute(f,new MidiWriter(nomFichierDeux, nomFichierDeux.substring(0,nomFichierDeux.length()-4)), d);
			} catch (IOException | InvalidMidiDataException e) {
				System.out.println("Fichier invalide");
			}//A completer-----------Penser a ajouter a nomfichierdeux un _BEAUTY
		}else{
			System.out.println("Extension Invalide");
		}
	}
	
	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la methode Lilybeauty avec les parametres adequates.
	 * @param args
	 * @param b
	 * @param d
	 */
	public static void launchLilyBeauty(String []args,int b,int d){
		String nomFichierUn=args[b+1];
		String nomFichierDeux=args[b+2];
		try{
			d=Integer.parseInt(args[d+1]);
		}catch(Exception e){
			System.out.println("Ceci n'est pas un nombre");
		}
		if (nomFichierUn.endsWith(".ly")){
			File f=new File(nomFichierUn);
			try {
				harmonieAvecBeaute(f,new LilypondWriter(nomFichierDeux, nomFichierDeux.substring(0,nomFichierDeux.length()-4)), d);
			} catch (IOException | InvalidMidiDataException e) {
				System.out.println("Fichier invalide");
			}
		}else{
			System.out.println("Extension Invalide");
		}
	}
	
	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la methode W avec les parametres adequates.
	 * @param args
	 * @param e
	 */
	public static void launchW(String []args,int e){
		String nomFichierUn=args[e+1];
		String nomFichierDeux=args[e+2];
		/*
		 * besoin des autres pour la finir.
		 * succession de toutes les autres commandes sauf help-name.
		 */
	}
}
