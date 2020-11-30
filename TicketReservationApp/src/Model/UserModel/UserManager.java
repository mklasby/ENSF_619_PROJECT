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

	public void loginUser(ResultSet resultSetUser) { // i changed it so it not static anymore

		// I am assuming that the user to give to me is not null
		// Mike said it would be a good idea to have user instantiate in User Manager ==
		// less coupling == more lonely
		try {
			this.user = new User(resultSetUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean getIsRegisteredUser() {
		return isRegisteredUser;
	}

}
