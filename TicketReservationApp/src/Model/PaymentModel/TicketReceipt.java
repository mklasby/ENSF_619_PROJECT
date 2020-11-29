package Model.PaymentModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class TicketReceipt extends Receipt {
	private int ticketId;
	
	
	public TicketReceipt(int ticketId) {
		this.ticketId = ticketId;
		putFields();
	}


	public TicketReceipt(ResultSet rs) throws SQLException {
		super(rs);
		setTicketId(rs.getInt("TicketID"));
		putFields();

	}

	public TicketReceipt(JSONObject jsonObj) throws JSONException, ParseException {
		super(jsonObj);
		setTicketId(jsonObj.getInt("ticketId"));
		putFields();
	}

	private void putFields() {
		try {
			put("ticketId", ticketId);

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
