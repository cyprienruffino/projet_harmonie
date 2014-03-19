package accords;

import main.Accord;

public class VII extends Accord {

	public VII(int s, int a, int t, int b, int d) {
		super(s, a, t, b, d);
		// TODO Auto-generated constructor stub
	}
	public VII() {
		super();
	}
	public VII clone(){
		VII ac=new VII (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
	public int getAccord(){
		return 7;
	}
}
