package harmonie;

import java.io.*;

public class LilypondWriter {
public static void LilyWriter(String nomDuFichier,int partion[],String titre ) throws IOException{
	File file = new File(nomDuFichier);
    FileWriter fw= new FileWriter(file);
    fw.write("\\header{\n title = \""+titre+"\"\n}\n\\new ChoirStaff\n<<");
    for(int i=0;i<4;i++){
    	switch(i){
    	case(0):
    		fw.write("% Soprano\n ");
    		fw.write("\\new Staff { \n");
    		fw.write("\\set Staff.instrumentName = #\"Soprano \" \n");
    		fw.write("\\clef treble \n");
    		fw.write("\\relative c''{\n");
    		break;
    	case(1):
    		fw.write("% Alto\n");
    		fw.write("\\new Staff { \n");
    		fw.write("\\set Staff.instrumentName = #\"Alto \" \n");
    		fw.write("\\clef treble \n");
    		fw.write("\\relative c'' {\n");
    		break;
    	case(2):
    		fw.write("% Tenor\n");
    		fw.write("\\new Staff { \n");
    		fw.write("\\set Staff.instrumentName = #\"Tenor \" \n");
    		fw.write("\\clef treble\n");
    		fw.write("\\relative c' { \n");
    		break;
    	case(3):
    		fw.write("% Basse\n");
    		fw.write("\\new Staff { \n");
    		fw.write("\\set Staff.instrumentName = #\"Basse \" \n");
    		fw.write("\\clef treble\n");
    		fw.write("\\relative c {\n");
    		break;
    	}
    	
    	
    	fw.write("}}\n");
    }
    fw.write(">>");
    fw.close();
}
}
