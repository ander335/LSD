import static Sorter.Sorter.SortJava;
import static Sorter.Sorter.SortC;

public class LSD {
	
	public static void main(String[] args) {
		System.out.println("Helloy World!");
		
		int[] mass = new int[10];
		
		SortJava(mass);
		System.out.println(mass[0]);
		SortC(mass);
		System.out.println(mass[0]);
		
		long time = System.nanoTime();
		SortC(mass);
		System.out.println((System.nanoTime() - time) / 1000000.);
	}

}
