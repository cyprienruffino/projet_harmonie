package accords;

import main.Accord;

public class VI extends Accord {

	public VI(int s, int a, int t, int b, int d) {
		super(s, a, t, b, d);
		// TODO Auto-generated constructor stub
	}
	public VI() {
		super();
	}
	public VI clone(){
		VI ac=new VI (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
	public int getAccord(){
		return 6;
	}
}
