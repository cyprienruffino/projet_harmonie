package harmonie;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class LilypondWriterTest {

	@Test
	public void test() throws IOException {
		int[] tab={0,1,3};
		LilypondWriter.LilyWriter("test.txt",tab,"salutation");
	}

}
