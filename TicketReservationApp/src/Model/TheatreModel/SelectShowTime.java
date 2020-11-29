package Model.TheatreModel;
import CommonMessage.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class SelectShowTime implements MessageConstants {
	private ArrayList<ShowTime> showTimeList;
	
	public SelectShowTime() {
		
	}
	
	public Message getShowTimeList(ResultSet allShowTimes) throws SQLException {
		do {
			ShowTime thisShowTime = new ShowTime(allShowTimes);
			showTimeList.add(thisShowTime);
		} while (allShowTimes.next());
		JSONArray showTimeData = new JSONArray();
		for (ShowTime showTime : showTimeList) {
			showTimeData.put(showTime.toString());
		}
		Message response = new Message(OK, showTimeData);
		return response;
	}
	
	public ArrayList<ShowTime> getSeatList() {
		return showTimeList;
	}

	public void setSeatList(ArrayList<ShowTime> showTimetList) {
		this.showTimeList = showTimeList;
	}
}	
	
	