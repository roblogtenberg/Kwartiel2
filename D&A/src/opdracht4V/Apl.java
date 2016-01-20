package opdracht4V;

import java.io.File;
import java.util.Scanner;

public class Apl {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		System.out.println("Input file moet voldoen aan het volgende formaat:");
		System.out.println(" - Eerst alle taken op een aparte regel");
		System.out.println(" - Een wit regel als scheiding");
		System.out.println(" - Na de scheiding komen de regels met de volgorde");
		System.out.println("de structuur van een regel die de volgorde aangeeft is:");
		System.out.println("{taak1},{taak2},{duur in getallen}");
		System.out.println("een voorbeeld van een regel die de volgorde aangeeft is:");
		System.out.println("deeg bereiden,deeg bakken,5");
		System.out.println();
		System.out.println("geef de file op die je wil inscannen:");
		String fname = in.nextLine();
		System.out.println("\n---");
		
		Scanner scan = new Scanner(new File(fname));
		PertNetwork pn = new PertNetwork();
		String line;

		//eerste gedeelte taken inlezen
		while (scan.hasNextLine()) {
			line = scan.nextLine().trim();
			if (!line.equals(""))
				pn.voegTaakToe(new Taak(line));
			else
				break;
		}

		//tweede gedeelte volgorde
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			String[] stukjes = line.split(",");
			if (stukjes.length != 3)
				throw new Exception("fout in invoer");
			else {
				Taak t1 = pn.getTaak(stukjes[0].trim());
				Taak t2 = pn.getTaak(stukjes[1].trim());
				if(t1 == null || t2 == null)
					throw new Exception("fout in invoer, bij het koppelen van taken een taak gebruikt die nog niet bestond");
				pn.verbindTaak(t1, t2, Integer.parseInt(stukjes[2].trim()));
			}
		}
		
		scan.close();

		pn.vroegsteTijd();
		pn.laatsteTijd();

		for (Taak t : pn.getTaken()) {
			System.out.println(t + " - op zn vroegst klaar na: "
					+ t.getVroegst() + " - op zn laatst: " + t.getLaatst());
		}

	}

}
