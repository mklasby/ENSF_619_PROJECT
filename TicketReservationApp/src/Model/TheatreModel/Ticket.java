package Model.TheatreModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.json.JSONException;
import org.json.JSONObject;

public class Ticket extends JSONObject {
	private Movie movie;
	private Theatre theatre;
	private ShowTime showTime;
	private Seat seat;
	private int ticketID;
	private double price;

	public Ticket(ResultSet allTickets) throws SQLException {

		movie = new Movie(allTickets);
		theatre = new Theatre(allTickets);
		showTime = new ShowTime(allTickets);
		seat = new Seat(allTickets);
		ticketID = allTickets.getInt("TicketID");
		setPrice(allTickets.getDouble("Price"));
	}

	private void putFields() {
		try {
			put("movieName", movie.getMovieName());
			put("seatNumber", seat.getSeatNum());
			put("showTimeId", showTime.getShowTimeID());
			put("showTime", showTime.getStartTime().toString());
			put("theatreName", theatre.getTheatreName());
			put("price", price);
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}

	public Ticket(Movie selectedMovie) {
		this.setMovie(selectedMovie);
	}

	public void encode() {
		this.putFields();
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public ShowTime getShowTime() {
		return showTime;
	}

	public void setShowTime(ShowTime showTime) {
		this.showTime = showTime;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
