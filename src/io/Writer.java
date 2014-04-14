package writer;


import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import main.Accord;


public interface Writer {
	
	public void addPartition(Accord[] partition);
	public void ecrirePartition() throws IOException, InvalidMidiDataException;
	
}
