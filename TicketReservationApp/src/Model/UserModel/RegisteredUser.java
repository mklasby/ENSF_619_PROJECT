package Model.UserModel;

import Model.PaymentModel.PaymentInfo;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisteredUser extends User{
	private String firstName;
	private String lastName;
	private String userName;
	private String userType;
	private String password; //to fix? 
	private boolean hasPaidDues;

	public RegisteredUser(String userName, String email, PaymentInfo paymentInfo) { // gonna fix
		super(email, paymentInfo);
		setUserName(userName);
	}

	public RegisteredUser(JSONObject jsonObject) throws JSONException {
		super(jsonObject);
		setFirstName(jsonObject.getString("firstName"));
		setLastName(jsonObject.getString("lastName"));
		setUserType(jsonObject.getString("userType"));
		setUserName(jsonObject.getString("userName"));
		setPassword(jsonObject.getString("password"));
		putFields();
	}
	public RegisteredUser(ResultSet rs) throws SQLException {
		super(rs);
		firstName = rs.getString("fName");
		lastName = rs.getString("lName");
		userName = rs.getString("Username");
		userType = rs.getString("UserType");
		password = rs.getString("UserPassword");
		putFields();
	}

	private void putFields() {
		try {
			put("firstName", firstName);
			put("lastName", lastName);
			put("userName", userName);
			put("userType", userType);
			put("password", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isHasPaidDues() {
		return hasPaidDues;
	}

	public void setHasPaidDues(boolean hasPaidDues) {
		this.hasPaidDues = hasPaidDues;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
