package Model.TheatreModel;
import CommonMessage.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class SelectSeat implements MessageConstants {
	private ArrayList<Seat> seatList;
	
	public SelectSeat() {
		
	}
	
	public Message getSeatList(ResultSet allSeats) throws SQLException {
		do {
			Seat thisSeat = new Seat(allSeats);
			seatList.add(thisSeat);
		} while (allSeats.next());
		JSONArray seatData = new JSONArray();
		for (Seat seat : seatList) {
			seatData.put(seat.toString());
		}
		Message response = new Message(OK, seatData);
		return response;
	}

	public ArrayList<Seat> getSeatList() {
		return seatList;
	}

	public void setSeatList(ArrayList<Seat> seatList) {
		this.seatList = seatList;
	}
	
	
}
