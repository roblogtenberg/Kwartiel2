package opdracht2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static final String TESTFILEPATH = "testGen.txt";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		File file = null;

		int input = 0;
		while (true) {
			System.out.println("wilt u een file met een aantal element genereren (1) of wilt u een file opgeven om te sorteren(2)?");

			while (true) {
				try {
					input = in.nextInt();
					if (input == 1 || input == 2 || input == 3) {
						break;
					}
				} catch (InputMismatchException e) {
				}
				System.out.println("geen geldig antwoord");
			}
			if (input == 1) {
				System.out.println("hoeveel items?");
				while (true) {
					try {
						input = in.nextInt();
						if (input > 1) {
							break;
						}
					} catch (InputMismatchException e) {
						in.nextLine();
					}
					System.out.println("geen geldige hoeveelheid of getal");
				}
				file = generateTestFile(TESTFILEPATH, input);
			} else if (input == 2) {
				System.out.println("formaat van dit bestand moet zijn: getallen met een spatie om ze te onderscheiden");
				System.out.println("geef het absolute bestands pad:");
				while (true) {
					file = new File(in.nextLine());
					if (file.exists()) {
						break;
					}
					System.out.println("file niet gevonden probeer opnieuw");
				}

			} else if (input == 3) {
				System.out.println("hoeveel items?");
				while (true) {
					try {
						input = in.nextInt();
						if (input > 1) {
							break;
						}
					} catch (InputMismatchException e) {
						in.nextLine();
					}
					System.out.println("geen geldige hoeveelheid of getal");
				}
				file = generateTestFileII(TESTFILEPATH, input);
			}

			System.out.println("hoe groot mag de heap zijn?");

			while (true) {
				try {
					input = in.nextInt();
					if (input > 1) {
						break;
					}
				} catch (InputMismatchException e) {
					in.nextLine();
				}
				System.out.println("geen geldige hoeveelheid of getal");
			}

			FileSorter sorter;
			try {
				sorter = new FileSorter(file, input);
				String resultFile = sorter.Sort();
				System.out.println("De runs zijn te vinden in: " + resultFile);
				System.out.println("gemmidelde aantal elementen in een complete run: " + sorter.getGemElementen());
				System.out.println("aantal runs: " + sorter.getRuns());
			} catch (FileNotFoundException e) {
				System.err.println("het opgegeven bestand bestaat niet");
				e.printStackTrace();
			}
		}
	}

	public static File generateTestFile(String path, int amountItems) {
		File f = new File(path);
		try {
			PrintWriter pw = new PrintWriter(f);
			for (int i = 0; i < amountItems; i++) {
				pw.print((int) (Math.random() * amountItems * 2) + " ");
			}
			pw.flush();
			pw.close();
			return f;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File generateTestFileII(String path, int amountItems) {
		File f = new File(path);
		try {
			PrintWriter pw = new PrintWriter(f);
			for (int i = 0; i < amountItems; i++) {
				pw.print(amountItems - i + " ");
			}
			pw.flush();
			pw.close();
			return f;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
