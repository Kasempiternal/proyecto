package control;

import java.sql.*;

public class Metodos {

	static Connection conn;
	private int id = 0;
	static BaseDatosConexion bd = new BaseDatosConexion();

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
