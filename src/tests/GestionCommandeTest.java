package tests;


import static org.junit.Assert.*;

import main.Commande;

import org.junit.Before;
import org.junit.Test;

public class GestionCommandeTest {

	@Test
	public void setUp() throws Exception {
		String []args=new String[8];
		args[0]="-name";
		args[1]="-help";
		args[2]="-midi";
		args[3]="fichier1.chant";
		args[4]="fichier2.mid";
		args[5]="-nombre";
		args[6]="fichier5.chant";
		
		String []argsBis=new String [6];
		argsBis[0]="-beaute";
		argsBis[1]="a";
		argsBis[2]="-midi";
		argsBis[3]="fichier1.chant";
		argsBis[4]="fichier2.mid";
		
		String []argsBisBis=new String[10];
		argsBisBis[0]="-ly";
		argsBisBis[1]="fichierMerde.chant";
		argsBisBis[2]="fichierMerde.ly";
		argsBisBis[3]="-beaute";
		argsBisBis[4]="5";
		
		Commande.readCommand(args);
		Commande.readCommand(argsBis);
		Commande.readCommand(argsBisBis);
		
	}
}
