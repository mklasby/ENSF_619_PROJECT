package Controller.PaymentController;

import Model.PaymentModel.AnnualFee;
import Model.PaymentModel.AnnualReceipt;
import Model.UserModel.RegisteredUser;
import Model.UserModel.User;

public class PayAnnualFee {

	private AnnualFee annualfee;
	private AnnualReceipt theReceipt;

	public PayAnnualFee(User user2, AnnualFee annualFee) {
		theReceipt = new AnnualReceipt();
		theReceipt.setCreditCardNumber(user2.getPaymentInfo().getCardNumber());
		theReceipt.setReceiptType("Annual");
		theReceipt.setAmount(annualFee.getAmount());
		this.annualfee = annualFee;
		payAnnualFee(user2); // unnecessary?
	}

	public void payAnnualFee(User user2) {
		// TODO: Don't think we care? Causing issues since we cannot cast from user to
		// Reg user
		// user2.setHasPaidDues(true);
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
