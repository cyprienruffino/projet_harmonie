package tests;

import static org.junit.Assert.*;


import java.io.IOException;


import main.Accord;

import org.junit.Test;

import io.LilypondWriter;




public class LilypondWriterTest {

	@Test
	public void test() throws IOException {
		Accord[] tab={new Accord(21,18,16,7,4,0),new Accord(21,19,17,10,4,3),new Accord(21,19,16,12,4,5),new Accord(22,20,18,11,4,4)};
		LilypondWriter writer=new LilypondWriter("test.ly",tab,"salutation");
		writer.ecrirePartition();
	}

}
