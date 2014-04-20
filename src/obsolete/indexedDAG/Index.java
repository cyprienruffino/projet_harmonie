package obsolete.indexedDAG;

import java.util.ArrayList;
import java.util.Iterator;

public class Index implements Iterable<Node>{
	private ArrayList<Node> nodeList;
	
	public Index(){
		nodeList=new ArrayList<Node>();
	}
	
	public void add(Node p){
		nodeList.add(p);
	}
	public Node get(int index){
		return nodeList.get(index);
	}
	public void remove(int index){
		nodeList.remove(index);
	}
	public void remove(Node p){
		nodeList.remove(p);
	}
	
	public Iterator<Node> iterator(){
		return nodeList.iterator();
	}
	public int size() {
		return nodeList.size();
	}

	public int sizeOfNext(int rank) {
		return nodeList.get(rank).sizeOfNext();
	}

	public ArrayList<Node> getArray() {
		return nodeList;
	}
}
