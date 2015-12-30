package opdracht2;

import java.io.IOException;
import java.util.ArrayList;

public class MainClass {
	public static void main(String[] args) throws IOException {
		Gen g = new Gen();
		int heapSize = 3;
		// aantal getallen om te sorteren
		int N = 20;
		String seperator = "/"; // backslash(\) als escape, dus bij een backslash moet je er 2 in zetten
		String fileNameInput = "bestandje.txt";
		String fileNameOutput = "bestandjeoutput.txt";
		FileBeheer fb = new FileBeheer(fileNameOutput); // genereerd reeks met getallen van lengte N
		int[] intarrayWrite = g.generate(N);

		fb.writetofile(intarrayWrite, seperator, fileNameInput); // maakt de reeks met getallen en zet deze in een file
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator); // maakt een arraylist uit de getallen in de file en geeft deze terug
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile); // zet de arraylist om naar een array

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns(); // zorgt voor de runs en het tellen ervan
	}
}
