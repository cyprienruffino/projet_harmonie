package main;

import java.util.*;

/**
 * Classe instanciant la partition et contenant les algorithmes de graphes pour
 * la génération de l'harmonisation
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 * 
 */

public class Partition {

	// Graphe indexé contenant l'harmonisation générée
	private ArrayList<Accord>[] jeu;

	// Graphe indexé linéaire contenant la parition soprano donnée
	private ArrayList<Accord>[] soprano;

	/**
	 * Constructeur de partition
	 * 
	 * @param soprano
	 *            La partition soprano d'entrée, sous forme de graphe indexé
	 */
	public Partition(ArrayList<Accord>[] soprano) {
		this.soprano = soprano;
	}

	/**
	 * Génère toutes les harmonisations possibles
	 */
	public void generate() {
		// Génère tous les cas possibles de partitions suivant le schéma du
		jeu = new ArrayList[soprano.length];
		generateJeu();
	}

	/**
	 * Calcule le nombre d'harmonisations totales
	 * 
	 * @return nombre Nombre d'harmonisations total
	 */
	public int nombre() {
		// Attention, il faut avoir généré la partition!
		Accord pere = pere();
		// On crée un sommet virtuel qui a comme fils tous les accords du
		// premier temps
		// Et on lance un parcours dessus
		return 1 + parcoursProfondeurComptage(pere);
	}

	/**
	 * Choisis selon la beauté donnée l'harmonisation qui convient.
	 * 
	 * @param beaute
	 *            Entier entre 1 et 4
	 * @return partition
	 */
	public Accord[] choixHarmonie(int beaute) {
		switch (beaute) {
		case 1:
			return choixPremier(); // Séléction de la première harmonisation
									// possible
		case 2:
			return choixLocal(); // Recherche de la plus grande beauté locale
		case 3:
			elaguer();
			return choixLocal(); // Recherche de la plus grande beauté locale en
									// faisant un premier tri
		case 4:
			elaguer();
			return choixGlobal();// Recherche de la plus grande beauté globale
		}
		return choixPremier();
	}

	
	
	/*
	 * C'est normale que ca ne marche pas il ne faut donner l'ArrayListe des Accord i-1 et non la note qui a per mis de les générer
	 */
	private void generateJeu() {
		// Création de la matrice des possibilités
		MonteurCombinaisons builder = new MonteurCombinaisons();
		builder.setCombinaison(null, soprano[0].get(0));
		builder.construireCombinaison();
		jeu[0] = builder.getCombinaison();
		for (int i = 1; i < soprano.length; i++) {
			builder.setCombinaison(jeu[i - 1], soprano[i].get(0));//Voila le probléme!!
			builder.construireCombinaison();
			jeu[i] = builder.getCombinaison();
		}

		// Création des liens père-fils
		Accord accordFils;
		for (int i = jeu.length - 1; i > 0; i--) {
			for (int j = 0; j < jeu[i].size(); j++) {
				accordFils = jeu[i].get(j);
				// Nettoyage des chemins sans suite 
				if ((i != jeu.length - 1)
						&& (accordFils.jeuxSuivants.size() == 0)) {
					jeu[i].remove(accordFils);
					j--;
					continue;
				}
				//Vérification des règles
				for (Accord accordPere : jeu[i - 1]) {
					if (Accord.enchainementCorrect(accordPere, accordFils)) {
						accordPere.addSuivant(accordFils);
					}
				}
			}
		}
		// Nettoyage de la première case de la partition
		for (int j = 0; j < jeu[0].size(); j++) {
			accordFils = jeu[0].get(j);
			if (accordFils.jeuxSuivants.size() == 0) {
				jeu[0].remove(accordFils);
			}
		}
	}

	private Accord[] choixPremier() {
		// Choisis la première harmonisation
		Accord[] retour = new Accord[jeu.length];
		retour[0] = jeu[0].get(0);
		for (int i = 0; i < retour.length - 1; i++) {
			retour[i + 1] = retour[i].jeuxSuivants.get(0);
		}
		return retour;
	}

	private Accord[] choixLocal() {
		// A chaque temps, prends l'enchaînement le plus beau
		Accord[] retour = new Accord[jeu.length];
		retour[0] = jeu[0].get(0);
		int max;
		for (int i = 0; i < retour.length - 1; i++) {
			max = 0;
			for (int j = 0; j < retour[i].jeuxSuivants.size(); j++) {
				if (beaute(retour[i], retour[i].jeuxSuivants.get(j)) > beaute(
						retour[i], retour[i].jeuxSuivants.get(max)))
					;
				max = j;
			}
			retour[i + 1] = retour[i].jeuxSuivants.get(max);
		}
		return retour;
	}

