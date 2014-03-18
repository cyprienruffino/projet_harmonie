package accords;

import main.Accord;

public class V extends Accord {

	public V(int s, int a, int b, int t, int d) {
		super(s, a, b, t, d);
		// TODO Auto-generated constructor stub
	}
	public V clone(){
		V ac=new V (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
}
