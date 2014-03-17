package harmonie;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class PartitionTest {

	@Test
	public void testGenerate() {
		int [][] tab={{0,1,0,0,0,0,0,0,0}};
		Partition.generate(tab);
		int[][] partition=Partition.getJeu();
		boolean test=true;
		for(int[]jeu:partition)
			test&=jeu[8]==3||jeu[8]==6;
		Assert.assertTrue(test);
		}
}
