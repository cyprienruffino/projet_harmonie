package accords;

import main.Accord;

public class VII extends Accord {

	public VII(int s, int a, int b, int t, int d) {
		super(s, a, b, t, d);
		// TODO Auto-generated constructor stub
	}
	public VII clone(){
		VII ac=new VII (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
}
