package opdracht2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainClass {
	public static void main(String[] args) throws IOException {
		heap20Array100Random();
	}
	
	public static void heap20Array50() throws IOException {
		int heapSize = 20;
		String seperator = "/"; 
		String fileNameInput = "bestandje.txt";
		String fileNameOutput = "bestandjeoutput.txt";
		FileBeheer fb = new FileBeheer(fileNameOutput); 
		
		int[] array = new int[50];
		for(int i = 0; i < 50; i++) {
			array[i] = i + 1;
		}

		fb.writetofile(array, seperator, fileNameInput); 
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator); 
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile); 

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns(); 
		System.out.println("h20, a50: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));		
	}

	public static void heap20Array50Descending() throws IOException {
		int heapSize = 20;
		String seperator = "/"; 
		String fileNameInput = "bestandje.txt";
		String fileNameOutput = "bestandjeoutput.txt";
		FileBeheer fb = new FileBeheer(fileNameOutput); 
		
		int[] array = new int[50];
		for(int i = 49; i >= 0; i--) {
			array[i] = i + 1;
		}

		fb.writetofile(array, seperator, fileNameInput); 
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator); 
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile); 

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns(); // zorgt voor de runs en het tellen ervan
		System.out.println("h20, a50: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));		
	}

	public static void heap20Array100Random() throws IOException {
		int heapSize = 20;
		String seperator = "/"; 
		String fileNameInput = "bestandje.txt";
		String fileNameOutput = "bestandjeoutput.txt";
		FileBeheer fb = new FileBeheer(fileNameOutput); 
		
		Random r = new Random();
		int[] array = new int[100];
		int tempNumber;
		int random;
		for (int i = 0; i < 100; i++) {
			random = r.nextInt(i + 1);
			array[i] = i;
			tempNumber = array[i];
			array[i] = array[random];
			array[random] = tempNumber;

		}


		fb.writetofile(array, seperator, fileNameInput); 
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator); 
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile); 

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns(); // zorgt voor de runs en het tellen ervan
		System.out.println("h20, a100: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));		
	}

	public static void heap50Array20Random() throws IOException {
		int heapSize = 20;
		String seperator = "/"; 
		String fileNameInput = "bestandje.txt";
		String fileNameOutput = "bestandjeoutput.txt";
		FileBeheer fb = new FileBeheer(fileNameOutput); 
		
		Random r = new Random();
		int[] array = new int[20];
		int tempNumber;
		int random;
		for (int i = 0; i < 20; i++) {
			random = r.nextInt(i + 1);
			array[i] = i;
			tempNumber = array[i];
			array[i] = array[random];
			array[random] = tempNumber;
		}


		fb.writetofile(array, seperator, fileNameInput); 
		ArrayList<Integer> arrayListFromFile = fb.getArrayFromFile(fileNameInput, seperator); 
		Integer[] intArray = fb.arraylistToArray(arrayListFromFile); 

		HeapBuilder hb = new HeapBuilder(intArray, heapSize, fb);
		hb.createRuns(); // zorgt voor de runs en het tellen ervan
		System.out.println("h20, a50: runs: " + hb.getRuns() + " . Verwachte runs: " + ((intArray.length / heapSize) / 2));		
	}
	
}
