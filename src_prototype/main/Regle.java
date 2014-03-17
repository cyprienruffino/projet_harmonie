package main;

import accords.*;
import java.util.*;

public class Regle {
	/*
	 * Remarque: tierce=tonic+2 quinte=tonic+4=tierce+2
	 */

	public static boolean noteCorrect(Accord ac) {// renvoi true si la note est
		// correcte
		if (regle1(ac) && regle2(ac) && regle3(ac)) {
			return true;
		}
		return false;
	}

	public static boolean enchainementCorrect(Accord AcC, Accord AcS) {
		if (regle5(AcC, AcS) && regle6(AcC, AcS)) {
			return true;
		}
		return false;
	}

	public static boolean regle2(Accord ac) {
		if (ac.getBasse() < ac.getTenor() && ac.getTenor() < ac.getAlto() && ac.getAlto() < ac.getSoprano()) {
			return true;
		}
		return false;
	}

	public static boolean regle1(Accord ac) {// faire une variante soprano pour corentin
		if ((13 < ac.getSoprano() && ac.getSoprano() < 27) && (10 < ac.getAlto() && ac.getAlto() < 23
				) && (6 < ac.getTenor() && ac.getTenor()<20 )&& (2 < ac.getBasse() && ac.getBasse() < 16)) {
			return true;
		}
		return false;
	}

