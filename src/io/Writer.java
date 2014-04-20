package io;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import main.Accord;

/**
 * Fabrique abstraite de méthode d'écriture sur sortie
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 * 
 */

public abstract class Writer {

	/**
	 * Retourne la méthode d'écriture correspondant au type  
	 * @param type
	 * @param fichier
	 * @return
	 */
	public static Writer getWriter(String type, String fichier) {
		if (type == "lilypond")
			return new LilypondWriter(fichier, fichier.substring(0,
					fichier.length() - 3));
		else
			return new MidiWriter(fichier, fichier.substring(0,
					fichier.length() - 4));
	}

	public abstract void addPartition(Accord[] partition);

	public abstract void ecrirePartition() throws IOException,
			InvalidMidiDataException;

}
