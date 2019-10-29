package rest.alcoholimpiadas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginHandler {

	public ArrayList<User> getAllUsers(Connection connection) throws Exception {
		ArrayList<User> userList = new ArrayList<User>();
		try {
// String uname = request.getParameter("uname");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM user");
// ps.setString(1,uname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User users = new User();
				users.setIdUser(rs.getInt(1));
				users.setName(rs.getString(2));
				users.setType(rs.getInt(3));
				users.setPass(rs.getString(4));

				userList.add(users);
			}
			return userList;
		} catch (Exception e) {
			throw e;
		}
	}

}