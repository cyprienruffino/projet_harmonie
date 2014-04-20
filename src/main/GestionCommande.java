package main;

import io.*;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;

/**
 * Analyse les options et leurs parametres dans le tableau d'arguments et execute le code adequate.
 * 
 * @author RUFINO Cyprien
 * @author SERRETTE Nicolas
 * @author COLOMIERS Corentin
 * @author DALBIS Paul-Arthur
 */
public class GestionCommande {
	


//------------------- Misc ----------------------------------------------------------------------------------------------------------------------------
	/**
	 * Parcours le tableau d'argument et redirige vers l'execution adequate du programme. 
	 * @param String [] args
	 * @throws IOException
	 */
	public static void readCommand (String [] args){
		boolean name=false,help=false,midi=false,lilypond=false,nombre=false,beaute=false,w=false;
		int a=0,b=0,c=0,d=0,e=0;
		String nomFichierUn, nomFichierDeux;
		
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
			nomFichierUn=args[a+1];
			nomFichierDeux=args[a+2];
			launchHarmonieSansBeaute(nomFichierUn, nomFichierDeux);
		}
		if (lilypond && beaute==false && midi==false){
			nomFichierUn=args[a+1];
			nomFichierDeux=args[a+2];
			launchHarmonieSansBeaute(nomFichierUn, nomFichierDeux);
		}
		if (nombre){
			nomFichierUn=args[c+1];
			launchNombreHarmonisation(nomFichierUn);
		}
		if (midi && beaute){
			nomFichierUn=args[a+1];
			nomFichierDeux=args[a+2];
			d=Integer.parseInt(args[d+1]);
			launchBeauty(nomFichierUn, nomFichierDeux, d);
		}
		if (lilypond && beaute){
			nomFichierUn=args[b+1];
			nomFichierDeux=args[b+2];
			d=Integer.parseInt(args[d+1]);
			launchBeauty(nomFichierUn, nomFichierDeux, d);
		}
		if (w){
			nomFichierUn=args[e+1];
			nomFichierDeux=args[e+2];
			launchW(nomFichierUn, nomFichierDeux);
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

	
	
	
//------------------Nombre harmonisation ----------------------------------------------------------------------------------------------------------
	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la methode nombreHarmonisation avec les parametres adequates.
	 * @param String[]args
	 * @param int c
	 */
	public static void launchNombreHarmonisation(String nomFichierUn){
		if (nomFichierUn.endsWith(".chant")){
			try {
				File f=new File(nomFichierUn);
				System.out.println("Nombre d'harmonisations possibles : "+nombreHarmonisation(f));
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Fichier invalide");
			}
		}else{
			System.out.println("Extension invalide");
		}
	}
	
	/**
	 * Retourne un entier correspondant au nombre d'harmonisations possible du fichier en parametre.
	 * @param fichier
	 * @return int partition.nombre()
	 * @throws IOException
	 */
	private static int nombreHarmonisation(File fichier) throws IOException{
		ChantReader reader=new ChantReader(fichier);
		Partition partition = new Partition(reader.readChant());
		partition.generate();
		return partition.nombre();
	}

	
//------------------------- Harmonie Sans Beaute ---------------------------------------------------------------------------------------------
	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la methode harmonisationSansBeaute avec les parametre adequates.
	 * @param String[] args
	 * @param int a
	 */
	public static void launchHarmonieSansBeaute(String nomFichierUn, String nomFichierDeux){
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
			if (nomFichierUn.endsWith(".chant") && nomFichierDeux.endsWith(".ly")) {
				try {
					File f=new File(nomFichierUn);
					harmonieSansBeaute(f,new LilypondWriter(nomFichierDeux,nomFichierDeux.substring(0,nomFichierDeux.length()-3)));
				} catch (IOException e) {
					System.out.println("Fichier invalide");
				} catch (InvalidMidiDataException e) {
					System.out.println("Fichier invalide");
				}
			}else{
				System.out.println("Extension invalide");
			}
		}
	}
	
