package adatbazisokbeadando;

public class Dolgozo {
	private int Kod;
	private String nev;
	private String szulido;
	private String lakohely;
	private int iq;
	
public Dolgozo(int kod, String nev, String szulido, String lakohely, int iq) {
	this.Kod = kod;
	this.nev =nev;
	this.szulido = szulido;
	this.lakohely = lakohely;
	this.iq =iq;
	
}

public int getKod() {
	return Kod;
}

public void setKod(int kod) {
	this.Kod = kod;
}

public String getNev() {
	return nev;
}

public void setNev(String nev) {
	this.nev = nev;
}

public String getSzulido() {
	return szulido;
}

public void setSzulido(String szulido) {
	this.szulido = szulido;
}

public String getLakohely() {
	return lakohely;
}

public void setLakohely(String lakohely) {
	this.lakohely = lakohely;
}

public int getIq() {
	return iq;
}

public void setIq(int iq) {
	this.iq = iq;
}


}
