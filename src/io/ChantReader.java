package io;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import main.Accord;

public class ChantReader {

	public ArrayList<Accord>[] readChant(File f) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));
		reader.readLine(); // On saute la première ligne
		StringTokenizer input = new StringTokenizer(reader.readLine());
		int size=input.countTokens();
		ArrayList<Accord>[] output = new ArrayList[size];

		for(int i=0;i<size;i++) {
			StringTokenizer note = new StringTokenizer(input.nextToken(), ":");
			output[i]=new ArrayList<Accord>();
			output[i].add((strToAccord(note)));
		}
		reader.close();
		return output;
	}

	public Accord strToAccord(StringTokenizer code) {
		String note = code.nextToken();
		int duree = Integer.parseInt(code.nextToken());
		return new Accord(decode(note), -1, -1, -1, duree, -1);
	}

	public int decode(String note) {
		int octave = octave(note);
		note = note.substring(0, note.length() - 2);
		return noteStrToInt(note, octave);
	}

	public int octave(String note) {
		return Character.getNumericValue(note.charAt(note.length()-1));
	}

	public static int noteStrToInt(String note, int octave) {
		if (note.equals("do"))
			return (octave * 7);
		if (note.equals("re"))
			return (octave * 7) + 1;
		if (note.equals("mi"))
			return (octave * 7) + 2;
		if (note.equals("fa"))
			return (octave * 7) + 3;
		if (note.equals("sol"))
			return (octave * 7) + 4;
		if (note.equals("la"))
			return (octave * 7) + 5;
		if (note.equals("si"))
			return (octave * 7) + 6;
		return -1;
	}
}
