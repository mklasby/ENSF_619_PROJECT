package Controller.PaymentController;

import Model.PaymentModel.*;
import Model.PaymentModel.Coupon;
import Model.TheatreModel.Ticket;
import Model.UserModel.RegisteredUser;
import Model.UserModel.User;
import org.json.JSONArray;

public class PaymentManager {
	private User user;
	private Cart cart;
	private JSONArray reply;
	
	public PaymentManager(User user) {
		setUser(user);
		reply = null;
	}
	
	//give back a list of ticket id
	//flush cart 313 BOSS
	public JSONArray getCart() {

		JSONArray reply = new JSONArray();
		for (Ticket t : cartOfTickets) {
			reply.put(t.toString());
		}

		if (annualFee != null) {
			reply.put(annualFee.toString());
		}

		return reply;
	}

	public void payForTicket(){
		for (Ticket t : cart.getCartOfTickets()) {
			PayTicketFee ticketPayment = new PayTicketFee(t,user.getPaymentInfo().getCardNumber());
			Receipt thisReceipt = ticketPayment.getTheReceipt();
			reply.put(thisReceipt);
		}

		// send ticket to user email
		// send receipt to user email
    }

    public JSONArray payForAll(){

		if(!cart.getCartOfTickets().isEmpty()){
			payForTicket();
		}
		if (cart.getAnnualFee()!= null){
			AnnualReceipt annualreceipt = payAnnualFee((RegisteredUser) user, cart.getAnnualFee());
			reply.put(annualreceipt);
		}
		return reply;
	}

    public AnnualReceipt payAnnualFee(RegisteredUser theUser, AnnualFee annualFee){
    	PayAnnualFee annualPayment = new PayAnnualFee(theUser, annualFee);
    	AnnualReceipt annualReceipt = annualPayment.getTheReceipt();
    	return annualReceipt;
    }

    public void refundTicket(Receipt thereceipt){
    	RefundTicket ticketRefund = new RefundTicket(thereceipt);
    	Voucher theCoupon = ticketRefund.getTheCoupon();
    	//send ticket to user
    }
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setCart(Cart cart) {
		this.cart= cart;
	}

}