	public static boolean regle6(int[] noteC, int[] noteP) {
		for (int i = 0; i < 7; i++) {
			if (i % 2 == 0) {
				if ((noteP[i] - noteC[i]) < 7 && (noteC[i] - noteP[i]) < 7) {// interval
																				// de
																				// 6
																				// max
																				// entre
																				// les
																				// notes
					if (apartient(i, noteC, noteP)) {// dans le cas ou la note
														// est presente dans les
														// deux accords
						if (noteC[i] % 7 != noteP[i] % 7) {
							return false;
						}
					}
					if ((noteP[i] - noteC[i]) < 3 && (noteC[i] - noteP[i]) < 3) {// si
																					// la
																					// difference
																					// est
																					// >2
																					// elle
																					// doivent
																					// être
																					// de
																					// même
																					// nature
						if (nature(noteP[i], noteP[8]) != nature(noteC[i],
								noteC[8])) {
							return false;
						}
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean regle3(Accord ac) {
		int s=ac.getSoprano()%7;
		int a=ac.getAlto()%7;
		int t=ac.getTenor()%7;
		int b=ac.getBasse()%7;
		if(ac.getClass().equals(I.class)){
			if(b==0){
				if((s==2||s==4) && (a==2||a==4) && (t==2||t==4)){
					return true;
				}
			}
		}else if(ac.getClass().equals(II.class)){
			if(b==1){
				if((s==3 || s==5)&&(a==3||a==5)&&(t==3||t==5)){
					return true;
				}
			}
		}else if(ac.getClass().equals(III.class)){
			if(b==2){
				if((s==4||s==6)&&(a==4||a==6)&&(t==4||t==6)){
					return true;
				}
			}
		}else if(ac.getClass().equals(IV.class)||ac.getClass().equals(IVb.class)){
			if(b==3){
				if((s==5||s==0)&&(a==5||a==0)&&(t==5||t==0)){
					return true;
				}
			}
		}else if(ac.getClass().equals(V.class)){
			if(b==4){
				if((s==6||s==1)&&(a==6||a==1)&&(t==6||t==1)){
					return true;
				}
			}
		}else if(ac.getClass().equals(VI.class)){
			if(b==5){
				if((s==0||s==2)&&(a==0||a==2)&&(t==0||t==2)){
					return true;
				}
			}
		}else if(ac.getClass().equals(VII.class)){
			if(b==7){
				if((s==1||s==3)&&(a==1||a==3)&&(t==1||t==3)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean regle4(Accord[] part) {
		if (part[0].getClass().equals(IVb.class) || part[part.length-1].getClass().equals(IVb.class)) {
			return false;
		}
		return true;
	}

	public static boolean regle5(Accord AcC, Accord AcS) {
		if (AcC.getClass().equals(I.class)) {
			if (!(AcS.getClass().equals(IVb.class))) {
				return true;
			}
		} else if (AcC.getClass().equals(II.class)) {
			if (AcS.getClass().equals(V.class)
					|| AcS.getClass().equals(VII.class)) {
				return true;
			}
		} else if (AcC.getClass().equals(III.class)) {
			if (!(AcS.getClass().equals(I.class))
					&& !(AcS.getClass().equals(IVb.class))) {
				return true;
			}
		} else if (AcC.getClass().equals(IV.class)) {
			if (!(AcS.getClass().equals(IVb.class))){
				return true;
			}
		} else if (AcC.getClass().equals(IVb.class)) {
			if (AcS.getClass().equals(I.class)){
				return true;
			}
		} else if (AcC.getClass().equals(V.class)) {
			if (AcS.getClass().equals(I.class)
					|| AcS.getClass().equals(III.class)
					|| AcS.getClass().equals(IVb.class)
					|| AcS.getClass().equals(VI.class)) {
				return true;
			}
		} else if (AcC.getClass().equals(VI.class)) {
			if (AcS.getClass().equals(I.class)
					|| AcS.getClass().equals(I.class)
					|| AcS.getClass().equals(IV.class)
					|| AcS.getClass().equals(V.class)) {
				return true;
			}
		} else if (AcC.getClass().equals(VII.class)) {
			if (AcS.getClass().equals(I.class)
					|| AcS.getClass().equals(III.class)) {
				return true;
			}
		}
		return false;
	}

	public static boolean apartient(int i, int[] note, int[] ac) {// verifie si
																	// la note
		// est presente dans
		// l'accord
		int n = note[i];
		for (int j = 0; j < ac.length - 1; j++) {
			if ((n % 7) == (ac[j] % 7)) {
				return true;
			}
		}
		return false;
	}

	public static int nature(int note, int Ac) {// retourne la nature de la note
		// 1=tonic 2=tierce 3=quinte
		// l'accord doit être correct sinon
		// la valeur retourné sera fausse
		switch (Ac) {
		case (0):
			if ((note % 7) == 0) {
				return 1;
			} else {
				if ((note % 7) == 2) {
					return 2;
				}
			}
			return 3;
		case (1):
			if ((note % 7) == 1) {
				return 1;
			} else {
				if ((note % 7) == 3) {
					return 2;
				}
			}
			return 3;
		case (2):
			if ((note % 7) == 2) {
				return 1;
			} else {
				if ((note % 7) == 4) {
					return 2;
				}
			}
			return 3;
		case (3):
			if ((note % 7) == 3) {
				return 1;
			} else {
				if ((note % 7) == 5) {
					return 2;
				}
			}
			return 3;
		case (5):
			if ((note % 7) == 4) {
				return 1;
			} else {
				if ((note % 7) == 6) {
					return 2;
				}
			}
			return 3;
		case (6):
			if ((note % 7) == 5) {
				return 1;
			} else {
				if ((note % 7) == 0) {
					return 2;
				}
			}
			return 3;
		case (7):
			if ((note % 7) == 6) {
				return 1;
			} else {
				if ((note % 7) == 1) {
					return 2;
				}
			}
			return 3;
		}
		return -1;
	}

public static int accordPossible (int s){
	s=s%7;
	switch(s){
	case(0):
		return 460;
	case(1):
		return 157;
	case(2):
		return 206;
	case(3):
		return 147;
	case(4):
		return 520;
	case(5):
		return 146;
	case(6):
		return 257;
	}
	return-1;
}

public static ArrayList<Accord> generateCombinaison(int s){
	ArrayList<Accord> poss=new ArrayList<Accord>();
	switch(s%7){
	case(0):
		poss.add(new I(s,-1,-1,-1,-1));
		poss.add(new VI(s,-1,-1,-1,-1));
		poss.add(new IV(s,-1,-1,-1,-1));
		poss.add(new IVb(s,-1,-1,-1,-1));
		break;
	case(1):
		poss.add(new II(s,-1,-1,-1,-1));
		poss.add(new V(s,-1,-1,-1,-1));
		poss.add(new VII(s,-1,-1,-1,-1));
		break;
	}
}
}