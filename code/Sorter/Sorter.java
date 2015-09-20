package Sorter;

import java.util.Arrays;

public final class Sorter {
	private Sorter() {};
	
	public static native void NativeLSD(int[] mass);
	public static native void NativeQuick(int[] mass);
	
	public static void LSD(int[] mass) {
		final int maxOrder = 1;
		final int countChars = 1 << 16;
		
		int[] chars = new int[countChars];
		int[] tmpMass = new int[mass.length];
		
		for (int order = 0; order <= maxOrder; ++order) {
			Arrays.fill(chars, 0);
			for (int val : mass) {
				chars[GetChar(val, order)]++;
			}
			
			for (int i = 0, pos = 0; i < countChars; ++i) {
				int tmp = chars[i];
				chars[i] = pos;
				pos += tmp;
			}
			
			for (int val : mass) {
				tmpMass[chars[GetChar(val, order)]++] = val;
			}
			
			System.arraycopy(tmpMass, 0, mass, 0, mass.length);
		}
	}
	
	private static int GetChar(int val, int idx) {
		return (val >> (16 * idx)) & 0xFFFF;
	}
}
