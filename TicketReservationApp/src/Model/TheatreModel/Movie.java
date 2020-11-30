package Model.TheatreModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie extends JSONObject {
	private String movieName;
	private double moviePrice;
	private boolean isEarlyAccess; 
	private ArrayList<Theatre> theatreList;

	public Movie(String movieName, double moviePrice, boolean earlyAccess) {
		setMovieName(movieName);
		setMoviePrice(moviePrice);
		setTheatreList(new ArrayList<Theatre>());
		putFields();
	}

	private void putFields() {
		try {
			put("movieName", movieName);
			put("moviePrice", moviePrice);
			put("isEarlyAccess", isEarlyAccess);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Movie(ResultSet allMovies) throws SQLException {
		this.movieName = allMovies.getString("MovieName");
		this.isEarlyAccess = allMovies.getBoolean("IsEarlyAccess");
		putFields();
	}

	public Movie(JSONObject jsonObj) throws JSONException {
		movieName = jsonObj.getString("movieName");
		moviePrice = jsonObj.getDouble("moviePrice");
		isEarlyAccess = jsonObj.getBoolean("isEarlyAccess");
		putFields();
	}

	private void setMoviePrice(double moviePrice) {
		this.moviePrice = moviePrice;
	}

	public double getMoviePrice() {
		return moviePrice;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public boolean isEarlyAccess() {
		return isEarlyAccess;
	}

	public void setEarlyAccess(boolean earlyAccess) {
		this.isEarlyAccess = earlyAccess;
	}

	public ArrayList<Theatre> getTheatreList() {
		return theatreList;
	}

	private void setTheatreList(ArrayList<Theatre> theatreList) {
		this.theatreList = theatreList;
	}

}
