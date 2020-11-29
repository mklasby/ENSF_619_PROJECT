package Model.TheatreModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Seat {
	private int seatNum;
	
	public Seat(int seatNum) {
		setSeatNum(seatNum);
	}

	public Seat(ResultSet allSeats) throws SQLException {
		this.seatNum = allSeats.getInt("seatNum");
	}
	
	public Seat(JSONObject jsonObj) throws JSONException {
		seatNum = jsonObj.getInt("seatNum");
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
}
