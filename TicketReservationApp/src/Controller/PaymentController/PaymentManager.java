package Controller.PaymentController;

import Model.PaymentModel.*;
import Model.PaymentModel.Voucher;
import Model.TheatreModel.Ticket;
import Model.UserModel.RegisteredUser;
import Model.UserModel.User;



import org.json.JSONArray;
import org.json.JSONException;

public class PaymentManager {
	private User user;
	private Cart cart;
	private JSONArray reply;

	public PaymentManager(User user) {
		setUser(user);
		reply = new JSONArray();
	}

	// give back a list of ticket id
	// flush cart 313 BOSS
	public JSONArray getCart() {

		JSONArray reply = new JSONArray();
		for (Ticket t : cart.getCartOfTickets()) {
			reply.put(t.toString());
		}

		if (cart.getAnnualFee() != null) {
			reply.put(cart.getAnnualFee().toString());
		}

		return reply;
	}

	public void payForTicket() {
		for (Ticket t : cart.getCartOfTickets()) {
			PayTicketFee ticketPayment = new PayTicketFee(t, user.getPaymentInfo().getCardNumber());
			Receipt thisReceipt = ticketPayment.getTheReceipt();
			reply.put(thisReceipt);
		}

		// send ticket to user email
		// send receipt to user email
	}

	public JSONArray payForAll() {

		if (!cart.getCartOfTickets().isEmpty()) {
			payForTicket();
		}
		if (cart.getAnnualFee() != null) {
			AnnualReceipt annualreceipt = payAnnualFee(user, cart.getAnnualFee());
			reply.put(annualreceipt);
		}
		return reply;
	}

	public AnnualReceipt payAnnualFee(User user2, AnnualFee annualFee) {
		PayAnnualFee annualPayment = new PayAnnualFee(user2, annualFee);
		AnnualReceipt annualReceipt = annualPayment.getTheReceipt();
		return annualReceipt;
	}

	public JSONArray refundTicket(Receipt thereceipt) {
		try {
			boolean isReg = false;
			if (user.getString("userType").equals("M") | user.getString("userType").equals("R")) {
				isReg = true;
			}
			RefundTicket ticketRefund = new RefundTicket(thereceipt, isReg);
			Voucher theVoucher = ticketRefund.getTheVoucher();
			RefundReceipt theRefund = ticketRefund.getRefundReceipt();
			reply.put(theVoucher);
			reply.put(theRefund);
			return reply;

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
