package opdracht2;

import java.util.Arrays;

public class RSHeap<T extends Comparable<T>> {
	private Object[] items;
	private int deadspaceStart;
	private int heapLength;

	public RSHeap(int amountItems) {
		items = new Object[amountItems];
		deadspaceStart = amountItems;
	}

	/**
	 * if the item is smaller as the item that was last removed from the list the item will be stored in the death space otherwise it will be stored in the heap
	 * 
	 * @param item
	 * @throws Exception
	 */
	public void insert(T item) throws Exception {
		// alleen tevoegen als de boom niet vol is.
		if (!isFull()) {
			// kijken of het item groter is als het laatst verwijderde item
			if (items[0] == null || item.compareTo((T) items[0]) >= 0) {
				// kan toevoegen in heap.
				insertInHeap(item); // in heap !
			} else {
				// moet worden toegevoegd in deadspace.s
				insertInDeadSpace(item);
			}
		} else {
			throw new Exception();
		}
	}

	private void insertInHeap(T item) {
		// volgende item moet null zijn anders maken we een fout
		assert (items[heapLength + 1] == null);

		// onderaan toevoegen
		int itemPos = heapLength + 1;
		items[itemPos] = item;

		// zover mogelijk naar boven laten komen
		tryPercolateUp(itemPos);

		// heap is groter geworden
		heapLength++;

	}

	public void insertInDeadSpace(T item) throws Exception {
		if (isFull()) {
			throw new Exception();
		}

		// deadspace begint nu eerder
		deadspaceStart = deadspaceStart - 1;

		// item voor bestaande deadspace plakken.
		items[deadspaceStart] = item;
	}

	/**
	 * if there are items in the list the first item will be removed and returned it will return null if the heap is empty, it might be that the deadspace has consumed all space;
	 * 
	 * @return the first item of the list or null
	 */
	public T deleteNext() {
		// heap is leeg
		if (heapIsEmpty()) {
			return null;
		}
		// laatst verwijderde item aanpassen
		items[0] = items[1];

		// item verwijderen
		items[1] = null;

		// gat opvullen, had percolate down kunnen gebruiken maar die gebruikt
		// recursie via trypercolatedown en die methode vergelijkt en dan krijg
		// je een nullpointerexeption
		fillGap(1);

		// heaplength is kleiner;
		heapLength--;

		return (T) items[0];
	}

	/**
	 * fills a gap at the given position
	 */
	public void fillGap(int itemPos) {
		// positie moet van heap zijn
		assert (itemPos >= deadspaceStart);

		// geen items om mee op te vullen
		if ((itemPos * 2 + 1 >= deadspaceStart || items[itemPos * 2 + 1] == null) && (itemPos * 2 >= deadspaceStart || items[itemPos * 2] == null)
				&& (itemPos + 1 >= deadspaceStart || items[itemPos + 1] == null)) {

		} else {

			// wel items controleren of we childeren hebben
			if ((itemPos * 2 + 1 >= deadspaceStart || items[itemPos * 2 + 1] == null) && (itemPos * 2 >= deadspaceStart || items[itemPos * 2] == null) && itemPos + 1 < deadspaceStart) {

				// geen childeren een item van de rechterkant pakken kijken of
				// hij hier past
				items[itemPos] = items[itemPos + 1];
				items[itemPos + 1] = null;
				fillGap(itemPos + 1);
				tryPercolateUp(itemPos);

			} else if (itemPos * 2 + 1 < deadspaceStart && items[itemPos * 2 + 1] != null) {

				// meerdere childeren kijken welke groter is en daarmee vullen
				if (((T) items[itemPos * 2]).compareTo((T) items[itemPos * 2 + 1]) < 0) {
					items[itemPos] = items[itemPos * 2];
					items[itemPos * 2] = null;
					fillGap(itemPos * 2);
				} else {
					items[itemPos] = items[itemPos * 2 + 1];
					items[itemPos * 2 + 1] = null;
					fillGap(itemPos * 2 + 1);
				}
			} else {
				// een enkele child hem pakken
				items[itemPos] = items[itemPos * 2];
				items[itemPos * 2] = null;
			}
		}
	}

