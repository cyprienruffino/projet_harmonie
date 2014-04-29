package main;

import java.util.ArrayList;

/**
 * 
 * Objet chaîné représentant un accord dans le graphe de la partition
 * 
 * @author DALBIS Paul-Arthur
 * @author COLOMIERS Corentin
 * @author SERRETTE Nicolas
 * @author RUFINO Cyprien
 * 
 */
public class Accord implements Cloneable {

	/**
	 * Valeur de la note soprano
	 */
	public int soprano;
	/**
	 * Valeur de la note alto
	 */
	public int alto;
	/**
	 * Valeur de la note basse
	 */
	public int basse;
	/**
	 * Valeur de la note ténor
	 */
	public int tenor;
	/**
	 * Durée de l'accord
	 */
	public int duree;
	/**
	 * Numéro de l'accord
	 */
	public int type;

	Accord pere;
	int beaute;

	/**
	 * Liens vers les accords du temps suivant de l'harmonisation
	 */
	public ArrayList<Accord> jeuxSuivants;

	/**
	 * Constructeur d'accord
	 * 
	 * @param soprano
	 * @param alto
	 * @param ténor
	 * @param basse
	 * @param durée
	 * @param type
	 */
	public Accord(int s, int a, int t, int b, int d, int c) {
		soprano = s;
		alto = a;
		tenor = t;
		basse = b;
		duree = d;
		type = c;
		jeuxSuivants = new ArrayList<Accord>();
	}

	public Accord() {
		jeuxSuivants = new ArrayList<Accord>();
	}

	/**
	 * Egalité de valeur de deux accords
	 * 
	 * @param ac
	 * @return boolean
	 */
	public boolean equals(Accord ac) {
		if (this.soprano == ac.soprano)
			if (this.alto == ac.alto)
				if (this.tenor == ac.tenor)
					if (this.basse == ac.basse)
						if (this.duree == ac.duree)
							return true;
		return false;
	}

	/**
	 * crée une copie de l'accord
	 */
	public Accord clone() {
		Accord ac = new Accord();
		ac.soprano = this.soprano;
		ac.alto = this.alto;
		ac.tenor = this.tenor;
		ac.basse = this.basse;
		ac.duree = this.duree;
		ac.type = this.type;
		return ac;
	}

	public void addSuivant(Accord a) {
		this.jeuxSuivants.add(a);
	}

	/**
	 * Renvoi la note tonique de l'accord
	 */
	public int getTonic() {
		int a = this.type;
		if (a == 7) {
			a = 3;
		}
		return a;
	}

	/**
	 * Verifie si l'accord est correct
	 * @param ac
	 * @return
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

	/**
	 * Verifie si un enchainement d'accord est possible, utilise les régles 5 et
	 * 6
	 * 
	 * @param AcC
	 *            Accord courent
	 * @param AcS
	 *            Accord suivant
	 * @return
	 */
	public static boolean enchainementCorrect(Accord AcC, Accord AcS) {
		if (regle5(AcC, AcS) && regle6(AcC, AcS)) {
			return true;
		}
		return false;
	}

	private static boolean regle2(Accord ac) {
		if ((ac.basse < ac.tenor && ac.tenor < ac.alto && ac.alto < ac.soprano)
				|| (ac.soprano == -1 && ac.basse < ac.tenor && ac.tenor < ac.alto)) {
			return true;
		}
		return false;
	}

	private static boolean regle1(Accord ac) {
		if (((13 < ac.soprano && ac.soprano < 27) || ac.soprano == -1)
				&& (10 < ac.alto && ac.alto < 23)
				&& (6 < ac.tenor && ac.tenor < 20)
				&& (2 < ac.basse && ac.basse < 16)) {
			return true;
		}
		return false;
	}

	private static boolean regle6(Accord acC, Accord acS) {
		if (regle61(acC, acS) && regle63(acC, acS) && regle62(acC, acS))
			return true;
		return false;
	}

	private static boolean regle61(Accord acC, Accord acS) {
		if (acC.soprano - acS.soprano < 7 || acS.soprano - acC.soprano < 7
				|| acS.soprano == -1 | acC.soprano == -1)
			if (acC.alto - acS.alto < 7 || acS.alto - acC.alto < 7)
				if (acC.tenor - acS.tenor < 7 || acS.tenor - acC.tenor < 7)
					if (acC.basse - acS.basse < 7 || acS.basse - acC.basse < 7)
						return true;
		return false;
	}

	private static boolean regle63(Accord acC, Accord acS) {
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
			if (i != 0 || ((c != -1) && (s != -1)))
				if (c - s > 2 || s - c > 2) {
					if (!(TacC == c % 7 && TacS == s % 7)
							&& !((TacC + 2) % 7 == c % 7 && (TacS + 2) % 7 == s % 7)
							&& !((TacC + 4) % 7 == c % 7 && (TacS + 4) % 7 == s % 7))
						return false;
				}
		}
		return true;
	}

	private static boolean regle62(Accord acC, Accord acS) {
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
			if (i == 0 && (acS.soprano == -1 || acC.soprano == -1))
				continue;
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
				if (j == 0 && (acS.soprano == -1 || acC.soprano == -1))
					continue;
				if (j != i) {
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

	private static boolean regle3(Accord ac) {
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
			} else if (a == t) {
				if (a == (nac + 2) % 7) {
					if (s == (nac + 4) % 7) {
						return true;
					}
				} else if (a == (nac + 4) % 7) {
					if (s == (nac + 2) % 7) {
						return true;
					}
				}
			}else if (s == -1) {
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
		return false;
	}

	/**
	 * Verifie si la partion commence ou fini par un accord IVb renvoi false si oui
	 * @param part
	 * @return
	 */
	public static boolean regle4(Accord[] part) {
		if (part[0].type == 7 || part[part.length - 1].type == 7) {
			return false;
		}
		return true;
	}

	public static boolean regle5(Accord AcC, Accord AcS) {
		if (AcC.type == 0) {
			if (AcS.type != 7) {
				return true;
			}
		} else if (AcC.type == 1) {
			if (AcS.type == 4 || AcS.type == 6) {
				return true;
			}
		} else if (AcC.type == 2) {
			if (AcS.type != 0 && AcS.type != 7) {
				return true;
			}
		} else if (AcC.type == 3) {
			if (AcS.type != 7) {
				return true;
			}
		} else if (AcC.type == 7) {
			if (AcS.type == 0) {
				return true;
			}
		} else if (AcC.type == 4) {
			if (AcS.type == 0 || AcS.type == 2 || AcS.type == 7
					|| AcS.type == 5) {
				return true;
			}
		} else if (AcC.type == 5) {
			if (AcS.type == 0 || AcS.type == 3 || AcS.type == 4) {
				return true;
			}
		} else if (AcC.type == 6) {
			if (AcS.type == 0 || AcS.type == 2) {
				return true;
			}
		}
		return false;
	}

}