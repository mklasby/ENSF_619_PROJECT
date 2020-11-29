package Model.TheatreModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class Theatre {
	private String theatreName;
	
	public Theatre(String theatreName) {                //what is the parameter ResultSet for?
		setTheatreName(theatreName);                    //how come theatre has no seats
	}
	
	public Theatre (ResultSet allTheatres) throws SQLException {
		this.theatreName = allTheatres.getString("TheatreName");
	}
	
	public Theatre(JSONObject jsonObj) throws JSONException {
		theatreName = jsonObj.getString("TheatreName");
	}
	
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

}
