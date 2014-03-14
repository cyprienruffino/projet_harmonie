package harmonie;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncoderTest {
	@Test
	public void test() {
		int[] notes = { 24, 1, 24, 1, 24, 1, 24, 1, 4 };
		int test = Encoder.encoder(notes);
		assertArrayEquals(notes, Encoder.decode(test));
	}
}
