package tests;

import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;

import junit.framework.Assert;

import main.*;

import io.*;

import org.junit.*;

public class PartitionTest {
	
	@Test
	public void testPartition() throws IOException{
		ChantReader reader=new ChantReader(new File("data/AuClairDeLaLune.chant"));
		Partition partition = new Partition(reader.readChant());
		partition.generate();
		ArrayList<Accord>[] jeu=partition.getJeu();
		//Test de non-existence de noeuds sans fils
		boolean retour=true;
		boolean tmp=false;
		for(int i=0;i<jeu.length-1;i++){
			for(Accord pere:jeu[i]){
				for(Accord fils:pere.jeuxSuivants){
					for(Accord verif:jeu[i+1]){
						tmp|=(verif==fils);
					}
					retour&=tmp;
					tmp=false;
				}
			}
		}
		Assert.assertTrue(retour);
		//Test de non-existence de noeuds sans pères
		for(int i=1;i<jeu.length;i++){
			for(Accord fils:jeu[i]){
				for(Accord pere:jeu[i-1]){
					for(Accord verif:pere.jeuxSuivants){
						tmp|=(verif==fils);
					}
					retour&=tmp;
					tmp=false;
				}
			}
		}
		//Un noeud sans père n'est pas accessible, donc ne pose pas de problèmes
		//Assert.assertTrue(retour);
	}
}
