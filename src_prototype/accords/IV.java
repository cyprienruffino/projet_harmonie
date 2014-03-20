package accords;

import main.Accord;

public class IV extends Accord {

	public IV(int s, int a, int t, int b, int d) {
		super(s, a, t, b, d);
		// TODO Auto-generated constructor stub
	}
	public IV() {
		super();
	}
	public IV clone(){
		IV ac=new IV (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
	public int getAccord(){
		return 4;
	}
}
