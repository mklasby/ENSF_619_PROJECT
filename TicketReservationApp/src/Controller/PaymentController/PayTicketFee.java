package Controller.PaymentController;

import Model.PaymentModel.TicketReceipt;
import Model.TheatreModel.Ticket;

public class PayTicketFee {
	private TicketReceipt theReceipt;

	public PayTicketFee(Ticket theTicket, int creditCardNumber) {
		theReceipt = new TicketReceipt(theTicket.getTicketID(), creditCardNumber);
		theReceipt.setAmount(theTicket.getPrice());
		System.out.println("This is the price of the ticket " + theTicket.getPrice());
		theReceipt.setReceiptType("Ticket");
		theReceipt.setCreditCardNumber(creditCardNumber);
		theReceipt.setTicketId(theTicket.getTicketID());
		theReceipt.putFields();
	}


	public TicketReceipt getTheReceipt() {
		return theReceipt;
	}

	public void setTheReceipt(TicketReceipt theReceipt) {
		this.theReceipt = theReceipt;
	}

}
