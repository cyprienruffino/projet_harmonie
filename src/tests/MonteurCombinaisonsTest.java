package tests;

import static org.junit.Assert.*;
import junit.framework.Assert;
import main.Accord;
import main.MonteurCombinaisons;

import org.junit.Test;

public class MonteurCombinaisonsTest {

	@Test
	public void test() {
		boolean test=true;
		boolean test2=false;
		Accord accord=new Accord(1,0,0,0,0,0);
		MonteurCombinaisons builder = new MonteurCombinaisons();
		builder.setCombinaison(null, accord);
		try{
			builder.construireCombinaison();
		}catch(NullPointerException e){
			test=false;
		}
		accord=new Accord(-1,0,0,0,0,0);
		builder.setCombinaison(null, accord);
		try{
			builder.construireCombinaison();
		}catch(NullPointerException e){
			test2=true;
		}
		assertTrue(test&&test2);
	}

}
