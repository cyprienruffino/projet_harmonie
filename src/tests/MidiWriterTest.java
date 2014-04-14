package tests;

import static org.junit.Assert.*;


import main.Accord;

import org.junit.Test;



import io.MidiWriter;

public class MidiWriterTest {

	@Test
	public void test() {
		
		Accord[] tab={new Accord(21,18,16,7,4,0),new Accord(21,19,17,10,4,3),new Accord(21,19,16,12,4,5),new Accord(22,20,18,11,4,4)};
		try {
			MidiWriter writer=new MidiWriter("test.mid",tab,"test");
			writer.ecrirePartition();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}