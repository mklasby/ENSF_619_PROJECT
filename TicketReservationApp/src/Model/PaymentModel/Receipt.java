package Model.PaymentModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Receipt extends JSONObject {
	private int receiptId;
	private double amount;
	private String receiptType;
	private int creditCardNumber;
	// private Date theDate;

	public Receipt() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		setReceiptId(num);
		setReceiptType("Unassigned");
		setAmount(0);
		setCreditCardNumber(0);
		putFields();
	}

	public Receipt(JSONObject jsonObj) throws JSONException, ParseException {
		receiptId = jsonObj.getInt("receiptId");
		amount = jsonObj.getDouble("amount");
		receiptType = jsonObj.getString("receiptType");
		creditCardNumber = jsonObj.getInt("creditCardNumber");
		putFields();
	}


	public Receipt(ResultSet rs) throws SQLException {
		setReceiptId(rs.getInt("ReceiptID"));
		setAmount(rs.getDouble("Price"));
		setReceiptType(rs.getString("ReceiptType"));
		setCreditCardNumber(rs.getInt("CreditCardNumber"));
		putFields();
	}

	public void putFields() {
		try {
			put("receiptId", receiptId);
			put("amount", amount);
			put("receiptType", receiptType);
			put("creditCardNumber", creditCardNumber);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;

	}

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public int getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

}
