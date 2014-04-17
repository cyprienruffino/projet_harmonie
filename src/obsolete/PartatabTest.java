package obsolete;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;

import main.Accord;
import main.Partatab;

import org.junit.Test;

import accords.VI;

public class PartatabTest {
	@Test
	public void rempliretpetitenoteTest() {
		int[][] lalafell = new int[4][7];
		Partatab.remplire(lalafell);
		Assert.assertEquals(Partatab.petitenote("do3:1", lalafell), 14);
	}

	@Test
	public void nbnotetest() {
		Assert.assertEquals(
				Partatab.nbnote("do4:1 do4:1 do4:1 re4:1 mi4:2 re4:2 do4:1 mi4:1 re4:1 re4:1 do4:3 -:1"),
				12);
	}

	@Test
	public void isolementest() {
		String[] s = new String[4];
		String[] si = new String[4];
		for (int i = 0; i < 4; i++) {
			if (i == 0)
				si[i] = "do4:1";
			else if (i == 1)
				si[i] = "-:1";
			else if (i == 2)
				si[i] = "sol2:3";
			else
				si[i] = "re2:1";
		}
		Partatab.isolement("do4:1 -:1 sol2:3 re2:1", s);
		assertArrayEquals(s, si);
	}
/*
	public void readchanttest() {
		File f = new File("data/croaporal.chant");
		ArrayList<Accord>[] s=new ArrayList[3];
		try {
			s = Partatab.readChant(f);
		} catch (IOException e) {
			System.out.println("bhou");
		}
		ArrayList<Accord>[] si = new ArrayList[3];
		for(int i = 0;i<3;i++){
				si[i]=new ArrayList();
				si[i].add(new VI(21,0,0,0,0));
		}
		boolean test=true;
		for(int i=0;i<s.length;i++){
			test &= s[i]==si[i];
		}		
	}
	*/
}