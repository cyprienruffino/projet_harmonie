package accords;

import main.Accord;

public class IV extends Accord {

	public IV(int s, int a, int b, int t, int d) {
		super(s, a, b, t, d);
		// TODO Auto-generated constructor stub
	}
	public IV clone(){
		IV ac=new IV (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
}
