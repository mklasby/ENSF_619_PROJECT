package Controller.PaymentController;

import Model.PaymentModel.AnnualFee;
import Model.PaymentModel.AnnualReceipt;
import Model.PaymentModel.Cart;
import Model.PaymentModel.Coupon;
import Model.PaymentModel.Receipt;
import Model.PaymentModel.TicketReceipt;
import Model.TheatreModel.Ticket;
import Model.UserModel.RegisteredUser;
import Model.UserModel.User;
import org.json.JSONArray;

public class PaymentManager {
	private User user;
	private Cart cart;
	
	public PaymentManager(User user) {
		setUser(user);
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

	public JSONArray payForTicket(){
		JSONArray reply = new JSONArray();
		for (Ticket t : cart.getCartOfTickets()) {
			PayTicketFee ticketPayment = new PayTicketFee(t,user.getPaymentInfo().getCardNumber());
			Receipt thisReceipt = ticketPayment.getTheReceipt();
			reply.put(thisReceipt);
		}
		return reply;
		// send ticket to user email
		// send receipt to user email
    }

    public JSONArray payForAll(){
		JSONArray temp = null;
		if(!cart.getCartOfTickets().isEmpty()){
			temp = payForTicket();
		}
	}

    public AnnualReceipt payAnnualFee(RegisteredUser theUser, AnnualFee annualFee){
    	PayAnnualFee annualPayment = new PayAnnualFee(theUser, annualFee);
    	AnnualReceipt annualReceipt = annualPayment.getTheReceipt();
    	return annualReceipt;
    }

    public void refundTicket(Receipt thereceipt){
    	RefundTicket ticketRefund = new RefundTicket(thereceipt);
    	Coupon theCoupon = ticketRefund.getTheCoupon();
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
