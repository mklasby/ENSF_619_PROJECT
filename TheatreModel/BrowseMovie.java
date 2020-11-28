package TheatreModel;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BrowseMovie {
	private ArrayList<Movie> movieList;
    private boolean isRegUser;
    
    public BrowseMovie(ResultSet allMovies, boolean isRegUser) {
    	setIsRegUser(isRegUser);
    }
    
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}

	public boolean isRegUser() {
		return isRegUser;
	}

	public void setIsRegUser(boolean isRegUser) {
		this.isRegUser = isRegUser;
	}
	
	private Movie parseMovieList(String movieName) {  // How is the connection? we already have the array of movies
		for (Movie movieObj : movieList) {             //Changed signature
			if (movieObj.getMovieName().equals(movieName))
				return movieObj;
		}
		return null;
	}
	
	public Movie selectMovie(String movieName) {       // original signature selectMovie(ResultSet movie)
		Movie movie = parseMovieList(movieName);
		return movie;
	}
	
}
