package Model.UserModel;

import java.sql.ResultSet;
import PaymentModel.*;

public class User {

	private String email;
	private PaymentInfo paymentInfo;

	public User(String email, PaymentInfo paymentInfo) {
		setEmail(email);
		setPaymentInfo(paymentInfo);
	}

	public User(ResultSet rs) {
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

	public Object getPassword() {
		return null;
	}

	public String getUserType() {
		return null;
	}
}
