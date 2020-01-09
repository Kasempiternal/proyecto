package control;

public class BDExcepcion extends Exception {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BDExcepcion(String mensaje, Throwable e) {
		super(mensaje, e);
	}

}
