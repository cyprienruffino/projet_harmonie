package accords;

import main.Accord;

public class IVb extends Accord {

	public IVb(int s, int a, int t, int b, int d) {
		super(s, a, t, b, d);
		// TODO Auto-generated constructor stub
	}
	public IVb() {
		super();
	}
	public IVb clone(){
		IVb ac=new IVb (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
	public int getAccord(){
		return 4;
	}
	public boolean isB(){
		return true;
	}
}
