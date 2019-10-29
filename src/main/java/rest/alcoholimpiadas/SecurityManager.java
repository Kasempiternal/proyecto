package rest.alcoholimpiadas;

import java.sql.Connection;
import java.util.ArrayList;

import rest.alcoholimpiadas.*;

public class SecurityManager {

	public static ArrayList<User> getAllUsersList() throws Exception {
		ArrayList<User> userList = null;
		try {
			DbConnection database = new DbConnection();
			Connection connection = database.getConnection();
			LoginHandler loginHandler = new LoginHandler();
			userList = loginHandler.getAllUsers(connection);

		} catch (Exception e) {
			throw e;
		}
		return userList;
	}

}