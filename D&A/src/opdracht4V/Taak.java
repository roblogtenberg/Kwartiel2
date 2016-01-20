package opdracht4V;

public class Taak {
	public int vroegst;
	public int laatst;
	public String description;
	
	
	public Taak(String description) {
		this.description = description;
	}
	
	public void setVroegst(int vroegst) {
		this.vroegst = vroegst;
	}
	
	public void setLaatst(int laatst) {
		this.laatst = laatst;
	}
	
	public int getVroegst() {
		return vroegst;
	}
	
	public int getLaatst() {
		return laatst;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Taak)
		{
			return ((Taak) obj).description.equals(description);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
