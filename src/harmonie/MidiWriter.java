package harmonie;

import java.io.*;
import javax.sound.midi.*;

public class MidiWriter {

	private static final int VELOCITY = 64;

	public void encodeMidi(int[][] partition) throws Exception {
		File outputFile = new File("AuClairDeLaLune.mid");

		Sequence sequence = new Sequence(Sequence.PPQ, 1, 4);
		for (int i = 0; i < 4; i++) {
			Track track = sequence.createTrack();
			switch (i) {
			case (0):
				track.add(instrument(73, 1));
				break;
			case (1):
				track.add(instrument(11, 2));
				break;
			case (2):
				track.add(instrument(12, 3));
				break;
			case (3):
				track.add(instrument(32, 4));
				break;
			}
			for (int j = 0; j < partition[0].length; j++) {
				switch (partition[i][j]) {
				case (0):
					track.add(noteOn(36, j, i + 1));
					track.add(noteOff(36, j + 1, i + 1));
					break;
				case (1):
					track.add(noteOn(38, j, i + 1));
					track.add(noteOff(38, j + 1, i + 1));
					break;
				case (2):
					track.add(noteOn(40, j, i + 1));
					track.add(noteOff(40, j + 1, i + 1));
					break;
				case (3):
					track.add(noteOn(41, j, i + 1));
					track.add(noteOff(41, j + 1, i + 1));
					break;
				case (4):
					track.add(noteOn(43, j, i + 1));
					track.add(noteOff(43, j + 1, i + 1));
					break;
				case (5):
					track.add(noteOn(45, j, i + 1));
					track.add(noteOff(45, j + 1, i + 1));
					break;
				case (6):
					track.add(noteOn(47, j, i + 1));
					track.add(noteOff(47, j + 1, i + 1));
					break;
				case (7):
					track.add(noteOn(48, j, i + 1));
					track.add(noteOff(48, j + 1, i + 1));
					break;
				case (8):
					track.add(noteOn(50, j, i + 1));
					track.add(noteOff(50, j + 1, i + 1));
					break;
				case (9):
					track.add(noteOn(52, j, i + 1));
					track.add(noteOff(52, j + 1, i + 1));
					break;
				case (10):
					track.add(noteOn(53, j, i + 1));
					track.add(noteOff(53, j + 1, i + 1));
					break;
				case (11):
					track.add(noteOn(55, j, i + 1));
					track.add(noteOff(55, j + 1, i + 1));
					break;
				case (12):
					track.add(noteOn(57, j, i + 1));
					track.add(noteOff(57, j + 1, i + 1));
					break;
				case (13):
					track.add(noteOn(59, j, i + 1));
					track.add(noteOff(59, j + 1, i + 1));
					break;
				case (14):
					track.add(noteOn(60, j, i + 1));
					track.add(noteOff(60, j + 1, i + 1));
					break;
				case (15):
					track.add(noteOn(62, j, i + 1));
					track.add(noteOff(62, j + 1, i + 1));
					break;
				case (16):
					track.add(noteOn(64, j, i + 1));
					track.add(noteOff(64, j + 1, i + 1));
					break;
				case (17):
					track.add(noteOn(65, j, i + 1));
					track.add(noteOff(65, j + 1, i + 1));
					break;
				case (18):
					track.add(noteOn(67, j, i + 1));
					track.add(noteOff(67, j + 1, i + 1));
					break;
				case (19):
					track.add(noteOn(69, j, i + 1));
					track.add(noteOff(69, j + 1, i + 1));
					break;
				case (20):
					track.add(noteOn(71, j, i + 1));
					track.add(noteOff(71, j + 1, i + 1));
					break;
				case (21):
					track.add(noteOn(72, j, i + 1));
					track.add(noteOff(72, j + 1, i + 1));
					break;
				case (22):
					track.add(noteOn(74, j, i + 1));
					track.add(noteOff(74, j + 1, i + 1));
					break;
				case (23):
					track.add(noteOn(76, j, i + 1));
					track.add(noteOff(76, j + 1, i + 1));
					break;
				case (24):
					track.add(noteOn(77, j, i + 1));
					track.add(noteOff(77, j + 1, i + 1));
					break;
				case (25):
					track.add(noteOn(79, j, i + 1));
					track.add(noteOff(79, j + 1, i + 1));
					break;
				case (26):
					track.add(noteOn(81, j, i + 1));
					track.add(noteOff(81, j + 1, i + 1));
					break;
				case (27):
					track.add(noteOn(83, j, i + 1));
					track.add(noteOff(83, j + 1, i + 1));
					break;

				}
			}
		}
		MidiSystem.write(sequence, 1, outputFile);
	}

	private MidiEvent noteOn(int nKey, long lTick, int canal)
			throws InvalidMidiDataException {
		return createNoteEvent(ShortMessage.NOTE_ON, nKey, VELOCITY, lTick,
				canal);
	}

	private MidiEvent noteOff(int nKey, long lTick, int canal)
			throws InvalidMidiDataException {
		return createNoteEvent(ShortMessage.NOTE_OFF, nKey, 0, lTick, canal);
	}

	private MidiEvent instrument(int instrument, int canal)
			throws InvalidMidiDataException {
		return createNoteEvent(ShortMessage.PROGRAM_CHANGE, instrument, 0, 0,
				canal);
	}

	private MidiEvent createNoteEvent(int nCommand, int nKey, int nVelocity,
			long lTick, int canal) throws InvalidMidiDataException {
		ShortMessage message = new ShortMessage();
		message.setMessage(nCommand, canal, nKey, nVelocity);
		return new MidiEvent(message, lTick);
	}
}