	private Accord pere() {
		// Crée un faux accord père de tous les accords du premier temps
		Accord pere = new Accord();
		for (Accord temp : jeu[0])
			pere.addSuivant(temp);
		return pere;
	}

	private Accord[] choixGlobal() {
		// Choisis la plus belle harmonisation possible
		Accord pere = pere();
		ArrayList<Accord> dernier = jeu[jeu.length - 1];
		Accord[] retour = new Accord[jeu.length];
		// On applique un parcours en profondeur pour trouver les beautés
		// totales des chemins
		parcoursProfondeurBeaute(pere, 0);
		int max = 0;
		// On choisis le plus beau chemin
		for (int i = 0; i < dernier.size(); i++) {
			if (dernier.get(i).beaute > dernier.get(max).beaute)
				max = i;
		}
		// On le remonte pour retrouver toute l'harmonisation
		Accord chemin = dernier.get(max);
		for (int i = jeu.length - 1; i > 0; i--) {
			retour[i] = chemin;
			chemin = chemin.pere;
		}
		return retour;
	}

	private void elaguer() { 
		// Coupe les liens inutiles, ne gardant pour chaque accord que le plus beau père
		for (int i = 1; i < jeu.length; i++) {
			for (Accord pere : jeu[i - 1]) {
				for (Accord fils : pere.jeuxSuivants) {
					if (fils.pere == null
						|| beaute(fils.pere, fils) > beaute(pere, fils))
						fils.pere = pere;
				}	
			}
		}
		for (ArrayList<Accord> index : jeu) {
			for (Accord accord : index) {
				accord.jeuxSuivants = new ArrayList<Accord>();
			}
		}
		for (int i = 1; i < jeu.length; i++) {
			for (int j = 0; j < jeu[i].size(); j++) {
				jeu[i].get(j).pere.jeuxSuivants.add(jeu[i].get(j));
			}
		}
	}

	/*
	 * private void elaguer() { int max; ArrayList<Accord> temps;
	 * ArrayList<Accord> fils; for (int i = jeu.length - 2; i > 0; i--) { temps
	 * = new ArrayList<Accord>(); for (int j = 0; j < jeu[i].size(); j++) { max
	 * = 0; fils = new ArrayList<Accord>(); for (int k = 0; k <
	 * jeu[i].get(j).jeuxSuivants.size(); k++) { if (beaute(jeu[i].get(j),
	 * jeu[i].get(j).jeuxSuivants.get(k)) > beaute( jeu[i].get(j),
	 * jeu[i].get(j).jeuxSuivants.get(max))) { max = k; } }
	 * jeu[i].get(j).jeuxSuivants.get(max).pere = jeu[i].get(j);
	 * fils.add(jeu[i].get(j).jeuxSuivants.get(max));
	 * temps.add(jeu[i].get(j).jeuxSuivants.get(max));
	 * jeu[i].get(j).jeuxSuivants = fils; } jeu[i] = temps; } for(int i=0;){
	 * 
	 * } }
	 */

	private int beaute(Accord pere, Accord fils) {
		// Calcule la beauté pour un enchaînement
		return crit1(pere, fils) + crit2(pere, fils) + crit3(pere, fils);
	}

	private int crit1(Accord p, Accord f) {
		// Favorise les petits changements entre ténor et basse
		return 28 - Math.abs(Math.abs(p.alto - p.tenor)
				+ Math.abs(f.alto - f.tenor));
	}

	private int crit2(Accord p, Accord f) {
		// Favorise la tenue entre alto et ténor
		return (7 - Math.abs(f.alto - p.alto) - (7 - Math
				.abs(f.tenor - p.tenor)));
	}

	private int crit3(Accord p, Accord f) {
		// Favorise la grande amplitude de la basse
		return 12 - Math.abs(p.basse - f.basse);
	}

	private int parcoursProfondeurComptage(Accord s) {
		// Compte le nombre d'harmonisations totales
		int compteur = (s.jeuxSuivants.size());
		// Pour chaque accord (sauf du dernier temps), on compte le nombre de
		// liens suivants, et on retire 1
		// Ainsi, a chaque bifurcation d'harmonisations, on augmente le nombre
		if (compteur > 0)
			compteur--;
		for (Accord fils : s.jeuxSuivants)
			compteur += parcoursProfondeurComptage(fils);
		return compteur;
	}

	private void parcoursProfondeurBeaute(Accord pere, int compteur) {
		// Assigne les critères de beauté au graphe
		for (Accord fils : pere.jeuxSuivants) {
			pere.beaute = (beaute(pere, fils));
			parcoursProfondeurBeaute(fils, pere.beaute);
		}
	}
}
