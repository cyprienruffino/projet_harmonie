package io;

import java.io.*;

/**
 * Permet la creation d'un fichier HTML qui recapitule la commande -w
 * 
 * @author RUFINO Cyprien
 * @author SERRETTE Nicolas
 * @author COLOMIERS Corentin
 * @author DALBIS Paul-Arthur
 */
public class HtmlWriter {
	public String nom;
	public int nbH;
	public String pathMidSb;
	public String pathLySb;
	public String pathMidAb;
	public String pathLyAb;

	/**
	 * Constructeur avec parametres
	 * 
	 * @param nom
	 * @param nbH
	 * @param pathMidSb
	 * @param pathLySb
	 * @param pathMidAb
	 * @param pathLyAb
	 */
	public HtmlWriter(String nom, int nbH, String pathMidSb, String pathLySb,
			String pathMidAb, String pathLyAb) {
		this.nom = nom;
		this.nbH = nbH;
		this.pathMidSb = pathMidSb;
		this.pathLySb = pathLySb;
		this.pathMidAb = pathMidAb;
		this.pathLyAb = pathLyAb;
	}

	/**
	 * Constructeur dans parametre
	 */
	public HtmlWriter() {
		this.nom = null;
		this.nbH = 0;
		this.pathMidSb = null;
		this.pathLySb = null;
		this.pathMidAb = null;
		this.pathLyAb = null;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNbH(int nbH) {
		this.nbH = nbH;
	}

	public void setPathMidSb(String pathMidSb) {
		this.pathMidSb = pathMidSb;
	}

	public void setPathLySb(String pathLySb) {
		this.pathLySb = pathLySb;
	}

	public void setPathMidAb(String pathMidAb) {
		this.pathMidAb = pathMidAb;
	}

	public void setPathLyAb(String pathLyAb) {
		this.pathLyAb = pathLyAb;
	}

	public String getNom() {
		return this.nom;
	}

	public int getNbH() {
		return this.nbH;
	}

	public String getPathMidSb() {
		return this.pathMidSb;
	}

	public String getPathLySb() {
		return this.pathLySb;
	}

	public String getPathMidAb() {
		return this.pathMidAb;
	}

	public String getPathLyAb() {
		return this.pathLyAb;
	}

	/**
	 * Debut d'execution de l'ecriture du fichier html.
	 * 
	 * @param tab
	 * @param path
	 * @throws IOException
	 */
	public static void htmlWrite(HtmlWriter[] tab, String path)
			throws IOException {
		File file = new File(path);
		FileWriter fw = new FileWriter(file);
		toHtml(tab, fw);
		fw.close();
	}

	/**
	 * lance les sous programmes qui ecrivent les differentes parties d'un
	 * fichier html avec les arguments adequates.
	 * 
	 * @param tab
	 * @param fw
	 * @throws IOException
	 */
	private static void toHtml(HtmlWriter[] tab, FileWriter fw)
			throws IOException {
		enTete(fw);
		body(tab, fw);
		fw.write("</html>\n");
	}

	/**
	 * Ecriture de l'en-tete du fichier html
	 * 
	 * @param fw
	 * @throws IOException
	 */
	private static void enTete(FileWriter fw) throws IOException {
		fw.write("<!DOCTYPE html> \n <html>\n");
		fw.write("<head> \n <meta charset=\"utf-8\" />\n");
		fw.write("<title>Résultat des Harmonisation</title>\n");
		fw.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n");
		fw.write("</head>\n");
	}

	/**
	 * Ecriture du corps du fichier html, lance l'execution de l'ecriture du
	 * tableau en html
	 * 
	 * @param tab
	 * @param fw
	 * @throws IOException
	 */
	private static void body(HtmlWriter[] tab, FileWriter fw)
			throws IOException {
		fw.write("<body> \n");
		toTab(tab, fw);
		fw.write("</body>\n");
	}

	/**
	 * ecriture du tableau avec les donnees adequate.
	 * 
	 * @param tab
	 * @param fw
	 * @throws IOException
	 */
	private static void toTab(HtmlWriter[] tab, FileWriter fw)
			throws IOException {
		fw.write("<table>\n");
		fw.write("<caption>Liste des Harmonisation</caption>\n");
		fw.write("<tr>\n");
		fw.write("<th>Chant</th> <th>Nombre d'Harmonistation</th> <th>Fichier Midi</th> <th>Fichier LilyPond</th>\n");
		fw.write("</tr>\n");
		for (int i = 0; i < tab.length; i++) {
			fw.write("<tr>\n");
			fw.write("<th>" + tab[i].nom + "</th>");
			fw.write("<td>" + tab[i].nbH + "</td>");
			fw.write("<td><a href=\"" + tab[i].pathLySb + "\"> " + tab[i].nom
					+ ".mid</a></td>");
			fw.write("<td><a href=\"" + tab[i].pathMidSb + "\"> " + tab[i].nom
					+ ".mid</a></td>");
			fw.write("<td><a href=\"" + tab[i].pathLyAb + "\"> " + tab[i].nom
					+ ".mid</a></td>");
			fw.write("<td><a href=\"" + tab[i].pathMidAb + "\"> " + tab[i].nom
					+ ".mid</a></td>");
			fw.write("</tr>\n");
		}
	}

}