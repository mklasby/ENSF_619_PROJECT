package Model.TheatreModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowTime extends JSONObject {
	private int showTimeID;
	private Timestamp startTime;
	private Timestamp endTime;

	// I really dont think we need date, because shouldnt time already have date??
	// We could just have these two things, using sql Timestamp type. I think its a
	// wrapper?

	// private Timestamp startTime;
	// private Timestamp endTime;

	public ShowTime(int showTimeID, Timestamp startTime, Timestamp endTime) {
		setShowTimeID(showTimeID);
		setStartTime(startTime);
		setEndTime(endTime);
		putFields();
	}

	public void putFields() {
		try {
			put("showTimeID", showTimeID);
			put("startTime", startTime);
			put("endTime", endTime);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ShowTime(ResultSet allShowTimes) throws SQLException {
		this.showTimeID = allShowTimes.getInt("ShowTimeID");
		this.startTime = allShowTimes.getTimestamp("StartTime");
		this.endTime = allShowTimes.getTimestamp("EndTime");
		putFields();
	}

	/**
	 * This takes in a JSONObject and converts startTime and endTime stored in it
	 * from a string to a time object. Ive commented coding for if we decide to use
	 * Timestamp instead (via sql wrapper)
	 * 
	 * @param jsonObj
	 * @return
	 */
	public Time parseTimeFromJson(JSONObject jsonObj, String timeName) {

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
			Date parsedDate;
			parsedDate = (Date) dateFormat.parse(jsonObj.getString(timeName));
			// Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			Time timestamp = new Time(parsedDate.getTime());
			return timestamp;
		} catch (ParseException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public ShowTime(JSONObject jsonObj) throws JSONException {
		showTimeID = jsonObj.getInt("showTimeID");
		startTime = (Timestamp) jsonObj.get("startTime");
		startTime = (Timestamp) jsonObj.get("endTime");
		putFields();
	}

	public int getShowTimeID() {
		return showTimeID;
	}

	public void setShowTimeID(int showTimeID) {
		this.showTimeID = showTimeID;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public static void main(String[] arg0) {

	}
}