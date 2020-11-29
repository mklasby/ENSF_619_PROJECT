package Model.TheatreModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class Theatre extends JSONObject {
	private String theatreName;

	public Theatre(String theatreName) { // what is the parameter ResultSet for?
		setTheatreName(theatreName); // how come theatre has no seats
		putFields();
	}

	public void putFields() {
		try {
			put("theatreName", theatreName);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Theatre(ResultSet allTheatres) throws SQLException {
		this.theatreName = allTheatres.getString("TheatreName");
		putFields();
	}

	public Theatre(JSONObject jsonObj) throws JSONException {
		theatreName = jsonObj.getString("theatreName");
		putFields();
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

}
