package accords;

import main.Accord;

public class V extends Accord {

	public V(int s, int a, int t, int b, int d) {
		super(s, a, t, b, d);
		// TODO Auto-generated constructor stub
	}
	public V clone(){
		V ac=new V (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
}
