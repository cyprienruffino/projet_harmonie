package accords;

import main.Accord;

public class I extends Accord {

	public I(int s, int a, int t, int b, int d) {
		super(s, a, t, b, d);
	}
	public I clone(){
		I ac=new I (this.getSoprano(),this.getAlto(),this.getTenor(),this.getBasse(),this.getDuree());
		return ac;
	}
}
