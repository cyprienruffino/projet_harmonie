package harmonie;

import static org.junit.Assert.*;

import org.junit.Test;

public class MidiWriterTest {

	@Test
	public void test() {
		int tab[]={0,1,12,1,14,1,27,1,3};
		int e=Encoder.encoder(tab);
		int tab2[]={e,e};
		try {
			MidiWriter.encodeMidi(tab2,"test");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
