package main;

import io.*;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Analyse les options et leurs parametres dans le tableau d'arguments et
 * execute le code adequate.
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 * 
 */
public class Commande {
	/**
	 * Parcours le tableau d'argument et redirige vers l'execution adequate du
	 * programme.
	 * 
	 * @param String
	 *            [] args
	 * @throws IOException
	 */
	public static void readCommand(String[] args) { // faire boolean pour chaque
													// commande --> non prise en
													// compte de l'ordre.
		boolean name = false, help = false, midi = false, lilypond = false, nombre = false, beaute = false, w = false;
		int a = 0, b = 0, c = 0, d = 0, e = 0;
		String nomFichierUn, nomFichierDeux;
		

		for (int i = 0; i < args.length; i++) {
			if (args[i] != null) {
				if (args[i].equals("-name")) {
					name = true;
				}
				if (args[i].equals("-h")) {
					help = true;
				}
				if (args[i].equals("-midi")) {
					midi = true;
					a = i;
				}
				if (args[i].equals("-ly")) {
					lilypond = true;
					b = i;
				}
				if (args[i].equals("-nombre")) {
					nombre = true;
					c = i;
				}
				if (args[i].equals("-beaute")) {
					beaute = true;
					d = i;
				}
				if (args[i].equals("-w")) {
					w = true;
					e = i;
				}
			}
		}
		if (name) {
			name();
		}
		if (help) {
			help();
		}
		if (midi && beaute == false && lilypond == false) {
			nomFichierUn = args[a + 1];
			nomFichierDeux = args[a + 2];
			launchHarmonisation(nomFichierUn, nomFichierDeux, getBeaute(args, d), "midi");
		}
		if (lilypond && beaute == false && midi == false) {
			nomFichierUn = args[a + 1];
			nomFichierDeux = args[a + 2];
			launchHarmonisation(nomFichierUn, nomFichierDeux, getBeaute(args, d), "lilypond");
		}
		if (nombre) {
			nomFichierUn=args[c+1];
			launchNombreHarmonisation(nomFichierUn);
		}
		if (midi && beaute) {
			nomFichierUn = args[a + 1];
			nomFichierDeux = args[a + 2];
			launchHarmonisation(nomFichierUn, nomFichierDeux, getBeaute(args, d), "midi");
		}
		if (lilypond && beaute) {
			nomFichierUn = args[a + 1];
			nomFichierDeux = args[a + 2];
			launchHarmonisation(nomFichierUn, nomFichierDeux, getBeaute(args, d), "lilypond");
		}
		if (w) {
			nomFichierUn=args[e+1];
			nomFichierDeux=args[e+2];
			launchW(nomFichierUn, nomFichierDeux);
		}
	}

	/**
	 * Affiche les noms des eleves.
	 */
	public static void name() {
		System.out.println("RUFFINO Cyprien");
		System.out.println("SERRETTE Nicolas");
		System.out.println("COLOMIER Corentin");
		System.out.println("DALBIS Paul-Arthur");
	}

