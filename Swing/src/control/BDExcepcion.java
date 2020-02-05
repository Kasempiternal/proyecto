package control;

public class BDExcepcion extends Exception {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//metodo para lanzar excepcion
	public BDExcepcion(String mensaje, Throwable e) {
		super(mensaje, e);
	}

}
