package Controller.PaymentController;

import Model.PaymentModel.AnnualFee;
import Model.PaymentModel.AnnualReceipt;
import Model.UserModel.RegisteredUser;

public class PayAnnualFee {
	
	private AnnualFee annualfee;
	private AnnualReceipt theReceipt;
	
	public PayAnnualFee(RegisteredUser theUser, AnnualFee annualFee) {
		theReceipt = new AnnualReceipt();
		this.annualfee = annualFee;
	
		payAnnualFee(theUser);
	}
	
	public void payAnnualFee(RegisteredUser theUser) {
		
		theReceipt.setTheUser(theUser);
		theUser.setHasPaidDues(true);
	}

	public void setTheReceipt(AnnualReceipt theReceipt) {
		this.theReceipt = theReceipt;
	}

	public AnnualReceipt getTheReceipt() {
		return theReceipt;
	}

	public AnnualFee getAnnualfee() {
		return annualfee;
	}

	public void setAnnualfee(AnnualFee annualfee) {
		this.annualfee = annualfee;
	}
}
