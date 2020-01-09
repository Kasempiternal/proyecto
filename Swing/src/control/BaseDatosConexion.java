package control;

import java.sql.*;
import java.util.*;


public class BaseDatosConexion {
	
	static Connection conn;
	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver" ;
	  private static final String URL = "jdbc:mysql://localhost:3306/alcoholimpiadas" ;
	  private static final String USUARIO = "root" ;
	  private static final String CONTRASEnA = "123456789" ;
	
	
	public  Connection Conectar() throws BDExcepcion{
		
		try {
			Class.forName(CONTROLADOR);
			conn = DriverManager.getConnection(URL, USUARIO, CONTRASEnA);
			
			
			}catch(SQLException e) {
				throw new BDExcepcion("No se encontro la base de datos", e);
			
			
			}catch(ClassNotFoundException e){
				throw new BDExcepcion("No se pudo cargar el driver", e);
			
			}
		return conn;
			
    }
		
	
	public void desconectar() throws BDExcepcion{
		
		try {
			
			if(conn!=null) {
				conn.close();
			}
			
			}catch(SQLException e) {
				throw new BDExcepcion("No se encontro la base de datos", e);
	   
	    }

	}
	
	
	
	
}
	
	
	
	


