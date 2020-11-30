package Model.PaymentModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class TicketReceipt extends Receipt {
	private int ticketId;

	public TicketReceipt(int ticketId, int creditCardNumber) {
		this.ticketId = ticketId;
		super.setCreditCardNumber(creditCardNumber);
		super.setReceiptType("Ticket");
		putFields();
	}

	public TicketReceipt(ResultSet rs) throws SQLException {
		super(rs);
		super.setAmount(rs.getDouble("Price"));
		setTicketId(rs.getInt("TicketID"));
		putFields();

	}

	public TicketReceipt(JSONObject jsonObj) throws JSONException, ParseException {
		super(jsonObj);
		setTicketId(jsonObj.getInt("ticketId"));
		putFields();
	}

	public void putFields() {
		try {
			this.setReceiptType("Ticket");
			put("ticketId", ticketId);
			super.putFields();

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

}
