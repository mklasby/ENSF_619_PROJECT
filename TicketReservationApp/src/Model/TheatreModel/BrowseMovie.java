package Model.TheatreModel;

import CommonMessage.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class BrowseMovie implements MessageConstants {
	private ArrayList<Movie> movieList;
	

	public Message getMovieList(ResultSet allMovies) throws SQLException {
		movieList = new ArrayList<Movie>();
		
		do {
			Movie thisMovie = new Movie(allMovies);
			movieList.add(thisMovie);
		} while (allMovies.next());
		JSONArray movieData = new JSONArray();
		for (Movie movie : movieList) {
			movieData.put(movie);
		}
		Message response = new Message(OK, movieData);
		return response;
	}

	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}


	private Movie parseMovieList(String movieName) {
		for (Movie movieObj : movieList) {
			if (movieObj.getMovieName().equals(movieName))
				return movieObj;
		}
		return null;
	}

	public Movie selectMovie(String movieName) { 
		Movie movie = parseMovieList(movieName);
		return movie;
	}

}
