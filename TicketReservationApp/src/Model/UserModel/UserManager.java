package Model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

	User user;
	boolean isRegisteredUser;

	public UserManager() {
		this.user = null;
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
		this.user = null;
		this.isRegisteredUser = false;

	}

	public boolean isRegistered() {
		return isRegisteredUser;
	}

	public void setIsRegistered(boolean status) {
		this.isRegisteredUser = status;
	}

	public User parseUserSQL(ResultSet resultSet) {
		try {
			return new User(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
