package monteur;

import java.util.ArrayList;

import main.Accord;

/**
 * Monteur abstrait de combinaisons d'accord a un temps t
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 *
 */

public interface Monteur {
	/**
	 * Retourne la combinaison générée
	 * @return
	 */
	public ArrayList<Accord> getCombinaisons();
	/**
	 * Génère les combinaisons d'accords
	 */
	public void monterCombinaisons();
}
