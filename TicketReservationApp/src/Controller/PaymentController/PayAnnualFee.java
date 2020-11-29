package Controller.PaymentController;

import Model.PaymentModel.AnnualReceipt;
import Model.UserModel.RegisteredUser;

public class PayAnnualFee {
	private double amount;
	private AnnualReceipt theReceipt;
	
	public PayAnnualFee(RegisteredUser theUser, int year) {
		theReceipt = new AnnualReceipt(year);
		payAnnualFee(theUser);
	}
	
	public void payAnnualFee(RegisteredUser theUser) {
		theReceipt.setAmount(amount);
		theReceipt.setTheUser(theUser);
		theUser.setHasPaidDues(true);
	}

	public void setTheReceipt(AnnualReceipt theReceipt) {
		this.theReceipt = theReceipt;
	}

	public AnnualReceipt getTheReceipt() {
		return theReceipt;
	}
}