	/**
	 * Lit le fichier .chant, creer une harmonie SANS beaute de se fichier et creer un nouveau fichier dont le nom et le type est specifie par le writer.
	 * @param fichier
	 * @param writer
	 * @throws IOException
	 * @throws InvalidMidiDataException
	 */
	private static void harmonieSansBeaute(File fichier, Writer writer) throws IOException, InvalidMidiDataException{
		ChantReader reader=new ChantReader(fichier);
		Partition partition = new Partition(reader.readChant());
		partition.generate();
		writer.addPartition(partition.choixHarmonie(1));
		writer.ecrirePartition();
	}
	
//-----------------------Harmonie AVEC Beaute --------------------------------------------------------------------------------------------------
	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la methode harmonisationSansBeaute avec les parametre adequates.
	 * @param String nomFichierUn
	 * @param String nomFichierDeux
	 * @param int d 
	 */
	public static void launchBeauty(String nomFichierUn, String nomFichierDeux, int d){
		if (nomFichierUn.endsWith(".chant")){
			File f=new File(nomFichierUn);
			try {
				harmonieAvecBeaute(f,new MidiWriter(nomFichierDeux, nomFichierDeux.substring(0,nomFichierDeux.length()-4)), d);
			} catch (IOException e){
				System.out.println("Fichier invalide");
			}catch(InvalidMidiDataException e) {
				System.out.println("Fichier invalide");
			}
		}else{
			if (nomFichierUn.endsWith(".ly")){
				File f=new File(nomFichierUn);
				try {
					harmonieAvecBeaute(f,new LilypondWriter(nomFichierDeux, nomFichierDeux.substring(0,nomFichierDeux.length()-4)), d);
				} catch (IOException  e) {
					System.out.println("Fichier lilypond invalide");
				} catch(InvalidMidiDataException e){
					System.out.println("Fichier midi invalide");
				}
			}else{
				System.out.println("Extension Invalide");
			}
		}
	}
	
	/**
	 * Lit le fichier .chant, creer une harmonie Avec beaute de se fichier et creer un nouveau fichier dont le nom et le type est specifie par le writer.
	 * @param fichier
	 * @param writer
	 * @param beaute
	 * @throws IOException
	 * @throws InvalidMidiDataException
	 */
	private static void harmonieAvecBeaute(File fichier, Writer writer, int beaute) throws IOException, InvalidMidiDataException{
		ChantReader reader=new ChantReader(fichier);
		Partition partition = new Partition(reader.readChant());
		partition.generate();
		writer.addPartition(partition.choixHarmonie(beaute));
		writer.ecrirePartition();
	}
	
	
//-------------- W -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la methode W avec les parametres adequates.
	 * @param String nomDossierUn
	 * @param String nomDossierDeux
	 * @param e
	 */
	public static void launchW(String nomDossierUn, String nomDossierDeux){
		int i=0;
		File[] tabFichier=getFilesFromFolder(nomDossierUn);
		HtmlWriter[] tab=new HtmlWriter[tabFichier.length];
		for (File fichier : tabFichier ){
			
			if (fichier.isFile()){
				try {
					HtmlWriter hw=new HtmlWriter(fichier.getName(), nombreHarmonisation(fichier),nomDossierDeux+"/hsb_"+fichier.getPath()+".mid", nomDossierDeux+"/hsb_"+fichier.getPath()+".ly", nomDossierDeux+"/hab_"+fichier.getPath()+".mid", nomDossierDeux+"/hab_"+fichier.getPath()+".ly");
					launchHarmonieSansBeaute(fichier.getPath(),nomDossierDeux+"/hsb_"+fichier.getPath()+".mid");
					launchHarmonieSansBeaute(fichier.getPath(),nomDossierDeux+"/hsb_"+fichier.getPath()+".ly");
					launchBeauty(fichier.getPath(),nomDossierDeux+"/hab_"+fichier.getPath()+".mid", 2);//nombre pris au pif
					launchBeauty(fichier.getPath(),nomDossierDeux+"/hab_"+fichier.getPath()+".ly", 2);//nombre pris au pif
					tab[i]=hw;
					i++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			HtmlWriter.htmlWrite(tab,nomDossierDeux+"/Recapitulatif.htm");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Mets les Fichiers d'un dossier dans un tableau de fichier
	 * @param String nomDossierUn
	 * @return File[] tabFichier
	 */
	public static File[] getFilesFromFolder(String nomDossierUn){
		File dossierUn=new File(nomDossierUn);
		File[] tabFichier=dossierUn.listFiles();
		return tabFichier;
	}
}
