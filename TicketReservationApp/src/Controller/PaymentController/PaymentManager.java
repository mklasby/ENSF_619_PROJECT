package Controller.PaymentController;

import Model.PaymentModel.AnnualFee;
import Model.PaymentModel.AnnualReceipt;
import Model.PaymentModel.Coupon;
import Model.PaymentModel.Receipt;
import Model.PaymentModel.TicketReceipt;
import Model.TheatreModel.Ticket;
import Model.UserModel.RegisteredUser;
import Model.UserModel.User;

public class PaymentManager {
	public User user;
	public Cart card;
	
	public PaymentManager(User user) {
		setUser(user);
	}
	
	
	public void payForTicket(Ticket theTicket){
		PayTicketFee ticketPayment = new PayTicketFee(theTicket);
		TicketReceipt ticketReceipt = ticketPayment.getTheReceipt();
		// send ticket to user email
		// send receipt to user email

    }

    public void payAnnualFee(RegisteredUser theUser, AnnualFee annualFee){
    	PayAnnualFee annualPayment = new PayAnnualFee(theUser, annualFee);
    	AnnualReceipt annualReceipt = annualPayment.getTheReceipt();
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

}
