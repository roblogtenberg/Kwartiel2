package opdracht2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FileBeheer {
	private String fileName;
	private BufferedWriter bwriter;

	public FileBeheer(String fileName) throws IOException {
		this.fileName = fileName;
		this.bwriter = new BufferedWriter(new FileWriter(new File(fileName)));
	}

	/**
	 * schrijft de array met N aantal waardes ongesorteerd naar een bestand
	 * 
	 * @param intArray
	 *            de array met de waardes
	 * @param seperator
	 *            de seperator tussen de waardes
	 * @param fileName
	 *            de naam van het bestand waar het heen moet worden geschreven
	 * @throws IOException
	 *             om foutmelding af te vangen
	 */
	public void writetofile(int[] intArray, String seperator, String fileName) throws IOException {
		String s = new String();
		for (int i = 0; i < intArray.length; i++) {
			s += intArray[i] + seperator;
		}
//		System.out.println(s);
		FileWriter fw = new FileWriter(fileName);
		fw.write(s);
		fw.close();
	}

	/**
	 * haalt het array weer uit de filename
	 * 
	 * @param fileName
	 *            bestandsnaam
	 * @param seperator
	 *            seperator van de getallen in het bestand
	 * @return geeft arraylist terug
	 * @throws IOException
	 *             vanwege gebruik van bestanden
	 */
	public ArrayList<Integer> getArrayFromFile(String fileName, String seperator) throws IOException {
		ArrayList<Integer> array = new ArrayList<Integer>();
		FileReader fr = new FileReader(fileName);

		Scanner scanner = new Scanner(fr);
		scanner.useDelimiter(seperator);

		while (scanner.hasNext()) {
			array.add(Integer.parseInt(scanner.next()));
		}
		return array;
	}

	/**
	 * dit is voor een nieuwe run, laatste reeks moet naar file worden geschreven
	 * 
	 * @param intArray
	 *            array om naar file te schrijven
	 * @throws IOException
	 */
	public void writeArrayToFile(ArrayList<Integer> intArray) throws IOException {
		for (int i = 0; i < intArray.size(); i++) {
			bwriter.write(Integer.toString(intArray.get(i)) + "/ ");
		}
		bwriter.newLine();
		bwriter.flush();
	}

	/**
	 * schrijft de restheap
	 * 
	 * @param h
	 *            de heap
	 * @throws IOException
	 *             vanwge werken met bestanden
	 */
	public void writeLastHeapToFile(Heap h) throws IOException {
		// op het einde blijft er een heap over, deze moet ook worden
		// weggeschreven
		int[] intArray = h.getHeapArray();
		for (int x = 0; x < intArray.length; x++) {
//			System.out.println("getal: " + intArray[x]);
		}
		// System.out.println("heapsize: " + intArray.length);
		for (int i = 0; i < intArray.length; i++) {
			int kleinsteGetal = Integer.MAX_VALUE;
			int positie = 0;
			for (int j = 0; j < intArray.length; j++)
			// laatste getal van deze heap is een 0, deze mag niet worden meegenomen
			{
				int getal = intArray[j];
				if (getal < kleinsteGetal) {
					kleinsteGetal = getal;
					positie = j;
				}
			}
			intArray[positie] = Integer.MAX_VALUE;
			// System.out.println("geschreven: " + kleinsteGetal);
			bwriter.write(Integer.toString(kleinsteGetal) + "/ ");
			// kleinste getal moet vooraan komen bij het wegschrijven op de regel
		}
		bwriter.newLine();
		bwriter.flush();
	}

	/**
	 * zet een arraylist om in en array
	 * 
	 * @param arraylist
	 *            arraylist dat een array moet worden
	 * @return array met inegers
	 */
	public Integer[] arraylistToArray(ArrayList<Integer> arraylist) {
		Integer[] intArray = (Integer[]) arraylist.toArray(new Integer[arraylist.size()]);
		return intArray;
	}

	public ArrayList<Integer> getArrayFromFileForTestHeap3Nummers10(String fileName, String seperator) throws IOException {
		ArrayList<Integer> array = new ArrayList<Integer>();
		FileReader fr = new FileReader(fileName);

		Scanner scanner = new Scanner(fr);
		scanner.useDelimiter(seperator);

		while (scanner.hasNextInt()) {
			array.add(Integer.parseInt(scanner.next()));
		}
		scanner.nextLine();
		while (scanner.hasNextInt()) {
			array.add(Integer.parseInt(scanner.next()));
		}
		scanner.nextLine();
		while (scanner.hasNextInt()) {
			array.add(Integer.parseInt(scanner.next()));
		}
		return array;
		// getallen die we invoeren heeft 3 regels dus voor het gemak en
		// snelheid van de test maken gewoon zo slordig 3 keer een while gemaakt
	}
}
