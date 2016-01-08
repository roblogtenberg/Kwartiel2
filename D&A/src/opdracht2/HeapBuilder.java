package opdracht2;

import java.io.IOException;
import java.util.ArrayList;

public class HeapBuilder {
	private Heap heap;
	private Integer[] input;
	private ArrayList<Integer> lastHeapSorted;
	private FileBeheer fB;
	private int heapSize;
	private int inputFileIndex = 0;
	private ArrayList<String> runFileNames;
	private int aantalRuns;

	public int getRuns() {
		return aantalRuns;
	}

	public HeapBuilder(Integer[] input, int heapSize, FileBeheer fb) {
		this.fB = fb;
		this.heapSize = heapSize;
		this.input = input;
		runFileNames = new ArrayList<String>();
		this.lastHeapSorted = new ArrayList<Integer>();
	}

	public void setHeapSize(int newHeapSize) {
		heapSize = newHeapSize;
	}

	public ArrayList<String> getRunFileNames() {
		return runFileNames;
	}

	/**
	 * bouwt een heap
	 * 
	 * @return geeft de nieuwe heap
	 */
	public Heap buildHeap() {
		Heap oldHeap = heap;
		Heap newHeap = new Heap(heapSize);

		int elementsInserted = 0;

		if (oldHeap != null) { // kijken of het de eerste heap is, bij eerste heap is de oldheap namelijk null omdat er nog geen gevulde heap was
			for (int i = oldHeap.size() + 1; i < oldHeap.getHeapArray().length; i++) { // check voor deadspace
				newHeap.insert(oldHeap.getHeapArray()[i]);
				elementsInserted++;
			}
		}

		for (int i = elementsInserted; i < heapSize; i++) {
			newHeap.insert(getNext());
			elementsInserted++;
		}
		return newHeap;
	}

	/**
	 * start het programma
	 * 
	 * @throws IOException
	 *             vanwege gebruik van bestanden
	 */
	public void createRuns() throws IOException {
		 while (!allesGelezen()) {
			heap = buildHeap();
			aantalRuns++;

			// while checkt voor of er nog elementen zijn om te lezen en of er
			// nog wat in de heap zitten
			while (heap.size() > -1 && !allesGelezen()) {
				int next = getNext();
				lastHeapSorted.add(heap.getMin());
				if (next >= heap.getMin()) { // check of nieuwe getal kleiner is dan huidige root
					// System.out.println("heap getmin");
					heap.setMin(next); // nieuw getal als root zetten
				} else {
					heap.setElement(0, heap.getElement(heap.size()));
					// als laatste kleiner is dan de root zet laatste als root
					heap.setElement(heap.size(), next); // zet nieuw element op plek van laatste
					heap.moveLastToDeadspace(); // zeg dat laatste de deadspace is
				}
				// heap opnieuw bouwen
				heap.naarBeneden(0);
			}
			fB.writeArrayToFile(lastHeapSorted); // schrijf array naar file
			lastHeapSorted.clear();
		}
		// Haalt een kopie op van de laatste heap in het geheugen
		Heap restHeap = heap.getheap();
		// schrijft de laatste heap naar het geheugen
		fB.writeLastHeapToFile(restHeap);
 	}

	/**
	 * haalt het volgende getal uit de reeks
	 * 
	 * @return het nieuwe getal
	 */
	public int getNext() {
		int tmpInt = input[inputFileIndex];
		inputFileIndex++;
		return tmpInt;
	}

	/**
	 * controleerd of een bestand helemaal is gelezen
	 * 
	 * @return true or false
	 */
	public boolean allesGelezen() {
		if (inputFileIndex == input.length) {
			return true;
		} else {
			return false;
		}
	}
}