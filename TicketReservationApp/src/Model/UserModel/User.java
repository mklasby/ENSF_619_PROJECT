package Model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import Model.PaymentModel.PaymentInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class User extends JSONObject {

	private String email;
	private PaymentInfo paymentInfo;

	public User() {
	};

	public User(String email, PaymentInfo paymentInfo) {
		setEmail(email);
		setPaymentInfo(paymentInfo);
	}

	public User(JSONObject jsonObj) throws JSONException {
		email = jsonObj.getString("email");
		paymentInfo = new PaymentInfo(jsonObj.getInt("cardNumber"), jsonObj.getString("cardType"));
		putFields();
	}

	private void putFields() {
		try {
			put("email", email);
			put("paymentInfo", paymentInfo);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public User(ResultSet rs) throws SQLException {
		setEmail(rs.getString("Email"));
		setPaymentInfo(new PaymentInfo(rs.getInt("CreditCardNumber"), rs.getString("CreditCardType")));
		putFields();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public static void setUser(Object object) {
	}

}
