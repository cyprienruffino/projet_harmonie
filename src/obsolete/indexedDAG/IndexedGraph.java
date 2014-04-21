package obsolete.indexedDAG;


import java.util.ArrayList;
import java.util.Iterator;
/**
 * 
 * Structure de données modulaire abandonnée pour faute de temps
 *
 */
public class IndexedGraph {
	public Index[] array;
	
	public IndexedGraph(int size){
		array=new Index[size];
		for(int i=0;i<size;i++)
			array[i]=new Index();
	}
	
	public void add(Node param,int index){
		array[index].add(param);
	}
	public void addList(ArrayList<Node> list, int index) {
		for(Node param:list){
			array[index].add(param);
		}
	}
	public void addNext(int index, Node fils){
		
	}
	public Node get(int rank, int index){
		return array[rank].get(index);
	}
	public ArrayList<Node> getArray(int index){
		return array[index].getArray();
	}
	
	
	public int deepFirstSearch(SearchStrategy strategy){
		int counter=0;
		for(Node son:array[0]){
			counter+=this.deepFirstSearch(strategy, son);
		}
		return counter;
	}
	private int deepFirstSearch(SearchStrategy strategy, Node father){
		int acc=strategy.count(father);
		Iterator<Node> it=father.next().iterator();
		while(it.hasNext()){
			Node son=(Node) it.next();
			acc+=this.deepFirstSearch(strategy, son);
		}
		return acc;
	}
	public Iterator<Node> iterator(int index){
		return array[index].iterator();
	}
	public int sizeOfNext(int index, int rank) {
		return array[index].sizeOfNext(rank);
	}

	public void remove(Node p, int index) {
		array[index].remove(p);
		
	}	
}

