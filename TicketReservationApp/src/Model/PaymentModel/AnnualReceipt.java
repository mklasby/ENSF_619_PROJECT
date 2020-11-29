package Model.PaymentModel;
import Model.PaymentModel.Receipt;
import Model.UserModel.RegisteredUser;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnualReceipt extends Receipt {
	private RegisteredUser theUser;
	private int paymentYear;

	public AnnualReceipt(int year) {
		setPaymentYear(year);
	}

	public AnnualReceipt(ResultSet rs) throws SQLException { //to complete, how is annual payment receipt stored in db? linked to userName?
		setPaymentYear(rs.getInt("PaymentYear"));
		putFields(); // need to create Annualreceipt object with user info
	}

	public AnnualReceipt(JSONObject jsonObj) throws JSONException {
		setPaymentYear(jsonObj.getInt("paymentYear"));
		putFields();
	}

	private void putFields() {
		try {
			put("paymentYear", paymentYear);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	public int getPaymentYear() {
		return paymentYear;
	}

	public void setPaymentYear(int year) {
		this.paymentYear = year;
	}

	public RegisteredUser getTheUser() {
		return theUser;
	}

	public void setTheUser(RegisteredUser theUser) {
		this.theUser = theUser;
	}

}
