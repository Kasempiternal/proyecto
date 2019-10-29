package rest.alcoholimpiadas;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserArray {
	
	//CHANGE THIS THREE STRINGS ACCORDING TO YOUR DATABASE
	
	//IT IS NECESARY TO ADD JDBC DRIVER TO THE WEB-INF/lib DIRECTORY. YOU CAN DO THIS BY PRESSING RIGHT CLICK
	//ON THE JAVA PROYECT, THEN PROPERTIES, THEN DEPLOYMENT ASSEMBLY AND THEN CLICK IN  WEB-INF/lib and ADD THE DRIVER
	
	String url ="jdbc:mysql://localhost:3306/alcoholimpiadas";
	String username= "root";
	String pass= "passw0rd";
	
	Connection con = null;
	
	
	public UserArray(){
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,pass);
		} catch (SQLException e) {
			System.out.println("Fallo al conectar con la DB..");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}
	
	public List<User> getUsers(){
		
		List<User> users = new ArrayList<User>();
		String sql ="select * from User ";
		try {
			con = DriverManager.getConnection(url,username,pass);
			Statement st = con.createStatement();
			ResultSet rs =  st.executeQuery(sql);
			while(rs.next()) {
				
				User a = new User();
				a.setIdUser(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setType(rs.getInt(3));
				a.setPass(rs.getString(4));;
				
				users.add(a);
			}
		} catch (SQLException e) {
			System.out.println("Fallo al crear el statement..");
			e.printStackTrace();
		}
		
		
		return users;
	}
	
	public User getUser(int IdUser) {
		
		User a = new User();
		String sql ="select * from User where id_user="+IdUser;
		try {
			
			Statement st = con.createStatement();
			ResultSet rs =  st.executeQuery(sql);
			while(rs.next()) {
				
				
				a.setIdUser(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setType(rs.getInt(3));
				a.setPass(rs.getString(4));
				
				
			}
		} catch (SQLException e) {
			System.out.println("Fallo al crear el statement..");
			e.printStackTrace();
		}
		
		return a;
	}
	
	public void createUser(User us) {
		
		String sql ="insert into User values (?,?,?,?)";
		
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, us.getIdUser());
			st.setString(2, us.getName());
			st.setInt(3, us.getType());
			st.setString(4, us.getPass());
			
			
		} catch (SQLException e) {
			System.out.println("Fallo al recibir usuario..");
			e.printStackTrace();
		}
		
	}

}
