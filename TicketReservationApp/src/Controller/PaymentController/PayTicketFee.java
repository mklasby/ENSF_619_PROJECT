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

		// payForTicket(theTicket);
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
