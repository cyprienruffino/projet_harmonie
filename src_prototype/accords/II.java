package accords;

import main.Accord;

public class II extends Accord {

	public II(int s, int a, int t, int b, int d) {
		super(s, a, t, b, d);
		// TODO Auto-generated constructor stub
	}
	public II() {
		super();
	}
	public II clone(){
		II ac=new II (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
	public int getAccord(){
		return 2;
	}
}
