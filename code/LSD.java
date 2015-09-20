import static Sorter.Sorter.LSD;
import static Sorter.Sorter.NativeLSD;
import static Sorter.Sorter.NativeQuick;

import java.util.Arrays;
import java.util.Random;

public class LSD {
	private static final int arraySize = 1000000;
	private static final Random rand = new Random();
	private static final boolean isNativeEnabled;
	
	static {
		String osName = System.getProperty("os.name");
		String osArch = System.getProperty("os.arch");

		isNativeEnabled = osName.startsWith("Windows") && osArch.equals("amd64");
		
		if (isNativeEnabled) {
			System.loadLibrary("Sorter_Sorter");
		}
	}
	
	private static void FillArray(int mass[]) {
		for (int i = 0; i < mass.length; ++i) {
			mass[i] = rand.nextInt(2000000000);
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
		final int countTests = 10;
		int[] mass = new int[arraySize];
		long time;
			
		time = System.nanoTime();
		for (int i = 0; i < countTests; ++i) {
			FillArray(mass);
			Arrays.sort(mass);	
		}
		time = System.nanoTime() - time;
		System.out.println(String.format("System Sort time   - %.5f", time / 1000000000. / countTests));
		
		time = System.nanoTime();
		for (int i = 0; i < countTests; ++i) {
			FillArray(mass);
			LSD(mass);		
		}
		time = System.nanoTime() - time;
		System.out.println(String.format("Java LSD time      - %.5f", time / 1000000000. / countTests));
		
		if (isNativeEnabled) {
			time = System.nanoTime();
			for (int i = 0; i < countTests; ++i) {
				FillArray(mass);
				NativeLSD(mass);		
			}
			time = System.nanoTime() - time;
			System.out.println(String.format("Native LSD time    - %.5f", time / 1000000000. / countTests));
			
			time = System.nanoTime();
			for (int i = 0; i < countTests; ++i) {
				FillArray(mass);
				NativeQuick(mass);		
			}
			time = System.nanoTime() - time;
			System.out.println(String.format("Native Quick time  - %.5f", time / 1000000000. / countTests));
		}
	}
}
