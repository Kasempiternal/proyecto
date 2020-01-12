package control;

public class Reto {
	
	private int idReto;
	private String name;
	private int max;
	private String pass;
	
	public Reto(int idReto, String name, int max, String pass) {
		super();
		this.idReto = idReto;
		this.name = name;
		this.max = max;
		this.pass = pass;
	}

	public Reto() {
		super();
	}

	public int getIdReto() {
		return idReto;
	}

	public void setIdReto(int idReto) {
		this.idReto = idReto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	

}
