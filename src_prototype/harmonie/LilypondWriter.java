package harmonie;

import java.io.*;

public class LilypondWriter {
	public static void LilyWriter(String nomDuFichier, Accord part[],
			String titre) throws IOException {
		File file = new File(nomDuFichier);
		FileWriter fw = new FileWriter(file);
		fw.write("\\header{\n title = \"" + titre
				+ "\"\n}\n\\new ChoirStaff\n<<");
		for (int i = 0; i < 4; i++) {
			String s=new String();
			switch (i) {
			case (0):
				fw.write("% Soprano\n ");
				fw.write("\\new Staff { \n");
				fw.write("\\set Staff.instrumentName = #\"Soprano \" \n");
				fw.write("\\clef treble \n");
				fw.write("\\relative c''{\n");
				break;
			case (1):
				fw.write("% Alto\n");
				fw.write("\\new Staff { \n");
				fw.write("\\set Staff.instrumentName = #\"Alto \" \n");
				fw.write("\\clef treble \n");
				fw.write("\\relative c'' {\n");
				break;
			case (2):
				fw.write("% Tenor\n");
				fw.write("\\new Staff { \n");
				fw.write("\\set Staff.instrumentName = #\"Tenor \" \n");
				fw.write("\\clef treble\n");
				fw.write("\\relative c' { \n");
				break;
			case (3):
				fw.write("% Basse\n");
				fw.write("\\new Staff { \n");
				fw.write("\\set Staff.instrumentName = #\"Basse \" \n");
				fw.write("\\clef bass\n");
				fw.write("\\relative c {\n");
				break;
			}
			for(int j=0;j<part.length;j++){
				s+=noteToString(part[j],i)+" ";
			}
			fw.write(s);
			fw.write("}}\n");
		}
		fw.write(">>\n");
		fw.write("\\version \"2.14.2\"");
		fw.close();
	}
	
	/**
	 * Retourne la note en notation anglaise avec les indication d'octave par rapport a la note relative
	 * cf:doc lilypond
	 * @param ac accord dont on extrait la note
	 * @param mode choix de la note a extraire 0=soprano, 1=alto, 2=tenor, 3=basse
	 * @return 
	 */
	public static String noteToString (Accord ac,int mode){
	int note=0;
	int ref = 0;
	String s=new String();
	switch(mode){
		case(0):
			note=ac.getSoprano();
			ref=3;
			break;
		case(1):
			note=ac.getAlto();
			ref=2;
			break;
		case(2):
			note=ac.getTenor();
			ref=2;
			break;
		case(3):
			note=ac.getBasse();
			ref=1;
			break;
	}
	switch(note%7){
	case(0):
		s+="c";
		break;
	case(1):
		s+="d";
		break;
	case(2):
		s+="e";
		break;
	case(3):
		s+="f";
		break;
	case(4):
		s+="g";
		break;
	case(5):
		s+="a";
		break;
	case(6):
		s+="b";
		break;
	}
	s+=ac.getDuree();
	if((note/7)>ref){
		for(int i=0;i<((note/7)-ref);i++){
			s+="'";
		}
	}else{
		for(int i=0;i<(ref-(note/7));i++){
			s+=",";
		}
	}
	return s;
	}
}
