package main;

import java.util.*;

public class Regle {
	/*
	 * Remarque: tierce=tonic+2 quinte=tonic+4=tierce+2
	 */

	public static boolean noteCorrect(Accord ac) {// renvoi true si la note est
		// correcte
		if (regle1(ac)) {
			if (regle2(ac))
				if (regle3(ac))
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
		if (ac.getBasse() < ac.getTenor() && ac.getTenor() < ac.getAlto()
				&& ac.getAlto() < ac.getSoprano()) {
			return true;
		}
		return false;
	}

	public static boolean regle1(Accord ac) {// faire une variante soprano pour
												// corentin
		if ((13 < ac.getSoprano() && ac.getSoprano() < 27)
				&& (10 < ac.getAlto() && ac.getAlto() < 23)
				&& (6 < ac.getTenor() && ac.getTenor() < 20)
				&& (2 < ac.getBasse() && ac.getBasse() < 16)) {
			return true;
		}
		return false;
	}

	public static boolean regle6(Accord acC, Accord acS) {
		if (regle61(acC, acS) && regle63(acC, acS) && regle62(acC, acS))
			return true;
		return false;
	}

	public static boolean regle61(Accord acC, Accord acS) {
		if (acC.getSoprano() - acS.getSoprano() < 7
				|| acS.getSoprano() - acC.getSoprano() < 7)
			if (acC.getAlto() - acS.getAlto() < 7
					|| acS.getAlto() - acC.getAlto() < 7)
				if (acC.getTenor() - acS.getTenor() < 7
						|| acS.getTenor() - acC.getTenor() < 7)
					if (acC.getBasse() - acS.getBasse() < 7
							|| acS.getBasse() - acC.getBasse() < 7)
						return true;
		return false;
	}

	public static boolean regle63(Accord acC, Accord acS) {
		int TacC = acC.getBasse() % 7;
		int TacS = acS.getBasse() % 7;
		int c = -1;
		int s = -1;
		for (int i = 0; i < 3; i++) {
			switch (i) {
			case (0):
				c = acC.getSoprano();
				s = acS.getSoprano();
				break;
			case (1):
				c = acC.getAlto();
				s = acS.getAlto();
				break;
			case (2):
				c = acC.getTenor();
				s = acS.getTenor();
				break;
			}
			if (c - s > 2 || s - c > 2) {
				if (!(TacC == c % 7 && TacS == s % 7)
						&& !((TacC + 2) % 7 == c % 7 && (TacS + 2) % 7 == s % 7)
						&& !((TacC + 4) % 7 == c % 7 && (TacS + 4) % 7 == s % 7))
					return false;
			}
		}
		return true;
	}

	public static boolean regle62(Accord acC, Accord acS) {
		int TacC = acC.getBasse() % 7;
		int TacS = acS.getBasse() % 7;
		if (acC.getType() == acS.getType()) {
			if (TacC != TacS)
				return false;
		}
		int s = -1;
		int c = -1;
		int h = -1;
		for (int i = 0; i < 3; i++) {
			switch (i) {
			case (0):
				c = acC.getSoprano();
				s = acS.getSoprano();
				break;
			case (1):
				c = acC.getAlto();
				s = acS.getAlto();
				break;
			case (2):
				c = acC.getTenor();
				s = acS.getTenor();
				break;
			}
			if (c % 7 == s % 7) {
				if (c != s) {
					return false;
				}
			}
			for (int j = 0; j < 3; j++) {
				if (j != s) {
					switch (j) {
					case (0):
						h = acS.getSoprano();
						break;
					case (1):
						h = acS.getAlto();
						break;
					case (2):
						h = acS.getTenor();
						break;
					}
					if ((h % 7 == c % 7) && c != s) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static boolean regle3(Accord ac) {
		int s = ac.getSoprano() % 7;
		int a = ac.getAlto() % 7;
		int t = ac.getTenor() % 7;
		int b = ac.getBasse() % 7;
		int nac = ac.getTonic();
		if (nac == b) {
			if (s == b) {
				if (a == (nac + 2) % 7) {
					if (t == (nac + 4) % 7) {
						return true;
					}
				} else if (a == (nac + 4) % 7) {
					if (t == (nac + 2) % 7) {
						return true;
					}
				}

			} else if (a == b) {
				if (s == (nac + 2) % 7) {
					if (t == (nac + 4) % 7) {
						return true;
					}
				} else if (s == (nac + 4) % 7) {
					if (t == (nac + 2) % 7) {
						return true;
					}
				}
			} else if (t == b) {
				if (s == (nac + 2) % 7) {
					if (a == (nac + 4) % 7) {
						return true;
					}
				} else if (s == (nac + 4) % 7) {
					if (a == (nac + 2) % 7) {
						return true;
					}
				}
			} else if (s == a) {
				if (s == (nac + 2) % 7) {
					if (t == (nac + 4) % 7) {
						return true;
					}
				} else if (s == (nac + 4) % 7) {
					if (t == (nac + 2) % 7) {
						return true;
					}
				} else if (s == t) {
					if (s == (nac + 2) % 7) {
						if (a == (nac + 4) % 7) {
							return true;
						}
					} else if (s == (nac + 4) % 7) {
						if (a == (nac + 2) % 7) {
							return true;
						}
					}
				} else if (s == a) {
					if (s == (nac + 2) % 7) {
						if (t == (nac + 4) % 7) {
							return true;
						}
					} else if (s == (nac + 4) % 7) {
						if (t == (nac + 2) % 7) {
							return true;
						}
					}
				} else if (a == t) {
					if (a == (nac + 2) % 7) {
						if (t == (nac + 4) % 7) {
							return true;
						}
					} else if (a == (nac + 4) % 7) {
						if (t == (nac + 2) % 7) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean regle4(Accord[] part) {
		if (part[0].isB() || part[part.length - 1].isB()) {
			return false;
		}
		return true;
	}

	public static boolean regle5(Accord AcC, Accord AcS) {
		if (AcC.getType() == 0) {
			if (AcS.getType()!=7) {
				return true;
			}
		} else if (AcC.getType() == 1) {
			if (AcS.getType() == 4 || AcS.getType() == 6) {
				return true;
			}
		} else if (AcC.getType() == 2) {
			if (AcS.getType() != 0 && AcS.getType()!=7) {
				return true;
			}
		} else if (AcC.getType()==3) {
			if (AcS.getType()!=7) {
				return true;
			}
		} else if (AcC.getType()==7) {
			if (AcS.getType() == 0) {
				return true;
			}
		} else if (AcC.getType() == 4) {
			if (AcS.getType() == 0 || AcS.getType() == 2
					|| AcS.getType()==7
					|| AcS.getType()==5) {
				return true;
			}
		} else if (AcC.getType()==5) {
			if (AcS.getType() == 0 || AcS.getType()==3
					|| AcS.getType() == 4) {
				return true;
			}
		} else if (AcC.getType() == 6) {
			if (AcS.getType() == 0 || AcS.getType() == 2) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Accord> initAccordPossible(int s,int dur) {
		ArrayList<Accord> poss = new ArrayList<Accord>();
		switch (s % 7) {
		case (0):
			poss.add(new Accord(s, -1, -1, -1, dur,0));
			poss.add(new Accord(s, -1, -1, -1, dur,5));
			poss.add(new Accord(s, -1, -1, -1, dur,3));
			poss.add(new Accord(s, -1, -1, -1, dur,7));
			break;
		case (1):
			poss.add(new Accord(s, -1, -1, -1, dur,1));
			poss.add(new Accord(s, -1, -1, -1, dur,4));
			poss.add(new Accord(s, -1, -1, -1, dur,6));
			break;
		case (2):
			poss.add(new Accord(s, -1, -1, -1, dur,2));
			poss.add(new Accord(s, -1, -1, -1, dur,0));
			poss.add(new Accord(s, -1, -1, -1, dur,4));
			break;
		case (3):
			poss.add(new Accord(s, -1, -1, -1, dur,1));
			poss.add(new Accord(s, -1, -1, -1, dur,3));
			poss.add(new Accord(s, -1, -1, -1, dur,7));
			poss.add(new Accord(s, -1, -1, -1, dur,6));
			break;
		case (4):
			poss.add(new Accord(s, -1, -1, -1, dur,0));
			poss.add(new Accord(s, -1, -1, -1, dur,2));
			poss.add(new Accord(s, -1, -1, -1, dur,4));
			break;
		case (5):
			poss.add(new Accord(s, -1, -1, -1, dur,1));
			poss.add(new Accord(s, -1, -1, -1, dur,3));
			poss.add(new Accord(s, -1, -1, -1, dur,7));
			poss.add(new Accord(s, -1, -1, -1, dur,5));
			break;
		case (6):
			poss.add(new Accord(s, -1, -1, -1, dur,2));
			poss.add(new Accord(s, -1, -1, -1, dur,4));
			poss.add(new Accord(s, -1, -1, -1, dur,6));
			break;
		}
		return poss;
	}


	
	public static void cleanGen(ArrayList<Accord> poss, int i) {
		for (int j = 0; j < i; j++) {
			poss.remove(0);
		}
	}

	public static void GenBasse(ArrayList<Accord> poss, int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 3; i < 16; i++) {
				Accord ac = poss.get(k).clone();
				int a = ac.getTonic();
				if (i % 7 == a) {
					ac.setBasse(i);
					poss.add(ac);
				}
			}
		}
	}

	public static void GenAlto(ArrayList<Accord> poss, int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 11; i < Math.min(22, poss.get(k).getSoprano()); i++) {
				Accord ac = poss.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.getBasse()) {
					if (ac.getBasse() % 7 == ac.getSoprano() % 7) {
						if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
							ac.setAlto(i);
							poss.add(ac);
						}
					} else if (i % 7 == a || i % 7 == (a + 2) % 7
							|| i % 7 == (a + 4) % 7) {
						ac.setAlto(i);
						poss.add(ac);
					}
				}
			}
		}
	}

	public static void GenTenor(ArrayList<Accord> poss, int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 7; i < Math.min(19, poss.get(k).getAlto()); i++) {
				Accord ac = poss.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.getBasse()) {
					if (ac.getSoprano() % 7 == ac.getAlto() % 7) {
						if (ac.getSoprano() % 7 == (a + 2) % 7) {
							if (i % 7 == a || i % 7 == (a + 4) % 7) {
								ac.setTenor(i);
								poss.add(ac);
							}
						} else if (ac.getSoprano() % 7 == (a + 4) % 7) {
							if (i % 7 == a || i % 7 == (a + 2) % 7) {
								ac.setTenor(i);
								poss.add(ac);
							}
						}
					} else if (ac.getSoprano() % 7 == ac.getBasse() % 7
							|| ac.getAlto() % 7 == ac.getBasse() % 7) {
						if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
							ac.setTenor(i);
							poss.add(ac);
						}
					} else if (i % 7 == a || i % 7 == (a + 2) % 7
							|| i % 7 == (a + 4) % 7) {
						ac.setTenor(i);
						poss.add(ac);
					}
				}
			}
		}
	}

	public static ArrayList<Accord> generateCombinaison(Accord accord) {
		int s = accord.getSoprano();
		ArrayList<Accord> poss = new ArrayList<Accord>();
		poss = initAccordPossible(s,accord.getDuree());
		int i = poss.size();
		GenBasse(poss, i);
		cleanGen(poss, i);
		i = poss.size();
		GenAlto(poss, i);
		cleanGen(poss, i);
		i = poss.size();
		GenTenor(poss, i);
		cleanGen(poss, i);
		Iterator<Accord> it = poss.iterator();
		Accord ac;
		while (it.hasNext()) {
			ac = it.next();
			if (!noteCorrect(ac))
				it.remove();
		}
		return poss;
	}

	public static boolean contient(ArrayList<Accord> poss, Accord ac) {
		int a = ac.getType();
		Iterator<Accord> it = poss.iterator();
		while (it.hasNext()) {
			Accord acb = it.next();
			if (acb.getType() == a) {
				if (acb.getType() == 3) {
						return true;
					} else if (acb.getType()!=7 && ac.getType()!=7) {
						return true;
					}
					return false;
				}
				return true;
			}
		return false;
	}

	public static ArrayList<Accord> initTpsVide(ArrayList<Accord> poss,int dur) {
		ArrayList<Accord> acSPoss = new ArrayList<Accord>();
		Iterator<Accord> it = poss.iterator();
		Accord[] accord = { new Accord(0, 0, 0, 0, dur,0), new Accord(0, 0, 0, 0, dur,1),
				new Accord(0, 0, 0, 0, dur,2), new Accord(0, 0, 0, 0, dur,3),
				new Accord(0, 0, 0, 0, dur,7), new Accord(0, 0, 0, 0, dur,4),
				new Accord(0, 0, 0, 0, dur,5), new Accord(0, 0, 0, 0, dur,6) };
		while (it.hasNext()) {
			Accord ac = it.next();
			for (int i = 0; i < accord.length; i++) {
				if (regle5(ac, accord[i])) {
					if (!contient(acSPoss, accord[i])) {
						acSPoss.add(accord[i]);
					}
				}
			}
		}
		return acSPoss;
	}

	public static ArrayList<Accord> genCombinaisonVide(ArrayList<Accord> prec,Accord accord) {
		ArrayList<Accord> poss = new ArrayList<Accord>();
		poss = initTpsVide(prec,accord.getDuree());
		int i = poss.size();
		GenBasse(poss, i);
		cleanGen(poss, i);
		i = poss.size();
		GenAltoVide(poss, i);
		cleanGen(poss, i);
		i = poss.size();
		GenTenorVide(poss, i);
		cleanGen(poss, i);
		Iterator<Accord> it = poss.iterator();
		Accord ac;
		while (it.hasNext()) {
			ac = it.next();
			if (!noteCorrect(ac))
				it.remove();
		}
		return poss;
	}

	public static void GenAltoVide(ArrayList<Accord> poss, int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 11; i < 22; i++) {
				Accord ac = poss.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.getBasse()) {
					if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
						ac.setAlto(i);
						poss.add(ac);
					}
				}
			}
		}
	}
	
	public static void GenTenorVide(ArrayList<Accord> poss, int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 7; i < Math.min(19, poss.get(k).getAlto()); i++) {
				Accord ac = poss.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.getBasse()) {
					if(ac.getAlto()%7==(a+2)%7){
						if(i==(a+4)%7){
							ac.setTenor(i);
							poss.add(ac);
						}
					}else if(ac.getAlto()%7==(a+4)%7){
						if(i%7==(a+2)%7){
							ac.setTenor(i);
							poss.add(ac);
						}
					}
					
				}
			}
		}
	}
	public static ArrayList<Accord> generate(ArrayList<Accord> preced,Accord curent){
		if(curent.getSoprano()!=-1){
			return generateCombinaison(curent);
		}else{
			return genCombinaisonVide(preced,curent);
		}
	}
	
	public static int noteStrToInt(String note,int octave){
		if (note.equals("do"))
			return (octave*7);
		if (note.equals("re"))
			return (octave*7)+1;
		if (note.equals("mi"))
			return (octave*7)+2;
		if (note.equals("fa"))
			return (octave*7)+3;
		if (note.equals("sol"))
			return (octave*7)+4;
		if (note.equals("la"))
			return (octave*7)+5;
		if (note.equals("si"))
			return (octave*7)+6;
		return -1;
	}
}
