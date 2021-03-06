package Model.UserModel;

import Model.PaymentModel.PaymentInfo;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisteredUser extends User{
	private String name;
	private String userName;
	private String userType;
	private String password; //to fix? 
	private String address;
	private boolean hasPaidDues;
	

	public RegisteredUser(String userName, String email, PaymentInfo paymentInfo) { // gonna fix
		super(email, paymentInfo);
		setUserName(userName);
	}

	public RegisteredUser(JSONObject jsonObject) throws JSONException {
		super(jsonObject);
		setName(jsonObject.getString("name"));
		setUserType(jsonObject.getString("userType"));
		setUserName(jsonObject.getString("userName"));
		setPassword(jsonObject.getString("password"));
		setAddress(jsonObject.getString("address"));
		
		putFields();
	}
	public RegisteredUser(ResultSet rs) throws SQLException {
		super(rs);
		name = rs.getString("Name");
		userName = rs.getString("Username");
		userType = rs.getString("UserType");
		password = rs.getString("UserPassword");
		address = rs.getString("Address");
		putFields();
	}

	public void putFields() {
		try {
			put("name", name);
			put("userName", userName);
			put("userType", userType);
			put("password", password);
			put("address", address);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
