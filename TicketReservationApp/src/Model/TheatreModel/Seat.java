package Model.TheatreModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Seat extends JSONObject {
	private int seatNum;
	
	public Seat(int seatNum) {
		setSeatNum(seatNum);
		putFields();
	}
	
	public Seat() {
		setSeatNum(0);
		putFields();
	}
	
	
	public void putFields() {
		try {
			put("seatNum", seatNum);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Seat(ResultSet allSeats) throws SQLException {
		this.seatNum = allSeats.getInt("seatNum");
		putFields();
	}
	
	public Seat(JSONObject jsonObj) throws JSONException {
		seatNum = jsonObj.getInt("seatNum");
		putFields();
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
}
