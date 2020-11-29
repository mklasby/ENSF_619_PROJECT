package Model.TheatreModel;

import CommonMessage.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectTheatre implements MessageConstants {
	private ArrayList<Theatre> theatreList;

	public SelectTheatre() {

	}

	public SelectTheatre(ResultSet result) {
		theatreList = new ArrayList<Theatre>();
		try {
			do {
				Theatre thisTheatre = new Theatre(result);
				theatreList.add(thisTheatre);
			} while (result.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Message getTheatreList(ResultSet allTheatres) throws SQLException {
		do {
			Theatre thisTheatre = new Theatre(allTheatres);
			theatreList.add(thisTheatre);
		} while (allTheatres.next());
		JSONArray theatreData = new JSONArray();
		for (Theatre theatre : theatreList) {
			theatreData.put(theatre);
		}
		Message response = new Message(OK, theatreData);
		return response;
	}

	public JSONArray getTheatreList() {
		JSONArray response = new JSONArray();
		for (Theatre theatre : theatreList) {
			response.put(theatre);
		}
		return response;
	}

	public void setTheatreList(ArrayList<Theatre> theatreList) {
		this.theatreList = theatreList;
	}

	private Theatre parseTheatreList(String theatreName) { // How is the connection? we already have the array of movies
		for (Theatre theatreObj : theatreList) { // Changed signature
			if (theatreObj.getTheatreName().equals(theatreName))
				return theatreObj;
		}
		return null;
	}

	public Theatre selectTheatre(String theatreName) { // original signature selectMovie(ResultSet movie)
		Theatre theatre = parseTheatreList(theatreName);
		return theatre;
	}
}