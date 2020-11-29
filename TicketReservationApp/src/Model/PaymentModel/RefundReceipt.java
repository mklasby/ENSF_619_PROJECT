package Model.PaymentModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefundReceipt extends Receipt{
	int couponId;

	public RefundReceipt(){

	}

	public RefundReceipt(ResultSet rs) throws SQLException {
		setCouponId(rs.getInt("VoucherID"));
		putFields();
	}

	public RefundReceipt(JSONObject jsonObj) throws JSONException {
		setCouponId(jsonObj.getInt("couponId"));
		putFields();
	}

	private void putFields() {
		try {
			put("couponId", couponId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	

}
