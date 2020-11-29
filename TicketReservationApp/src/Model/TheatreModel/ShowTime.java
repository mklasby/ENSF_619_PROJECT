package Model.TheatreModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowTime {
	private int showTimeID;
	private Date date;
	private Time startTime;
	private Time endTime;
	
	public ShowTime(int showTimeID, Date date, Time startTime, Time endTime) {
		setShowTimeID(showTimeID);
		setDate(date);
		setStartTime(startTime);
		setEndTime(endTime);
	}

	public ShowTime(ResultSet allShowTimes) throws SQLException {
		this.showTimeID = allShowTimes.getInt("ShowTimeID");
		//this.date = allShowTimes.getDate("ShowTimeDate");
		this.startTime = allShowTimes.getTime("startTime");
		this.endTime = allShowTimes.getTime("EndTime");
	}
	
	public ShowTime(JSONObject jsonObj) throws JSONException {
		showTimeID = jsonObj.getInt("showTimeID");
		//date = jsonObj.getString("date");                    //json object doesn't have getDate and Time
		//startTime = jsonObj.getString("startTime");
		//endTime = jsonObj.getString("endTime");
	}

	public int getShowTimeID() {
		return showTimeID;
	}

	public void setShowTimeID(int showTimeID) {
		this.showTimeID = showTimeID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
}	