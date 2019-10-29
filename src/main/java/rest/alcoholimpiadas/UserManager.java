package rest.alcoholimpiadas;

import java.sql.Connection;
import java.util.ArrayList;

public class UserManager {

	public static ArrayList<User> getAllUsersList() throws Exception {
		ArrayList<User> userList = null;
		try {
			DbConnection database = new DbConnection();
			Connection connection = database.getConnection();
			UserArray loginHandler = new UserArray();
			userList = loginHandler.getAllUsers(connection);

		} catch (Exception e) {
			throw e;
		}
		return userList;
	}

}