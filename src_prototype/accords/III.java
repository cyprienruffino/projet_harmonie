package accords;

import main.Accord;

public class III extends Accord {

	public III(int s, int a, int t, int b, int d) {
		super(s, a, t, b, d);
		// TODO Auto-generated constructor stub
	}
	public III() {
		super();
	}
	public III clone(){
		III ac=new III (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
	public int getAccord(){
		return 3;
	}
}
