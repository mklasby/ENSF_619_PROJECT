package Controller.PaymentController;

import Model.PaymentModel.TicketReceipt;
import Model.TheatreModel.Ticket;

public class PayTicketFee {
	private TicketReceipt theReceipt;

	public PayTicketFee(Ticket theTicket, int creditCardNumber) {
		theReceipt = new TicketReceipt(theTicket.getTicketID(), creditCardNumber);
		theReceipt.setAmount(theTicket.getPrice());
		theReceipt.setReceiptType("Ticket");
		theReceipt.setCreditCardNumber(creditCardNumber);
		theReceipt.setTicketId(theTicket.getTicketID());
<<<<<<< HEAD
		theReceipt.putFields();
		
//		payForTicket(theTicket);
=======

		// payForTicket(theTicket);
>>>>>>> 80c9ce4081ab13a4dc758be0dfa6b4361a644ff3
	}

	// public void payForTicket(Ticket theTicket) {
	// double amount = theTicket.getPrice();
	// int ticketNumber = theTicket.getTicketId();
	// theReceipt.setAmount(amount);
	// theReceipt.setTicketId(ticketNumber);
	// theTicket.setPaidfor(true);
	// }

	public TicketReceipt getTheReceipt() {
		return theReceipt;
	}

	public void setTheReceipt(TicketReceipt theReceipt) {
		this.theReceipt = theReceipt;
	}

}
