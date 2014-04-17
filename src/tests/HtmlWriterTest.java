package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import io.*;
import main.*;

public class HtmlWriterTest {

	@Test
	public void test() throws IOException {
		ChantReader reader=new ChantReader(new File("data/AuClairDeLaLune.chant"));
		Partition partition = new Partition(reader.readChant());
		partition.generate();
		int nb=partition.nombre();
		HtmlWriter writer=new HtmlWriter("AuClairDeLaLune",nb,"data/AuClairDeLaLune.mid","data/AuClairDeLaLune.ly");
		HtmlWriter[]tab={writer};
		writer.htmlWrite(tab,"data/test.html");
	}

}
