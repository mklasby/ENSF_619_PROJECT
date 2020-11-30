package Model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

	public User getUser() {
		return user;
	}

	User user;
	boolean isRegisteredUser;

	public UserManager() {
		this.user = new User();
		isRegisteredUser = false;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void loginUser(ResultSet resultSetUser) {

		try {
			this.user = new User(resultSetUser);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		this.isRegisteredUser = true;
	}

	public void logoutUser() {
		this.user = new User();
		isRegisteredUser = false;

	}

	public boolean isRegistered() {
		return isRegisteredUser;
	}

	public void setIsRegistered(boolean status, String type) {
		this.isRegisteredUser = status;
		this.getUser().setUserType(type);
		this.getUser().putFields();
	}

	public User parseUserSQL(ResultSet resultSet) {
		try {
			User user = new User(resultSet);
			return user;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	public boolean getIsRegisteredUser() {
		return isRegisteredUser;
	}

}
