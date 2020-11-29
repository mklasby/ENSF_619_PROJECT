package Model.PaymentModel;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.TheatreModel.Ticket;

public class Cart extends JSONObject {
	private ArrayList<Ticket> cartOfTickets;
	private AnnualFee annualFee;
	
	public Cart() {
		setCartOfTickets(new ArrayList<Ticket>());
		annualFee = null;
		putFields();
	}
	

	public JSONArray getCart() {
		
		JSONArray reply = new JSONArray(); 
		for (Ticket t : cartOfTickets) {
			reply.put(t.toString());
		}
		
		if(annualFee!=null) {
			reply.put(annualFee.toString());
		}
		
		return reply;
	}
	
	private void putFields() {
		try {
			put("cartOfTickets", cartOfTickets);
			put("annualFee", annualFee);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addTicketToCart(Ticket ticket) {
				
		this.cartOfTickets.add(ticket);
	}
	
	public void addAnnualFee(AnnualFee annualFee){
	if(this.annualFee == null) {
		this.setAnnualFee(annualFee);
	}
	}
	
	
	public ArrayList<Ticket> getCartOfTickets() {
		return cartOfTickets;
	}

	public void setCartOfTickets(ArrayList<Ticket> cartOfTickets) {
		this.cartOfTickets = cartOfTickets;
	}

	public AnnualFee getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(AnnualFee annualFee) {
		this.annualFee = annualFee;
	}
	
	
	
	
}
