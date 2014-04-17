package obsolete.graphes;

import java.util.ArrayList;
import java.util.Iterator;

public class IndexedGraph<Parameter> {
	public Index<Parameter>[] array;
	
	public IndexedGraph(int size){
		array=new Index[size];
		for(int i=0;i<size;i++)
			array[i]=new Index<Parameter>();
	}
	
	public void add(Parameter param,int index){
		array[index].add(param);
	}
	public Parameter get(int rank, int index){
		return array[rank].get(index);
	}
}
