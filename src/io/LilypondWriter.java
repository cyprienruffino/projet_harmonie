package io;

import java.io.*;

import main.Accord;

/**
 * Fabrique de méthode d'écriture en Lilypond
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 * 
 */

public class LilypondWriter extends Writer {
	private String fichier;
	private Accord part[];
	private String titre;

	public LilypondWriter(String fichier, Accord[] partition, String titre) {
		this.fichier = fichier;
		part = partition;
		this.titre = titre;
	}

	public LilypondWriter(String fichier, String titre) {
		this.fichier = fichier;
		this.titre = titre;
	}

	public void addPartition(Accord[] partition) {
		this.part = partition;
	}

	public void ecrirePartition() throws IOException {
		File file = new File(fichier);
		FileWriter fw = new FileWriter(file);
		fw.write("\\header{\n title = \"" + titre
				+ "\"\n}\n\\new ChoirStaff\n<<");
		for (int i = 0; i < 4; i++) {
			String s = new String();
			enTeteChantWriter(fw, i);
			for (int j = 0; j < part.length; j++) {
				int n = note(part[j], i);
				if (n == -1) {
					s+= "r"+Time(part[j].duree)+" ";
				} else {
					s += noteToString(n) +octref(reftest(n)) +Time(part[j].duree)
							+ " ";
				}
			}
			fw.write(s);
			fw.write("}}\n");
		}
		fw.write(">>\n");
		fw.write("\\version \"2.14.2\"");
		fw.close();
	}
	
	
	private static int reftest(int note){
		return (note/7);
	}
	
	private static String octref(int i){
		String s=new String();
		for (int j=0; j<i;j++){
			s+="'";
		}
		return s;
	}

 	private static int initRef(int mode) {
		switch (mode) {
		case (0):
			return 3;
		case (1):
			return 2;
		case (2):
			return 1;
		case (3):
			return 1;
		}
		return -1;
	}

	/**
	 * Retourne la note en notation anglaise avec les indication d'octave par
	 * rapport a la note relative cf:doc lilypond
	 * 
	 * @param ac
	 *            accord dont on extrait la note
	 * @param mode
	 *            choix de la note a extraire 0=soprano, 1=alto, 2=tenor,
	 *            3=basse
	 * @return
	 */
	private static String noteToString(int note) {
		String s = new String();
		switch (note % 7) {
		case (0):
			s += "c";
			break;
		case (1):
			s += "d";
			break;
		case (2):
			s += "e";
			break;
		case (3):
			s += "f";
			break;
		case (4):
			s += "g";
			break;
		case (5):
			s += "a";
			break;
		case (6):
			s += "b";
			break;
		}
		return s;
	}

	private static int note(Accord ac, int mode) {
		switch (mode) {
		case (0):
			return ac.soprano;
		case (1):
			return ac.alto;
		case (2):
			return ac.tenor;
		case (3):
			return ac.basse;
		}
		return -1;
	}

	private static String Time(int dur) {
		switch (dur) {
		case (1):
			return "4";
		case (2):
			return "2";
		case (3):
			return "2.";
		case (4):
			return "1";
		}
		return "";
	}

	private static String octaveRef(int note, int ref) {
		String s = new String();
		if ((note / 7) > ref) {
			for (int i = 0; i < ((note / 7) - ref); i++) {
				s += "'";
			}
		} else {
			for (int i = 0; i < (ref - (note / 7)); i++) {
				s += ",";
			}
		}
		return s;
	}

	private static int ref(String r, int ref) {
		if (r.length() != 0) {
			if (r.charAt(0) == '\'')
				return ref + r.length();
			if (r.charAt(0) == ',')
				return ref - r.length();
		}
		return ref;
	}

	private static void enTeteChantWriter(FileWriter fw, int i)
			throws IOException {
		switch (i) {
		case (0):
			fw.write("% Soprano\n ");
			fw.write("\\new Staff { \n");
			fw.write("\\set Staff.instrumentName = #\"Soprano \" \n");
			fw.write("\\clef treble \n");
		//	fw.write("\\relative c''{\n");
			fw.write("{");
			break;
		case (1):
			fw.write("% Alto\n");
			fw.write("\\new Staff { \n");
			fw.write("\\set Staff.instrumentName = #\"Alto \" \n");
			fw.write("\\clef treble \n");
		//	fw.write("\\relative c'' {\n");
			fw.write("{");
			break;
		case (2):
			fw.write("% Tenor\n");
			fw.write("\\new Staff { \n");
			fw.write("\\set Staff.instrumentName = #\"Tenor \" \n");
			fw.write("\\clef treble\n");
		//	fw.write("\\relative c' { \n");
			fw.write("{");
			break;
		case (3):
			fw.write("% Basse\n");
			fw.write("\\new Staff { \n");
			fw.write("\\set Staff.instrumentName = #\"Basse \" \n");
			fw.write("\\clef bass\n");
		//	fw.write("\\relative c {\n");
			fw.write("{");
			break;
		}
	}
}
