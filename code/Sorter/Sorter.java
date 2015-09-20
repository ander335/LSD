package Sorter;

import java.util.Arrays;

public final class Sorter {
	private Sorter() {};
	
	public static native void SortC(int[] mass);
	
	public static void SortJava(int[] mass) {
		final int maxOrder = 9;
		int[] chars = new int[10];
		int[] position = new int[10];
		int[] tmpMass = new int[mass.length];
		
		for (int order = 0; order <= maxOrder; ++order) {
			Arrays.fill(chars, 0);
			for (int val : mass) {
				chars[GetChar(val, order)]++;
			}
			
			position[0] = 0;
			for (int i = 1; i < 10; ++i) {
				position[i] = chars[i - 1] + position[i - 1];
			}
			
			for (int val : mass) {
				tmpMass[position[GetChar(val, order)]++] = val;
			}
			
			System.arraycopy(tmpMass, 0, mass, 0, mass.length);
		}
	}
	
	private static int GetChar(int val, int idx) {
		int divider = 1;
		
		while (idx-- > 0) {
			divider *= 10;
		}
		
		return (val / divider) % 10;
	}
}
