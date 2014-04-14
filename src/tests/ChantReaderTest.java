package io;

import static org.junit.Assert.*;

import io.ChantReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;

import main.Accord;

import org.junit.Test;

public class ChantReaderTest {

	@Test
	public void testReadChant() throws IOException {
		ChantReader reader=new ChantReader(new File("data/AuClairDeLaLune.chant"));
		ArrayList<Accord>[] actual=reader.readChant();
		Accord[] expected={
				new Accord(21, -1, -1, -1, 1, -1),
				new Accord(21, -1, -1, -1, 1, -1),
				new Accord(21, -1, -1, -1, 1, -1),
				new Accord(22, -1, -1, -1, 1, -1),
				new Accord(23, -1, -1, -1, 2, -1),
				new Accord(22, -1, -1, -1, 2, -1),
				new Accord(21, -1, -1, -1, 1, -1),
				new Accord(23, -1, -1, -1, 1, -1),
				new Accord(22, -1, -1, -1, 1, -1),
				new Accord(22, -1, -1, -1, 1, -1),
				new Accord(21, -1, -1, -1, 3, -1)
				};
		boolean test=true;
		for(int i=0;i<10;i++){
			test&=(actual[i].get(0).equals(expected[i]));
		}
		Assert.assertTrue(test);
	}

}
