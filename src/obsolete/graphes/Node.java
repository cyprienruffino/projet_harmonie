package obsolete.graphes;

import java.util.ArrayList;

class Node<Parameter>{
	
	private Parameter value;
	private ArrayList<Parameter> next;
	
	public Node(Parameter param) {
		next=new ArrayList<Parameter>();
		value=param;
	}
	
	public void addNext(Parameter p){
		next.add(p);
	}
	public Parameter value(){
		return value;
	}
	public ArrayList<Parameter>next(){
		return next();
	}
}
