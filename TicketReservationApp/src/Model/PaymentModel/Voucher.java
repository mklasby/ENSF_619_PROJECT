package Model.PaymentModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Voucher extends JSONObject {
	private int voucherId;
	private double amount;
	private Date expiryDate;

	public Voucher() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000); // creates random 5 digit Id
		setVoucherId(num);
		putFields();
	}

	public Voucher(JSONObject jsonObj) throws JSONException, ParseException {
		voucherId = jsonObj.getInt("voucherId");
		amount = jsonObj.getDouble("amount");
		expiryDate = DateFormat.getDateInstance().parse(jsonObj.getString("expiryDate")); // set the string format
		putFields();
	}

	public Voucher(ResultSet rs) throws SQLException {
		setVoucherId(rs.getInt("VoucherID"));
		setAmount(rs.getDouble("VoucherValue"));
		setExpiryDate(rs.getDate("VoucherExpiraryDate")); // fix typo in database
		putFields();
	}

	public void putFields() {
		try {
			put("voucherId", voucherId);
			put("amount", amount);
			put("expiryDate", expiryDate);
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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}

}
