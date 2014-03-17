package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import accords.*;

public class RegleTest {

	@Test
	public void test() {
		Accord a1=new II(1,1,1,1,1);
		Accord a2=new III(2,2,2,2,2);
		Accord a3=new II(1,1,1,1,3);
		
		ArrayList<Accord> accords=new ArrayList();
		accords.add(a1);
		accords.add(a2);
		accords.add(a3);
		
		Regle.cleanGen(accords,2);
		
		Assert.assertEquals(1,accords.size());
		
		Assert.assertEquals(3,accords.get(0).getDuree());
	}

}
