package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import io.*;
import main.*;

public class HtmlWriterTest {

	@Test
	public void testExistence() throws IOException {
		ChantReader reader=new ChantReader(new File("data/AuClairDeLaLune.chant"));
		Partition partition = new Partition(reader.readChant());
		partition.generate();
		int nb=partition.nombre();
		HtmlWriter writer=new HtmlWriter("AuClairDeLaLune",nb,"data/AuClairDeLaLune.mid","data/AuClairDeLaLune.ly");
		HtmlWriter[]tab={writer};
		writer.htmlWrite(tab,"data/test.html");
		try{
			BufferedReader tmp=new BufferedReader(new FileReader("data/test.html"));
			Assert.assertTrue(true);
		}catch(FileNotFoundException e){
			Assert.assertTrue(false);
		}
	}

}
