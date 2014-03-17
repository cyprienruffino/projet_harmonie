package tests;

import static org.junit.Assert.*;

import main.Accord;

import org.junit.Test;


import accords.*;
import writer.MidiWriter;

public class MidiWriterTest {

	@Test
	public void test() {
		
		Accord[] tab={new I(21,18,16,7,4),new IV(21,19,17,10,4),new VI(21,19,16,12,4),new V(22,20,18,11,4)};
		try {
			MidiWriter writer=new MidiWriter("test.mid",tab,"test");
			writer.ecrirePartition();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}