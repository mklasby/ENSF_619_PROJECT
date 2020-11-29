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
	private Date theDate;
	
	public Receipt() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000); // creates random 5 digit Id
//		String formatted = String.format("%05d", num); 
		setReceiptId(num);
		theDate = new Date(); // can adjust to make it a String with desired format 
		putFields();
;	}

	public Receipt(JSONObject jsonObj) throws JSONException, ParseException {
		receiptId = jsonObj.getInt("receiptId");
		amount = jsonObj.getDouble("amount");
		theDate = DateFormat.getDateInstance().parse(jsonObj.getString("theDate")); //set the string format
		putFields();
	}

	public Receipt(ResultSet rs) throws SQLException {
		setReceiptId(rs.getInt("ReceiptID"));
		setAmount(rs.getDouble("Price"));
		setTheDate(rs.getDate("ReceiptDate"));
		putFields();
	}

	private void putFields() {
		try {
			put("receiptId", receiptId);
			put("amount", amount);
			put("theDate", theDate);
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

	public Date getTheDate() {
		return theDate;
	}

	public void setTheDate(Date theDate) {
		this.theDate = theDate;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	

}
