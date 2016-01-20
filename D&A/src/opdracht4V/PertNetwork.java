package opdracht4V;

import java.util.ArrayList;
import java.util.List;

public class PertNetwork {

	private Graaf<Taak, Integer> graaf = new Graaf<Taak, Integer>();

	public PertNetwork() {

	}

	/**
	 * berekent de tijd dat een taak op zijn vroegst af kan zijn, dit wordt
	 * opgeslagen in het taak object
	 */
	public void vroegsteTijd() {
		List<Taak> inTopOrder = graaf.getTopologicalOrder();

		for (Taak v : inTopOrder) {
			int hoogst = 0;
			int timeToComplete = 0;
			for (Taak t : graaf.getIncomingVertices(v)) {

				if (timeToComplete == 0)
					timeToComplete = graaf.getWeight(t, v);

				if (t.getVroegst() > hoogst)
					hoogst = t.getVroegst();
			}

			v.setVroegst(hoogst + timeToComplete);
		}

	}

	/**
	 * berekent de tijd dat een taak op zijn laatst af kan zijn, dit wordt
	 * opgeslagen in het taak object.
	 * Deze methode werkt alleen als eerst de methode vroegsteTijd is uitgevoerd.
	 */
	public void laatsteTijd() {
		List<Taak> inTopOrder = graaf.getTopologicalOrder();

		for (int i = inTopOrder.size() - 1; i >= 0; i--) {
			Taak v = inTopOrder.get(i);

			if (graaf.getNeighbours(v).size() == 0) {
				v.setLaatst(v.getVroegst());
			}

			for (Taak t : graaf.getIncomingVertices(v)) {
				t.setLaatst(v.getLaatst() - graaf.getWeight(t, v));
			}

		}
	}

	/**
	 * 
	 * @return alle taken opgeslagen in dit pertnetwerk
	 */
	public List<Taak> getTaken() {
		return graaf.getVertices();
	}

	/**
	 * voegt een taak toe aan dit pertnetwerk
	 */
	public void voegTaakToe(Taak t) {
		graaf.putVertex(t);
	}

	/**
	 * de vebinding wordt niet gemaakt wanneer er een cycle ontstaat
	 * 
	 * @param van
	 *            vertex die gedaan moet worden voor
	 * @param naar
	 *            deze
	 * @param tijd
	 *            hoelang het duurt om de "naar" taak te maken
	 */
	public void verbindTaak(Taak van, Taak naar, int tijd) {
		assert van != null;
		assert naar != null;
		assert tijd >= 0;

		graaf.putEdge(van, naar, tijd);
		
		if(graaf.getTopologicalOrder().size() < graaf.getVertices().size())
		{
			System.err.println("cycle detected not adding edge");
			graaf.removeEdge(van, naar);
		}
	}

	/**
	 * zoek een taak op, op basis van de descriptie
	 * 
	 * @param desc
	 * @return de gevonden taak
	 */
	public Taak getTaak(String desc) {
		assert desc != null;
		assert desc.length() > 0;
		for (Taak t : getTaken()) {
			if (t.description.equals(desc))
				return t;
		}
		return null;
	}
}
