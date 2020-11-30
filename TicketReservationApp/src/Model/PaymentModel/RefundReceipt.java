package Model.PaymentModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefundReceipt extends Receipt {
	int voucherId;

	public RefundReceipt() {
		super();
		setVoucherId(-1);
		setReceiptType("Refund");
	}

	public RefundReceipt(ResultSet rs) throws SQLException {

		setVoucherId(rs.getInt("VoucherID"));
		putFields();
	}

	public RefundReceipt(JSONObject jsonObj) throws JSONException {
		setVoucherId(jsonObj.getInt("voucherId"));
		putFields();
	}

	public void putFields() {
		try {
			super.putFields();
			put("voucherId", voucherId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public int getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}

}
