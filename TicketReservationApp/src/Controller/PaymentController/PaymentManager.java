package paymentManager;

public class PaymentManager {
	public User user;
	
	public PaymentManager(User user) {
		setUser(user);
	}
	
	public void payForTicket(Ticket theTicket){
		PayTicketFee ticketPayment = new PayTicketFee(theTicket);
		TicketReceipt ticketReceipt = ticketPayment.getTheReceipt();
		// send ticket to user email
		// send receipt to user email

    }

    public void payAnnualFee(RegisteredUser theUser, int year){
    	PayAnnualFee annualPayment = new PayAnnualFee(theUser, year);
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
