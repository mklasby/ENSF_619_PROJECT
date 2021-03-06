package Controller.PaymentController;

import Model.PaymentModel.Voucher;
import Model.PaymentModel.Receipt;
import Model.PaymentModel.RefundReceipt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RefundTicket {
	private double adminFeeRate = 0.85;
	private double ticketPrice;
	double refundAmount;
	private Voucher theVoucher;
	private RefundReceipt refundReceipt;

	public RefundTicket(Receipt thereceipt, boolean isReg) {
		ticketPrice = thereceipt.getAmount();
		calculateRefund(thereceipt, isReg); // instantiates refundAmount
		refundTicket(thereceipt);
	}

	/**
	 * 
	 * @param thereceipt receipt of ticket purchase
	 */
	public void refundTicket(Receipt thereceipt) {
		createCoupon(thereceipt);
		createRefundReceipt();

	}

	/**
	 * creates coupon by setting its refund amount and expiry date
	 * 
	 * @param thereceipt ticket purchase receipt
	 */
	public void createCoupon(Receipt thereceipt) {
		theVoucher = new Voucher();
		theVoucher.setAmount(refundAmount);
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		c.add(Calendar.YEAR, 1);
		Date expiryDate = c.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = df.format(expiryDate);
		theVoucher.setExpiryDate(formattedDate); // sets expiry date of coupon
		theVoucher.putFields();
	}

	/**
	 * creates the refund receipt
	 */
	public void createRefundReceipt() {
		refundReceipt = new RefundReceipt();
		refundReceipt.setVoucherId(theVoucher.getVoucherId());
		refundReceipt.setAmount(refundAmount);
		refundReceipt.putFields();
	}

	/**
	 * sets the refund amount
	 * 
	 * @param thereceipt
	 * @param isReg
	 */
	private void calculateRefund(Receipt thereceipt, boolean isReg) {
		if (!isReg) {
			refundAmount = adminFeeRate * thereceipt.getAmount();
		} else {
			refundAmount = thereceipt.getAmount();
		}
	}

	public double getAdminFeeRate() {
		return adminFeeRate;
	}

	public void setAdminFeeRate(double adminFeeRate) {
		this.adminFeeRate = adminFeeRate;
	}

	public double getTicketAmount() {
		return ticketPrice;
	}

	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Voucher getTheVoucher() {
		return theVoucher;
	}

	public void setTheVoucher(Voucher theVoucher) {
		this.theVoucher = theVoucher;
	}
	
	public RefundReceipt getRefundReceipt() {
		return this.refundReceipt;
	}

}
