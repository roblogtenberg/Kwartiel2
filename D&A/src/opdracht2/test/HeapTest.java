package opdracht2.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import opdracht2.FileBeheer;
import opdracht2.Gen;
import opdracht2.HeapBuilder;

public class HeapTest {

	Gen g = new Gen();
	String seperator = "/";
	String fileNameInput = "bestandje.txt";
	String fileNameOutput = "bestandjeoutput.txt";

	@Test
	public void test_heap50_array1000() throws IOException {
		int heapSize = 50;
		int N = 1000; // aantal getallen om te sorteren
		FileBeheer fb = new FileBeheer(fileNameOutput);
		int[] intarrayWrite = g.generate(N);

		fb.writetofile(intarrayWrite, seperator, fileNameInput);
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator);
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile);

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns();
		System.out.println("h50, a1000: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));
	}

	@Test
	public void test_heap50_array10000() throws IOException {
		int heapSize = 50;
		int N = 10000; // aantal getallen om te sorteren
		FileBeheer fb = new FileBeheer(fileNameOutput);
		int[] intarrayWrite = g.generate(N);

		fb.writetofile(intarrayWrite, seperator, fileNameInput);
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator);
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile);

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns();
		System.out.println("h50, a10000: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));
	}

	@Test
	public void test_heap10_array1000() throws IOException {
		int heapSize = 10;
		int N = 1000; // aantal getallen om te sorteren
		FileBeheer fb = new FileBeheer(fileNameOutput);
		int[] intarrayWrite = g.generate(N);

		fb.writetofile(intarrayWrite, seperator, fileNameInput);
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator);
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile);

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns();
		System.out.println("h10, a1000: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));
	}

	@Test
	public void test_heap50_array5000() throws IOException {
		int heapSize = 50;
		int N = 5000; // aantal getallen om te sorteren
		FileBeheer fb = new FileBeheer(fileNameOutput);
		int[] intarrayWrite = g.generate(N);

		fb.writetofile(intarrayWrite, seperator, fileNameInput);
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator);
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile);

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns();
		System.out.println("h50, a5000: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));
	}

	@Test
	public void test_heap10_array5000() throws IOException {
		int heapSize = 10;
		int N = 5000; // aantal getallen om te sorteren
		FileBeheer fb = new FileBeheer(fileNameOutput);
		int[] intarrayWrite = g.generate(N);

		fb.writetofile(intarrayWrite, seperator, fileNameInput);
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator);
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile); //

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns();
		System.out.println("h10, a5000: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));
	}

	@Test
	public void test_heap5_array100() throws IOException {
		int heapSize = 5;
		int N = 100; // aantal getallen om te sorteren
		FileBeheer fb = new FileBeheer(fileNameOutput);
		int[] intarrayWrite = g.generate(N);

		fb.writetofile(intarrayWrite, seperator, fileNameInput);
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator);
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile);

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns();
		System.out.println("h5, a100: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));
	}

	@Test
	public void test_heap5_array1000() throws IOException {
		int heapSize = 5;
		int N = 1000; // aantal getallen om te sorteren
		FileBeheer fb = new FileBeheer(fileNameOutput);
		int[] intarrayWrite = g.generate(N);

		fb.writetofile(intarrayWrite, seperator, fileNameInput);
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator);
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile);

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns();
		System.out.println("h5, a1000: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));
	}

	@Test
	public void test_heap10_array10000() throws IOException {
		int heapSize = 10;
		int N = 10000; // aantal getallen om te sorteren
		FileBeheer fb = new FileBeheer(fileNameOutput);
		int[] intarrayWrite = g.generate(N);

		fb.writetofile(intarrayWrite, seperator, fileNameInput);
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator);
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile);

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns();
		System.out.println("h10, a10000: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));
	}

	@Test
	public void heapTest() throws IOException {
		int heapSize = 10;
		int N = 10000; // aantal getallen om te sorteren
		FileBeheer fb = new FileBeheer(fileNameOutput);
		int[] intarrayWrite = g.generate(N);

		fb.writetofile(intarrayWrite, seperator, fileNameInput);
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator);
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile);

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns();
		System.out.println("h10, a10000: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));
	}
	
	@Test
	public void test_Handmatige_test() throws IOException {
		int heapSize = 3;
		FileBeheer fb = new FileBeheer(fileNameOutput);
		int[] intarrayWrite = { 9, 6, 5, 7, 3, 4, 2, 1, 0, 8 };

		fb.writetofile(intarrayWrite, seperator, fileNameInput);
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator);
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile);

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns();

		ArrayList<Integer> arrayListFromFile2 = fb.getArrayFromFileForTestHeap3Nummers10(fileNameOutput, seperator + " ");
		Integer[] intArray2 = fb.arraylistToArray(arrayListFromFile2);
		String s = new String();
		for (int i = 0; i < intArray2.length; i++) {
			s += intArray2[i] + seperator;
		}
		String verwachteUitkomst = "5/7/6/9/2/3/4/0/1/8/";
		boolean isZelfde = s.equalsIgnoreCase(verwachteUitkomst);
		assertEquals(isZelfde, true);
	}

}
