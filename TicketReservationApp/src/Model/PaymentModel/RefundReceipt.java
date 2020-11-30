package Model.PaymentModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class RefundReceipt extends Receipt {
	int voucherId;

	public RefundReceipt() {
		super();
		setVoucherId(-1);
		setReceiptType("Refund");
		putFields();
	}

	public RefundReceipt(ResultSet rs) throws SQLException {
		super(rs);
		setVoucherId(rs.getInt("VoucherID"));
		setReceiptType("Refund");
		putFields();
	}

	public RefundReceipt(JSONObject jsonObj) throws JSONException, ParseException {
		super(jsonObj);
		setVoucherId(jsonObj.getInt("voucherId"));
		setReceiptType("Refund");
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
