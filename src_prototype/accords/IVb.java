package accords;

import main.Accord;

public class IVb extends Accord {

	public IVb(int s, int a, int b, int t, int d) {
		super(s, a, b, t, d);
		// TODO Auto-generated constructor stub
	}
	public IVb clone(){
		IVb ac=new IVb (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
}
