package harmonie;
import java.io.*;

public class partatab {
	//OK les gas vue que vous devez payé vos commentaire de code il faudrai que vous mexpliquer
	//deux trois truc. Comme par exemple si je doit attraper l'erreur ou pas ... (^-^)b
	public static int[][] titreenchantier(File f) throws IOException{
		//lecture fichier
		BufferedReader entre = 
				new BufferedReader(
				new FileReader(f));
		//je ne sais pas aller direrctement a la deuxieme ligne ((((((^_^;)
		entre.readLine();
		
		String patt=entre.readLine();
		int n = nbnote(patt);
		int[][] tab = new int [n][9];
		
		for(int i=0;i<n;i++){
			//moi aussi je peut ne pas commenté (O '.')-O Q('.' Q) 
			for(int j = 2;j<9;tab[i][j++]=0);
			String g = patt.substring(i*6,i*6+5);
			if(g.charAt(0)=='s'&&g.charAt(3)=='l')
				g=g+patt.charAt(i*6+6);
			tab[i][0]=petitenote(g);
			if(g.charAt(0)=='s'&&g.charAt(3)=='l')
				tab[i][i]=(int)g.charAt(6);
			else
				tab[i][i]=(int)g.charAt(5);
		}
		
		return tab;
	}
	//combien de note contien la partition sachent que ' ' est le separateur de note ? vous avez 3min
	public static int nbnote(String d){
		int x=0;
		for(int i = 0;i<d.length();i++){
			if(d.charAt(i)==' ')
				x++;
		}
		return x;
	}
	
	public static int petitenote(String s){
		String note = s.substring(0,1);
		int reloud =(int) s.charAt(3);
		if(s.length()==6){
			reloud++;
			return 4+7*(reloud-1);}
		if(note=="do")
			return 0+7*(reloud-1);
		else if(note=="re")
			return 1+7*(reloud-1);
		else if(note=="mi") 
			return 2+7*(reloud-1);
		else if(note=="fa") 
			return 3+7*(reloud-1);
		else if(note=="la")
			return 5+7*(reloud-1);
		else if(note=="si")
			return 6+7*(reloud-1);
		else 
			//et si c'est un temps mort ?
			return 88;
		}
	}

