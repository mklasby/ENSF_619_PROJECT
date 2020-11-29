package Model.TheatreModel;

import CommonMessage.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class SelectTheatre implements MessageConstants {
	private ArrayList<Theatre> theatreList;
	private boolean isRegUser;

	public SelectTheatre() {

	}

	public Message getTheatreList(ResultSet allTheatres, boolean isRegUser) throws SQLException {
		setIsRegUser(isRegUser);
		do {
			Theatre thisTheatre = new Theatre(allTheatres);
			theatreList.add(thisTheatre);
		} while (allTheatres.next());
		JSONArray theatreData = new JSONArray();
		for (Theatre theatre : theatreList) {
			theatreData.put(theatre.toString());
		}
		Message response = new Message(OK, theatreData);
		return response;
	}

	public ArrayList<Theatre> getTheatreList() {
		return theatreList;
	}

	public void setTheatreList(ArrayList<Theatre> theatreList) {
		this.theatreList = theatreList;
	}

	public boolean isRegUser() {
		return isRegUser;
	}

	public void setIsRegUser(boolean isRegUser) {
		this.isRegUser = isRegUser;
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

	public Message getTheatreList(ResultSet theatreList2) {
		return null;
	}
}