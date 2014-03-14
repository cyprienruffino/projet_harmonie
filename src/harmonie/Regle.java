package harmonie;

public class Regle {
	/*
	 * Remarque: tierce=tonic+2 quinte=tonic+4=tierce+2
	 */
	
	public static boolean noteCorrect(int[] note) {// renvoi true si la note est
											// correcte
		if (regle1(note) && regle2(note) && regle3(note)) {
			return true;
		}
		return false;
	}

	public static boolean enchainementCorrect(int[] noteC, int[] noteS) {
		if (regle5(noteC[8], noteS[8]) && regle6(noteC, noteS)) {
			return true;
		}
		return false;
	}

	public static boolean regle2(int[] note) {
		if (note[6] < note[4] && note[4] < note[2] && note[2] < note[0]) {
			return true;
		}
		return false;
	}

	public static boolean regle1(int[] note) {//faire une variante soprano pour corentin
		if (13 < note[0] && note[0] < 27 && 10 < note[2] && note[2] < 23
				&& 6 < note[4] && 2 < note[6] && note[6] < 16) {
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

	public static boolean regle3(int[] note) {
		if (note[8] != -1) {
			if (nature(note[6], note[8]) == 1) {
				if (nature(note[0], note[8]) == 2
						|| nature(note[0], note[8]) == 3) {
					if (nature(note[2], note[8]) == 2
							|| nature(note[2], note[8]) == 3) {
						if (nature(note[4], note[8]) == 2
								|| nature(note[4], note[8]) == 3) {
							return true;
						}
					}
				}
			}
		}
		return false;

	}

	public static boolean regle4(int[] partition) {
		int[] ac = Encoder.decode(partition[0]);
		int[] acf = Encoder.decode(partition[partition.length - 1]);
		if (ac[8] != 4 || acf[8] != 4) {
			return true;
		}
		return false;
	}

	public static boolean regle5(int AcC, int AcS) {
		switch (AcC) {
		case (0):
			if (AcS != 4) {
				return true;
			}
		case (1):
			if (AcS == 5 || AcS == 7) {
				return true;
			}
		case (2):
			if (AcS != 0 && AcS != 4) {
				return true;
			}
		case (3):
			if (AcS != 4) {
				return true;
			}
		case (4):
			if (AcS == 1) {
				return true;
			}
		case (5):
			if (AcS == 0 || AcS == 2 || AcS == 4 || AcS == 6) {
				return true;
			}
		case (6):
			if (AcS == 0 || AcS == 2 || AcS == 3 || AcS == 5) {
				return true;
			}
		case (7):
			if (AcS == 0 || AcS == 2) {
				return true;
			}
		}
		return false;
	}

	public static boolean apartient(int i, int[] note, int[] ac) {// verifie si la note
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
}