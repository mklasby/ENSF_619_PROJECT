package Model.PaymentModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Coupon extends JSONObject {
	private int couponId;
	private double amount;
	private Date expiryDate;

	public Coupon(){
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000); // creates random 5 digit Id
		setCouponId(num);
	}
	public Coupon(JSONObject jsonObj) throws JSONException, ParseException {
		couponId = jsonObj.getInt("couponId");
		amount = jsonObj.getDouble("amount");
		expiryDate = DateFormat.getDateInstance().parse(jsonObj.getString("expiryDate")); //set the string format
		putFields();
	}

	public Coupon(ResultSet rs) throws SQLException {
		setCouponId(rs.getInt("VoucherID"));
		setAmount(rs.getDouble("VoucherValue"));
		setExpiryDate(rs.getDate("VoucherExpiraryDate")); //fix typo in database
		putFields();
	}

	private void putFields() {
		try {
			put("couponId", couponId);
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
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}


	

}
