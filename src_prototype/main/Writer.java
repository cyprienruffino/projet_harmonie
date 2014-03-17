package main;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

public interface Writer {
	
	public void ecrirePartition() throws IOException, InvalidMidiDataException;
	
}
