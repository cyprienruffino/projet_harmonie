package tests;

import static org.junit.Assert.*;

import java.io.*;

import junit.framework.Assert;

import main.*;

import org.junit.*;

public class PartitionTest {
	
	@Before
	public void loadPartition() throws IOException{
		Partition partition = new Partition(Partatab.readChant(new File("data/AuClairDeLaLune.chant")));
	}
	@Test
	public void testGeneration(){
		
	}

}
