import static Sorter.Sorter.SortJava;

import java.util.Random;

public class LSD {
	private static final int arraySize = 100;
	
	static {
		System.loadLibrary("Sorter_Sorter");
	}
	
	private static void FillArray(int mass[]) {
		Random rand = new Random();
		for (int i = 0; i < mass.length; ++i) {
			mass[i] = rand.nextInt(1000000000);
		}
	}
	
	private static void PrintArray(int mass[]) {
		for (int val : mass) {
			System.out.println(val);
		}
	}
	
	private static boolean isSorted(int mass[]) {
		for (int i = 0; i < mass.length - 1; ++i) {
			if (mass[i] > mass[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[] mass = new int[arraySize];
		
		for (int i = 0; i < 1000000000; ++i) {
			FillArray(mass);
			
			SortJava(mass);
			
			if (isSorted(mass)) {
				if (i % 100000 == 0) {
					System.out.println(String.format("test number - %4d was pass!\n", i));
				}
			} else {
				PrintArray(mass);
				System.out.println("fail!");
				System.exit(-1);
			}
		}
		System.exit(0);

	}

}
