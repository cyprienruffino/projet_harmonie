package obsolete;

import obsolete.indexedDAG.*;

public class ComptageStrategy implements SearchStrategy {

	public int count(Node n) {
		int compteur=(n.next().size());
		if(compteur>0)
			compteur--;
		return compteur;
	}
	
}
