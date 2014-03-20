package obsolete;

public class Encoder {
	public static int encoder(int[] notes) {
		int encode = 0;
		for (int i = 0; i < 8; i = i + 2) {
			encode <<= 5;
			encode += notes[i];
			encode <<= 2;
			encode += notes[i + 1];
		}
		encode <<= 4;
		encode += notes[8];
		return encode;
	}

	public static int[] decode(int code) {
		int[] notes = new int[9];
		notes[8] = getIntFromNBits(code, 4);
		code >>>= 4;
		for (int i = 7; i >= 0; i = i - 2) {
			notes[i] = getIntFromNBits(code, 2);
			code >>>= 2;
			notes[i - 1] = getIntFromNBits(code, 5);
			code >>>= 5;
		}
		return notes;
	}

	private static int getBit(int n, int k) {
		return (n >> k) & 1;
	}

	private static int getIntFromNBits(int num, int e) {
		int res = 0;
		for (int i = 0; i < e; i++) {
			res += getBit(num, i) * Math.pow(2, i);
		}
		return res;
	}
}
