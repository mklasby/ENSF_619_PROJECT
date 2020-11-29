package Model.TheatreModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie extends JSONObject {
	private String movieName;
	private double moviePrice;
	private boolean earlyAccess; // what is the point in a movie object having this
	private ArrayList<Theatre> theatreList;

	public Movie(String movieName, double moviePrice, boolean earlyAccess) {
		setMovieName(movieName);
		setMoviePrice(moviePrice);
		setTheatreList(new ArrayList<Theatre>());
	}

	public Movie(ResultSet allMovies) throws SQLException {
		this.movieName = allMovies.getString("MovieName");
		this.earlyAccess = allMovies.getBoolean("earlyAccess");
	}
	
	public Movie(JSONObject jsonObj) throws JSONException {
		movieName = jsonObj.getString("MovieName");
		earlyAccess = jsonObj.getBoolean("isEarlyName");
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
		return earlyAccess;
	}

	public void setEarlyAccess(boolean earlyAccess) {
		this.earlyAccess = earlyAccess;
	}

	public ArrayList<Theatre> getTheatreList() {
		return theatreList;
	}

	private void setTheatreList(ArrayList<Theatre> theatreList) {
		this.theatreList = theatreList;
	}

}
