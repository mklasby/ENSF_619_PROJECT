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

public class ShowTime extends JSONObject{
	private int showTimeID;
	private Date date; 
	private Time startTime;
	private Time endTime;
	
	//I really dont think we need date, because shouldnt time already have date??
	//We could just have these two things, using sql Timestamp type. I think its a wrapper?
	
	//private Timestamp startTime;
	//private Timestamp endTime;
	
	public ShowTime(int showTimeID, Date date, Time startTime, Time endTime) {
		setShowTimeID(showTimeID);
		setDate(date);
		setStartTime(startTime);
		setEndTime(endTime);
		putFields();
	}
	
	public void putFields() {
		try {
			put("showTimeID", showTimeID);
			put("date", date);
			put("startTime", startTime);
			put("endTime", endTime);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ShowTime(ResultSet allShowTimes) throws SQLException {
		this.showTimeID = allShowTimes.getInt("ShowTimeID");
		this.date = allShowTimes.getDate("ShowTimeDate");
		this.startTime = allShowTimes.getTime("startTime");
		this.endTime = allShowTimes.getTime("EndTime");
		putFields();
	}
	
	/**
	 * This takes in a JSONObject and converts startTime and endTime stored in it from a string to a 
	 * time object. Ive commented coding for if we decide to use Timestamp instead (via sql wrapper)
	 * @param jsonObj
	 * @return
	 */
	public Time parseTimeFromJson(JSONObject jsonObj, String timeName) {
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
			Date parsedDate;
			parsedDate = (Date) dateFormat.parse(jsonObj.getString(timeName));
			//Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
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
		//json object doesn't have getDate and Time
				//Correct so lets try to convert the string back to a date!
		//date = (Date) dateFormat.parse(jsonObj.getString("date"));  i dont think we need date we have timestamp         
		//startTime = jsonObj.getString("startTime");
		//endTime = jsonObj.getString("endTime");
		startTime = parseTimeFromJson(jsonObj,"startTime");
		endTime = parseTimeFromJson(jsonObj,"endTime");
		putFields();
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