package accords;

import main.Accord;

public class III extends Accord {

	public III(int s, int a, int b, int t, int d) {
		super(s, a, b, t, d);
		// TODO Auto-generated constructor stub
	}
	public III clone(){
		III ac=new III (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
}
