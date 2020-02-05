package control;

import java.io.File;
import java.sql.*;

public class Metodos {

	static Connection conn;
	private int id = 0;
	static BaseDatosConexion bd = new BaseDatosConexion();
	//Comprueba que el fichero existe
	public boolean comprobarCheck() {
		boolean exists =false;
		
		File file = new File("src/control/data.txt");
	    exists = file.exists();
	    if (file.exists() && file.isFile())
	    {
	    	exists = true;
	    }
		
		return exists;
	}
	//Comprueba si el usuario del login existe
	public boolean checkUsername(String username, String pass) {

		PreparedStatement ps;
		ResultSet rs;
		boolean checkUser = false;
		String query = "SELECT * FROM User WHERE user_name =? AND user_pass=?";

		try {
			ps = bd.Conectar().prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, pass);

			rs = ps.executeQuery();

			if (rs.next()) {
				checkUser = checkId(username, pass);

			} else {
				checkUser = false;
			}
		} catch (SQLException | BDExcepcion ex) {

		}
		return checkUser;
	}
	//Comprueba que la id del usuario que intenta entrar es la correcta
	public boolean checkId(String username, String pass) {

		PreparedStatement ps;
		ResultSet rs;
		boolean checkUserid = false;

		String query1 = "SELECT user_type FROM User WHERE user_name =? AND user_pass=?";

		try {
			ps = bd.Conectar().prepareStatement(query1);
			ps.setString(1, username);
			ps.setString(2, pass);

			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
				if (id == 1) {
					checkUserid = true;
				}
			} else {
				checkUserid = false;
			}
		} catch (SQLException | BDExcepcion ex) {

		}
		return checkUserid;
	}
	//Metodo necesario para el Test
	public int getId(String username, String pass) {

		PreparedStatement ps;
		ResultSet rs;
		boolean checkUserid = false;

		String query1 = "SELECT id_user FROM User WHERE user_name =? AND user_pass=?";

		try {
			ps = bd.Conectar().prepareStatement(query1);
			ps.setString(1, username);
			ps.setString(2, pass);

			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
				
					
				
			} else {
				id=0;
			}
		} catch (SQLException | BDExcepcion ex) {

		}
		return id;
	}
	//borra el usuario que le pasamos
	public void deleteUser(int id) {
		int cont = 0;
		PreparedStatement ps;
		boolean rs;
		boolean checkUserid = false;
		cont = contarAdmin();
		if (id != 1) {

			String query1 = "DELETE FROM User WHERE id_user = ?;";

			try {
				ps = bd.Conectar().prepareStatement(query1);
				ps.setInt(1, id);
				rs = ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BDExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	//borra el reto que le pasamos
	public void deleteReto(int id) {
		int cont = 0;
		PreparedStatement ps;
		boolean rs;
		

			String query1 = "DELETE FROM Reto WHERE idReto = ?;";

			try {
				ps = bd.Conectar().prepareStatement(query1);
				ps.setInt(1, id);
				rs = ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BDExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	//Actualiza el usuario que le pasamos
	public void updateUser(User u,int pos) {
		
		PreparedStatement ps;

			String query1 = "UPDATE User SET id_user=?, user_name=?, user_type=?, user_pass=? WHERE id_user="+pos+" ;";
			
			

			try {
				ps = bd.Conectar().prepareStatement(query1);
				 ps.setInt (1, u.getIdUser());
			      ps.setString (2, u.getName());
			      ps.setInt (3, u.getType());
			      ps.setString(4, u.getPass());
				
				 ps.execute();
				 
				 System.out.println("llega");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BDExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}
	//Actualiza el reto que le pasamos
public void updateReto(Reto u,int pos) {
		
		PreparedStatement ps;

			String query1 = "UPDATE Reto SET idReto=?, nombreReto=?, maxReto=?, passReto=? WHERE idReto="+pos+" ;";
			
			

			try {
				ps = bd.Conectar().prepareStatement(query1);
				 ps.setInt (1, u.getIdReto());
			      ps.setString (2, u.getName());
			      ps.setInt (3, u.getMax());
			      ps.setString(4, u.getPass());
				
				 ps.execute();
				 
				 System.out.println("llega");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BDExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}
	//crea el usuario deseado
	public void crearUsuario(String nombre, int tipo, String pass) {
		PreparedStatement ps;
		
		String query = " insert into User (user_name, user_type, user_pass) values (?, ?, ?)";

		      // create the mysql insert preparedstatement
		    
		      try {
		    	  ps = bd.Conectar().prepareStatement(query);
			      ps.setString (1, nombre);
			      ps.setInt (2, tipo);
			      ps.setString(3, pass);
				
				 ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BDExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      

		      // execute the preparedstatement
		     
		
	}
	//crea el reto deseado
	public void crearReto(String nombre, int tipo, String pass) {
		PreparedStatement ps;
		
		String query = " insert into Reto (nombreReto, maxReto, passReto) values (?, ?, ?)";

		      // create the mysql insert preparedstatement
		    
		      try {
		    	  ps = bd.Conectar().prepareStatement(query);
			      ps.setString (1, nombre);
			      ps.setInt (2, tipo);
			      ps.setString(3, pass);
				
				 ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BDExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      

		      // execute the preparedstatement
		     
		
	}
	//inserta el usuario que le pasamos
	public void insertarUsuario(int idUser, int idReto) {
		PreparedStatement ps;
		
		String query = " insert into User_Reto (idUser, idReto) values (?, ?)";

		      // create the mysql insert preparedstatement
		    
		      try {
		    	  ps = bd.Conectar().prepareStatement(query);
			      ps.setInt (1, idUser);
			      ps.setInt (2, idReto);
			     
				
				 ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BDExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      

		      // execute the preparedstatement
		     
		
	}
	//cuenta losd admin para que por lo menos siempre tengamos 1
	public int contarAdmin() {
		int cont = 0;
		PreparedStatement ps;
		ResultSet rs;
		boolean pase;
		boolean checkUserid = false;

		String query1 = "SELECT * FROM User WHERE user_type=?";

		try {
			ps = bd.Conectar().prepareStatement(query1);
			ps.setInt(1, 1);

			rs = ps.executeQuery();

			if (rs.next()) {
				cont++;
			}
		} catch (SQLException | BDExcepcion ex) {

		}
		return cont;
	}

}