	/**
	 * checks of this item is smaller as its parents and then swaps it if needed
	 * 
	 * @param itemPos
	 *            the position of the item to be swapped
	 */
	public void tryPercolateUp(int itemPos) {
		int checkpos = itemPos / 2;
		// zolang we niet boven doorgaan.
		while (checkpos > 0) {
			// als parent groter is omdraaien.
			if (items[checkpos] != null && ((T) items[checkpos]).compareTo((T) items[itemPos]) > 0) {

				percolateUp(itemPos);
				itemPos = checkpos;
				checkpos = itemPos / 2;
			} else {
				// parent is kleiner daarboven dus ook, stoppen.
				break;
			}
		}
	}

	public void tryPercolateDown(int itemPos) {
		// als we een rechter kind hebben(end dus ook een linker) controleren of
		// een kleiner is en dan omruilen
		if (itemPos * 2 + 1 < deadspaceStart && items[itemPos * 2 + 1] != null) {
			if (((T) items[itemPos * 2 + 1]).compareTo((T) items[itemPos]) < 0 || ((T) items[itemPos * 2]).compareTo((T) items[itemPos]) < 0) {
				percolateDown(itemPos);
			}
		} else
		// controleren of we misschien maar 1 kind hebben en die kleiner is.
		if (itemPos * 2 < deadspaceStart && items[itemPos * 2] != null) {
			if (((T) items[itemPos * 2]).compareTo((T) items[itemPos]) < 0) {
				percolateDown(itemPos);
			}
		}

	}

	/**
	 * swaps the given item with its parent position
	 * 
	 * @param itemPos
	 *            the position of the item to be swapped
	 */
	private void percolateUp(int itemPos) {
		// omruilen met de bovenste positie;
		Object temp = items[itemPos / 2];
		items[itemPos / 2] = items[itemPos];
		items[itemPos] = temp;
	}

	/**
	 * swaps the given item with its smallest child
	 * 
	 * @param itemPos
	 *            the position of the item to be swapped
	 */
	private void percolateDown(int itemPos) {
		// kijken of we twee kinderen hebben.
		if (itemPos * 2 < deadspaceStart && items[itemPos * 2] != null && itemPos * 2 + 1 < deadspaceStart && items[itemPos * 2 + 1] != null) {
			// kijken welk kind kleiner is.
			if (itemPos * 2 + 1 < deadspaceStart && items[itemPos * 2 + 1] != null && ((T) items[itemPos * 2]).compareTo((T) items[itemPos * 2 + 1]) < 0) {
				// omruilen
				Object item = items[itemPos * 2];
				items[itemPos * 2] = items[itemPos];
				items[itemPos] = item;

				// kijken of hij nog verder naar beneden kan
				tryPercolateDown(itemPos * 2);
			} else {
				// omruilen
				Object item = items[itemPos * 2 + 1];
				items[itemPos * 2 + 1] = items[itemPos];
				items[itemPos] = item;

				// kijken of hij nog verder naar beneden kan
				tryPercolateDown(itemPos * 2 + 1);
			}
		} else if (itemPos * 2 < deadspaceStart && items[itemPos * 2] != null) {
			// omruilen
			Object item = items[itemPos * 2];
			items[itemPos * 2] = items[itemPos];
			items[itemPos] = item;
			// hoeven niet te controleren of hij nog verder kan, kan alleen
			// voorkomen op onderste rij
		}
	}

	public void buildMinHeap() {
		// items uit deadspace naar voren halen, in de meeste gevallen zullen ze
		// al vooraan staan omdat hij vol zit
		for (int i = heapLength + 1; i <= deadspaceStart - 1; i++) {
			items[i] = items[items.length - i];
			items[items.length - i] = null;
		}

		// laatst verwijderde item resetten
		items[0] = null;

		// heaplength en deadspaceStart omdraaien
		heapLength = items.length - deadspaceStart;
		deadspaceStart = items.length;

		// alle items vanaf de helft proberen omlaag te duwen.
		for (int a = heapLength / 2; a > 0; a--) {
			tryPercolateDown(a);
		}

	}

	/**
	 * @return true if there is no room for another item
	 */
	public boolean isFull() {
		return deadspaceStart - 1 == heapLength;
	}

	/**
	 * 
	 * @return true if heap and deadspace are both empty
	 */
	public boolean isEmpty() {
		return heapLength == 0 && deadspaceStart == items.length;
	}

	public boolean heapIsEmpty() {
		// kleiner dan zou eigenlijk niet voor mogen komen
		return heapLength <= 0;
	}

	public boolean deadspaceIsEmpty() {
		// groter dan zou eingelijk niet voor mogen komen
		return deadspaceStart >= items.length;
	}

	@Override
	public String toString() {
		return Arrays.toString(items) + " deadspace starts at: " + deadspaceStart;
	}
}
