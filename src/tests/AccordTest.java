package tests;


import main.*;

import org.junit.*;


public class AccordTest {

	@Test
	public void testAddSuivant(){
		Accord accord=new Accord();
		accord.addSuivant(accord);
		Assert.assertTrue(accord.jeuxSuivants.size()==1);
	}
	@Test
	public void testnoteCorrect(){
		Assert.assertTrue(Accord.noteCorrect(new Accord(21,18,16,7,4,0)));
		Assert.assertFalse(Accord.noteCorrect(new Accord(22,18,16,7,4,0)));
	}
	
	@Test
	public void testEnchainementCorrect(){
		Assert.assertTrue(Accord.enchainementCorrect(new Accord(21,18,16,7,4,0),new Accord(21,19,17,10,4,4)));
	}
	
	@Test
	public void testRegle4(){
		Accord[]part={new Accord(22,10,15,8,4,7),new Accord(2,2,4,0,4,0)};
		Accord[]part2={new Accord(22,10,15,8,4,0),new Accord(2,2,4,0,4,7)};
		Accord[]part3={new Accord(22,10,15,8,4,0),new Accord(2,2,4,0,4,0)};
		Assert.assertFalse(Accord.regle4(part));
		Assert.assertFalse(Accord.regle4(part2));
		Assert.assertTrue(Accord.regle4(part3));
		
	}
	
}
