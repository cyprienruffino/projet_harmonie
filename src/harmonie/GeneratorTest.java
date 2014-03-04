package harmonie;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class GeneratorTest {

	@Test
	public void testGenerate() {
		int [][] tab={{0,1,0,0,0,0,0,0,0}};
		Generator.generate(tab);
		int[][] partition=Generator.getJeu();
		boolean test=true;
		for(int[]jeu:partition)
			test&=jeu[8]==3||jeu[8]==6;
		Assert.assertTrue(test);
		}
}
