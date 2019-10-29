package rest.alcoholimpiadas;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import rest.alcoholimpiadas.User;



@Path("users")
public class UserResource {

	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getUser() {
		
		List<User> users = new ArrayList<User>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("EL DRIVER");;
		} 
		
	    Connection conection = null;
		try {
			conection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alcoholimpiadas","root", "passw0rd");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("EL CONECTION");
		} 
    	ResultSet rs_user = retrieveUsers(conection);
    	
    	    	
    	showUsers(rs_user,users);
    	
    	
	    try {
			conection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("EL CLOSE");
		}
		return users;
	}
		
		static ResultSet retrieveUsers(Connection conection) 
		{
			ResultSet rs = null; 
			try
			{
				String sql="SELECT * FROM User";
				Statement sentence = conection.createStatement();		   
			    boolean value_exists = sentence.execute(sql);  
			   		   
			    if(value_exists){
			    	rs = sentence.getResultSet();
			    } else {
			    	System.out.println("No users found");
			    }
			}
			catch (Exception ex)
			{
				System.out.print("RETRIEVE");
			}
			
			return rs;
		}

		static void showUsers(ResultSet rs_users, List<User> users)
		{
			
			try
			{
				// Show users
		    	if (null != rs_users)
		    	{
			    	System.out.println("\nUsers list: ");
			    	rs_users.beforeFirst();
		    		while (rs_users.next())
			    	{
		    			int id = rs_users.getInt(1);
		    			String user = rs_users.getString(2);
		    			int type = rs_users.getInt(3);
		    			String pass = rs_users.getString(4);
		    			
		    			users.add(new User(id, user, type, pass));
		    			
			    		//System.out.printf("%s, %s, %s, %s, %n", rs_users.getInt(1), rs_users.getString(2), rs_users.getInt(3), rs_users.getString(4));
			    	}
			    	
			    } else {
			    	System.out.println("No users found");
			    }
			}
			catch(Exception ex)
			{
				System.out.print("EL ARMARIO");
			}
		}

	}