	/**
	 * Affiche l'aide.
	 */
	public static void help() {
		System.out.println("-h : rapelle la liste des option du programme.");
		System.out.println("-name : affiche vos noms et prenom");
		System.out
				.println("-midi fichier1.chant fichier2.mid : donne une harmonisation du chant contenu dans fichier1.chant sous la forme d’un fichier midi fichier2.mid.");
		System.out
				.println("-ly fichier1.chant fichier2.mid : donne une harmonisation du chant contenu dans fichier1.chant sous la forme d’un fichier LiLyPond fichier2.ly.");
		System.out
				.println("-nombre fichier1.chant : ecrit le nombre d’harmonisations du chant contenu dans fichier1.chant.");
		System.out
				.println("-beaute k -midi fichier1.chant fichier2.mid : donne une plus belle harmonisation du chant contenu dans fichier1.chant suivant le critere de beaute numero k (k=1..4) sous la forme d’un fichier midi fichier2.mid.");
		System.out
				.println("-beaute k -ly fichier1.chant fichier2.mid : donne une plus belle harmonisation du chant contenu dans fichier1.chant suivant le critere de beaute numero k (k=1..4) sous la forme d’un fichier LiLyPond fichier2.ly.");
		System.out
				.println("-w dossier1 dossier2 : donne le resultat des calculs demandes precedents (3 à 7) appliques a tous les fichiers de chant du dossier dossier1 sous la forme d’un dossier dossier2 contenant les fichiers midi et LiLyPond calcules et une page html produisant un tableau qui, pour chaque chant, donne le nom, le nombre d’harmonisations et les liens sur les fichiers calcules.");
	}

	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la
	 * methode nombreHarmonisation avec les parametres adequates.
	 * 
	 * @param String
	 *            []args
	 * @param int c
	 */
	public static void launchNombreHarmonisation(String nomFichier) {
		// Compter le nombre d'harmonisation a la fin de la partition
		if (nomFichier.endsWith(".chant")) {
			try {
				File f = new File(nomFichier);
				nombreHarmonisation(f);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Fichier invalide");
			}
		} else {
			System.out.println("Extension invalide");
		}
	}

	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la
	 * methode harmonisation avec les parametres adequates.
	 * 
	 * @param String
	 *            []args
	 * @param int a
	 * @param int beaute
	 * @param String
	 *            type
	 */
	public static void launchHarmonisation(String nomFichierUn, String nomFichierDeux, int beaute,
			String type) {
		if (nomFichierUn.endsWith(".chant")) {
			File f = new File(nomFichierUn);
			try {
				Writer writer = Writer.getWriter(type, nomFichierDeux);
				harmonisation(f, writer, beaute);
			} catch (IOException e) {
				System.out.println("Fichier invalide");
			} catch (InvalidMidiDataException e) {
				System.out.println("Fichier invalide");
			}
		} else {
			System.out.println("Extension Invalide");
		}
	}

	/**
	 * Extrait les parametres des options du tableau d'arguments et appel la
	 * methode W avec les parametres adequates.
	 * 
	 * @param args
	 * @param e
	 */

	private static void harmonisation(File fichier, Writer writer, int beaute)
			throws IOException, InvalidMidiDataException {
		ChantReader reader = new ChantReader(fichier);
		Partition partition = new Partition(reader.readChant());
		partition.generate();
		writer.addPartition(partition.choixHarmonie(beaute));
		writer.ecrirePartition();
	}

	private static int nombreHarmonisation(File fichier) throws IOException {
		ChantReader reader = new ChantReader(fichier);
		Partition partition = new Partition(reader.readChant());
		partition.generate();
		int nb = partition.nombre();
		System.out.println("Nombre d'harmonisations possibles : " + nb);
		return nb;
	}

	private static int getBeaute(String[] args, int beaute) {
		try {
			return Integer.parseInt(args[beaute + 1]);
		} catch (Exception e) {
			System.out.println("Ceci n'est pas un nombre");
			return 1;
		}
	}

	public static void launchW(String nomDossierUn, String nomDossierDeux) {
		int i = 0;
		File[] tabFichier = getFilesFromFolder(nomDossierUn);
		HtmlWriter[] tab = new HtmlWriter[tabFichier.length];
		for (File fichier : tabFichier) {
			if (fichier.isFile()) {
				try {
					HtmlWriter hw = new HtmlWriter(fichier.getName(),
							nombreHarmonisation(fichier), nomDossierDeux
									+ "/hsb_" + fichier.getPath() + ".mid",
							nomDossierDeux + "/hsb_" + fichier.getPath()
									+ ".ly", nomDossierDeux + "/hab_"
									+ fichier.getPath() + ".mid",
							nomDossierDeux + "/hab_" + fichier.getPath()
									+ ".ly");

					launchHarmonisation(fichier.getPath(), nomDossierDeux
							+ "/hsb_" + fichier.getPath() + ".mid",1,"midi");

					launchHarmonisation(fichier.getPath(), nomDossierDeux
							+ "/hsb_" + fichier.getPath() + ".ly",1,"lilypond");
					tab[i] = hw;
					i++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			HtmlWriter.htmlWrite(tab, nomDossierDeux + "/Recapitulatif.htm");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mets les Fichiers d'un dossier dans un tableau de fichier
	 * 
	 * @param String
	 *            nomDossierUn
	 * @return File[] tabFichier
	 */
	public static File[] getFilesFromFolder(String nomDossierUn) {
		File dossierUn = new File(nomDossierUn);
		File[] tabFichier = dossierUn.listFiles();
		return tabFichier;
	}
}