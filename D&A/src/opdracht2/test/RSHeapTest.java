package opdracht2.test;

import static org.junit.Assert.fail;
//import opdracht2.OverflowExeption;
import opdracht2.RSHeap;

import org.junit.Test;

public class RSHeapTest {

	@Test
	public void testInsert() {
		RSHeap<Integer> heap = new RSHeap<>(30 + 1);

		for (int i = 0; i < 30; i++) {
			try {
				heap.insert((int) (Math.random() * 30 * 2));
			} catch (Exception e) {
				fail("had wel moeten inserten");
				e.printStackTrace();
			}
		}
		int item;
		int vorigItem = -1;
		for (int i = 0; i < 30; i++) {

			if ((item = heap.deleteNext()) >= vorigItem) {
				vorigItem = item;
			} else {
				fail("niet in de goede volgorde");
			}
		}
	}

	@Test
	public void testBuildMinHeap() {
		RSHeap<Integer> heap = new RSHeap<>(30 + 1);

		for (int i = 0; i < 30; i++) {
			try {
				heap.insertInDeadSpace((int) (Math.random() * 30 * 2));
			} catch (Exception e) {
				fail("had wel moeten inserten");
				e.printStackTrace();
			}
		}

		heap.buildMinHeap();

		int item;
		int vorigItem = -1;
		for (int i = 0; i < 30; i++) {

			if ((item = heap.deleteNext()) >= vorigItem) {
				vorigItem = item;
			} else {
				fail("niet in de goede volgorde");
			}
		}
	}

	@Test(expected = Exception.class)
	public void testTeveelItems() throws Exception {
		RSHeap<Integer> heap = new RSHeap<>(5);

		for (int i = 0; i < 30; i++) {
			heap.insertInDeadSpace((int) (Math.random() * 30 * 2));
		}

	}
}
