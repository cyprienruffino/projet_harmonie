package obsolete.indexedDAG;

import java.util.ArrayList;

public abstract class Node{
	
	private ArrayList<Node> next;
	
	public Node() {
		next=new ArrayList<Node>();
	}	
	public void append(Node p){
		next.add(p);
	}
	public ArrayList<Node>next(){
		return next();
	}
	public int sizeOfNext() {
		return next.size();
	}
}
