package Sorter;

public final class Sorter {
	static {
		System.loadLibrary("Sorter_Sorter");
	}
	private Sorter() {};
	
	public static native void SortC(int[] mass);
	public static void SortJava(int[] mass) { 
		mass[0] = 104362;
	}
}
