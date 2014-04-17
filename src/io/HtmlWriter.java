package io;

import java.io.*;

public class HtmlWriter {
	public String nom;
	public int nbH;
	public String pathMid;
	public String pathLy;


	public HtmlWriter(String nom, int nbH, String pathMid, String pathLy) {
		this.nom = nom;
		this.nbH = nbH;
		this.pathMid = pathMid;
		this.pathLy = pathLy;
	}

	public void htlmWrite(HtmlWriter[] tab) throws IOException {
		File file = new File("Harmonisation.html");
		FileWriter fw = new FileWriter(file);
		toHtml(tab,fw);
		fw.close();
	}

	private void enTete(FileWriter fw) throws IOException {
		fw.write("<!DOCTYPE html> \n <html>\n");
		fw.write("<head> \n <meta charset=\"utf-8\" />\n");
		fw.write("<title>Résultet des Harmonisation</title>\n");
		fw.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n");
		fw.write("</head>\n");
	}

	private void body(HtmlWriter[] tab,FileWriter fw) throws IOException{
		fw.write("<body> \n");
		toTab(tab,fw);
		fw.write("</body>\n");
	}
	
	private void toTab (HtmlWriter[] tab,FileWriter fw) throws IOException{
		fw.write("<table>\n");
		fw.write("<caption>Liste des Harmonisation</caption>\n");
		fw.write("<tr>\n");
		fw.write("<th>Chant</th> <th>Nombre d'Harmonistation</th> <th>Fichier Midi</th> <th>Fichier LilyPond</th>\n");
		fw.write("</tr>\n");
		for(int i=0;i<tab.length;i++){
			fw.write("<tr>\n");
			fw.write("<th>"+tab[i].nom+"</th>");
			fw.write("<td>"+tab[i].nbH+"</td>");
			fw.write("<td>"+tab[i].pathMid+"</td>");
			fw.write("<td><a href=\""+tab[i].pathLy+"\"> "+tab[i].nom+".mid</a></td>");
			fw.write("<td><a href=\""+tab[i].pathMid+"\"> "+tab[i].nom+".mid</a></td>");
			fw.write("</tr>\n");
		}
	}

	private void toHtml(HtmlWriter[] tab,FileWriter fw) throws IOException{
		enTete(fw);
		body(tab,fw);
		fw.write("</html>\n");
	}
}
