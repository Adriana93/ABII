package adatbazisokbeadando;

public class Task {

	private int tkod;
	private int Ekod;
	private String leiras;
	private String datum;
	private int prioritas;
	
	public Task(int tkod, int Ekod, String leiras, String datum, int prioritas) {
		this.tkod = tkod;
		this.Ekod =Ekod;
		this.leiras = leiras;
		this.datum = datum;
		this.prioritas =prioritas;
		
	}

	public int getTkod() {
		return tkod;
	}

	public void setTkod(int tkod) {
		this.tkod = tkod;
	}

	public int getEkod() {
		return Ekod;
	}

	public void setEkod(int ekod) {
		Ekod = ekod;
	}

	public String getLeiras() {
		return leiras;
	}

	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public int getPrioritas() {
		return prioritas;
	}

	public void setPrioritas(int prioritas) {
		this.prioritas = prioritas;
	}
	
	
	
	
		
	}
