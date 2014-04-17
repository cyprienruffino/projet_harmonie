package obsolete.graphes;

import java.util.ArrayList;
import java.util.Iterator;

class Index<Parameter> implements Iterable<Parameter>{
	private ArrayList<Node<Parameter>> list;
	
	public Index(){
		list=new ArrayList<Node<Parameter>>();
	}
	
	public void add(Parameter p){
		list.add(new Node(p));
	}
	public Parameter get(int index){
		return list.get(index).value();
	}
	public void remove(int index){
		list.remove(index);
	}
	
	
	public IndexIterator iterator(){
		return new IndexIterator(this);
	}
	private class IndexIterator implements Iterator{
		Iterator<Node> it;
		
		IndexIterator(Index<Parameter> i){
			it=i.iterator();
		}
		
		public boolean hasNext(){
			return it.hasNext();
		}
		public Parameter next(){
			return (Parameter) it.next().value();
		}
		public void remove() {
			it.remove();
		}
	}
}
