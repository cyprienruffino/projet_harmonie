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
		if (ac.basse < ac.tenor && ac.tenor < ac.alto
				&& ac.alto < ac.soprano) {
			return true;
		}
		return false;
	}

	public static boolean regle1(Accord ac) {// faire une variante soprano pour
												// corentin
		if ((13 < ac.soprano && ac.soprano < 27)
				&& (10 < ac.alto && ac.alto < 23)
				&& (6 < ac.tenor && ac.tenor < 20)
				&& (2 < ac.basse && ac.basse < 16)) {
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
		if (acC.soprano - acS.soprano < 7
				|| acS.soprano - acC.soprano < 7)
			if (acC.alto - acS.alto < 7
					|| acS.alto - acC.alto < 7)
				if (acC.tenor - acS.tenor < 7
						|| acS.tenor - acC.tenor < 7)
					if (acC.basse - acS.basse < 7
							|| acS.basse - acC.basse < 7)
						return true;
		return false;
	}

	public static boolean regle63(Accord acC, Accord acS) {
		int TacC = acC.basse % 7;
		int TacS = acS.basse % 7;
		int c = -1;
		int s = -1;
		for (int i = 0; i < 3; i++) {
			switch (i) {
			case (0):
				c = acC.soprano;
				s = acS.soprano;
				break;
			case (1):
				c = acC.alto;
				s = acS.alto;
				break;
			case (2):
				c = acC.tenor;
				s = acS.tenor;
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
		int TacC = acC.basse % 7;
		int TacS = acS.basse % 7;
		if (acC.type == acS.type) {
			if (TacC != TacS)
				return false;
		}
		int s = -1;
		int c = -1;
		int h = -1;
		for (int i = 0; i < 3; i++) {
			switch (i) {
			case (0):
				c = acC.soprano;
				s = acS.soprano;
				break;
			case (1):
				c = acC.alto;
				s = acS.alto;
				break;
			case (2):
				c = acC.tenor;
				s = acS.tenor;
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
						h = acS.soprano;
						break;
					case (1):
						h = acS.alto;
						break;
					case (2):
						h = acS.tenor;
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
		int s = ac.soprano % 7;
		int a = ac.alto % 7;
		int t = ac.tenor % 7;
		int b = ac.basse % 7;
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
		if (part[0].type==7 || part[part.length - 1].type==7) {
			return false;
		}
		return true;
	}

	public static boolean regle5(Accord AcC, Accord AcS) {
		if (AcC.type == 0) {
			if (AcS.type!=7) {
				return true;
			}
		} else if (AcC.type == 1) {
			if (AcS.type == 4 || AcS.type == 6) {
				return true;
			}
		} else if (AcC.type == 2) {
			if (AcS.type != 0 && AcS.type!=7) {
				return true;
			}
		} else if (AcC.type==3) {
			if (AcS.type!=7) {
				return true;
			}
		} else if (AcC.type==7) {
			if (AcS.type == 0) {
				return true;
			}
		} else if (AcC.type == 4) {
			if (AcS.type == 0 || AcS.type == 2
					|| AcS.type==7
					|| AcS.type==5) {
				return true;
			}
		} else if (AcC.type==5) {
			if (AcS.type == 0 || AcS.type==3
					|| AcS.type == 4) {
				return true;
			}
		} else if (AcC.type == 6) {
			if (AcS.type == 0 || AcS.type == 2) {
				return true;
			}
		}
		return false;
	}

	private static ArrayList<Accord> initAccordPossible(int s,int dur) {
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


	
	private static void cleanGen(ArrayList<Accord> poss, int i) {
		for (int j = 0; j < i; j++) {
			poss.remove(0);
		}
	}

	private static void GenBasse(ArrayList<Accord> poss, int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 3; i < 16; i++) {
				Accord ac = poss.get(k).clone();
				int a = ac.getTonic();
				if (i % 7 == a) {
					ac.basse=i;
					poss.add(ac);
				}
			}
		}
	}

	private static void GenAlto(ArrayList<Accord> poss, int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 11; i < Math.min(22, poss.get(k).soprano); i++) {
				Accord ac = poss.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.basse) {
					if (ac.basse % 7 == ac.soprano % 7) {
						if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
							ac.alto=i;
							poss.add(ac);
						}
					} else if (i % 7 == a || i % 7 == (a + 2) % 7
							|| i % 7 == (a + 4) % 7) {
						ac.alto=i;
						poss.add(ac);
					}
				}
			}
		}
	}

	private static void GenTenor(ArrayList<Accord> poss, int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 7; i < Math.min(19, poss.get(k).alto); i++) {
				Accord ac = poss.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.basse) {
					if (ac.soprano % 7 == ac.alto % 7) {
						if (ac.soprano % 7 == (a + 2) % 7) {
							if (i % 7 == a || i % 7 == (a + 4) % 7) {
								ac.tenor=i;
								poss.add(ac);
							}
						} else if (ac.soprano % 7 == (a + 4) % 7) {
							if (i % 7 == a || i % 7 == (a + 2) % 7) {
								ac.tenor=i;
								poss.add(ac);
							}
						}
					} else if (ac.soprano % 7 == ac.basse % 7
							|| ac.alto % 7 == ac.basse % 7) {
						if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
							ac.tenor=i;
							poss.add(ac);
						}
					} else if (i % 7 == a || i % 7 == (a + 2) % 7
							|| i % 7 == (a + 4) % 7) {
						ac.tenor=i;
						poss.add(ac);
					}
				}
			}
		}
	}

	private static ArrayList<Accord> generateCombinaison(Accord accord) {
		int s = accord.soprano;
		ArrayList<Accord> poss = new ArrayList<Accord>();
		poss = initAccordPossible(s,accord.duree);
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

	private static boolean contient(ArrayList<Accord> poss, Accord ac) {
		int a = ac.type;
		Iterator<Accord> it = poss.iterator();
		while (it.hasNext()) {
			Accord acb = it.next();
			if (acb.type == a) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Determine quelle sont les accords possible quand il y a un temps vide pour la soprano
	 */
	
	private static ArrayList<Accord> initTpsVide(ArrayList<Accord> poss,int dur) {
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

	private static ArrayList<Accord> genCombinaisonVide(ArrayList<Accord> prec,Accord accord) {
		ArrayList<Accord> poss = new ArrayList<Accord>();
		poss = initTpsVide(prec,accord.duree);
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

	private static void GenAltoVide(ArrayList<Accord> poss, int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 11; i < 22; i++) {
				Accord ac = poss.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.basse) {
					if (i % 7 == (a + 2) % 7 || i % 7 == (a + 4) % 7) {
						ac.alto=i;
						poss.add(ac);
					}
				}
			}
		}
	}
	
	private static void GenTenorVide(ArrayList<Accord> poss, int h) {
		for (int k = 0; k < h; k++) {
			for (int i = 7; i < Math.min(19, poss.get(k).alto); i++) {
				Accord ac = poss.get(k).clone();
				int a = ac.getTonic();
				if (i > ac.basse) {
					if(ac.alto%7==(a+2)%7){
						if(i==(a+4)%7){
							ac.tenor=i;
							poss.add(ac);
						}
					}else if(ac.alto%7==(a+4)%7){
						if(i%7==(a+2)%7){
							ac.tenor=i;
							poss.add(ac);
						}
					}
					
				}
			}
		}
	}
	/**
	 * Genere toutes les possibilitées d'accord a un instant t
	 * 
	 * @param preced ArrayList des possibilitées d'accord du temps t-1
	 * @param curent Accord au temps t
	 * @return un ArrayList des possibilitées d'accord au temps t
	 */
	
	public static ArrayList<Accord> generate(ArrayList<Accord> preced,Accord curent){
		if(curent.soprano!=-1){
			return generateCombinaison(curent);
		}else{
			return genCombinaisonVide(preced,curent);
		}
	}
	
}
