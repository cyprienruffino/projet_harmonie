package harmonie;

public class Regle {



boolean ordre(int [] tab){
	if(tab[6]<tab[4] && tab[4]<tab[2] && tab[2]<tab[0]){//verifie si les differentes note d'un accord sont bien ordonée B<T<A<S
		return true;
	}
	return false;
}



boolean interval(int[]tab){//verifife si les note de chaque table(soprano, alto, tenor, basse) sont dans le bon intervale
	if(13<tab[0] && tab[0]<27 && 10<tab[2] && tab[2]<23 && 6<tab[4] && 2<tab[6] && tab[6]<16){
		return true;
	}
	return false;
}

boolean enchainement_possible(int []noteC, int []noteP){//verifie si deux note ce suivant sont de le bon interval +-6
	if((noteP[0]-noteC[0])<7 && (noteC[0]-noteP[0])<7){
		if((noteP[2]-noteC[2])<7 && (noteC[2]-noteP[2])<7){
			if((noteP[4]-noteC[4])<7 && (noteC[4]-noteP[4])<7){
				if((noteP[6]-noteC[6])<7 && (noteC[6]-noteP[6])<7){
					return true;
				}
			}
		}
	}
	return false;
}

boolean checkAc (int[]note, int[]ac){//verifie si l'accord est valide
	if((note[6]%7)==(ac[0]%7)){
		if((note[0]%7)==(ac[1]%7)||(note[2]%7)==(ac[1]%7)||(note[4]%7)==(ac[1]%7)){
			if((note[0]%7)==(ac[4]%7)||(note[2]%7)==(ac[3]%7)||(note[4]%7)==(ac[2]%7)){
				return true;
			}
		}
	}
	return false;
}



}