package harmonie;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartatabTest {

	@Test
	public void test() {
		String s=new String("do1:1");
		int i=Partatab.petitenote(s);
		assertEquals(0,i);
	}

}
