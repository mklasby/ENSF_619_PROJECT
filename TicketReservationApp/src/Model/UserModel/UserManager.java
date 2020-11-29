package Model.UserModel;

import java.sql.ResultSet;

public class UserManager {
	
	User user;
	boolean isRegisteredUser;
	
	
	public UserManager() {
		this.user = null;
		isRegisteredUser = false;
	}

<<<<<<< HEAD
    public void setUser(User user) {
    	this.user = user;
=======
    public void loginUser(ResultSet resultSetUser) { // i changed it so it not static anymore
    	
    	// I am assuming that the user to give to me is not null
    	// Mike said it would be a good idea to have user instantiate in User Manager == less coupling == more lonely
    	this.user = new User(resultSetUser);
    	this.isRegisteredUser = true;
    }
    
    public void logoutUser() {
    	this.user = null;
    	this.isRegisteredUser = false;
>>>>>>> d21986655b519f0f834bbc730b1bb4c0ae2fbebc
    }

	public boolean isRegistered() {
		// TODO Auto-generated method stub
		return this.isRegisteredUser;
	}

}
