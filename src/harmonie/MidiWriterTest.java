package harmonie;

import static org.junit.Assert.*;

import org.junit.Test;

public class MidiWriterTest {

	@Test
	public void test() {
		int tab[]={0,1,0,1,0,1,0,1};
		int tab2[]={4,1,0,1,12,1,25,1,3};
		int tab3[]={};
		int e=Encoder.encoder(tab);
		int e2=Encoder.encoder(tab2);
		int part[]={e,};
		try {
			MidiWriter.encodeMidi(part,"test.mid");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}