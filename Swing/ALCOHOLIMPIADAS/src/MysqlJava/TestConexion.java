package MysqlJava;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConexion {
	
	public static void main(String[] args) {

		getallusers();
		
	}
	public static void getallusers() {
		ConexionFrame conexion = new ConexionFrame();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			
			cn = conexion.Conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM user");
			
			while(rs.next()) {
				int IdUser = rs.getInt(1);
				String NameUser = rs.getString(2);
				int UserType = rs.getInt(3);
				String UserPass = rs.getString(4);
				
				System.out.println("Id:" + IdUser + "  " + "Nombre:" + NameUser  + "  " + "Tipo:" + UserType + "  " + "Pass:" + UserPass);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			
		}finally {
			try {
					if(rs != null) {
						rs.close();
					}
					if(cn != null) {
						cn.close();
					}
					if(stm != null) {
						cn.close();
					}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
}
