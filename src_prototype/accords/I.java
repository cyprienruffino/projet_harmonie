package accords;

import main.Accord;

public class I extends Accord {

	public I(int s, int a, int b, int t, int d) {
		super(s, a, b, t, d);
	}
	public I clone(){
		I ac=new I (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
}
