package Controller.PaymentController;

import Model.PaymentModel.Voucher;
import Model.PaymentModel.Receipt;
import Model.PaymentModel.RefundReceipt;

import java.util.Calendar;
import java.util.Date;

public class RefundTicket {
	private double adminFeeRate = 0.15;
	private double ticketPrice;
	double refundAmount;
	private Voucher theVoucher;
	private RefundReceipt refundReceipt;

	public RefundTicket(Receipt thereceipt) {
		ticketPrice = thereceipt.getAmount();
		calculateRefund(thereceipt); // instantiates refundAmount
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
		theVoucher.setExpiryDate(expiryDate); // sets expiry date of coupon
	}

	/**
	 * creates the refund receipt
	 */
	public void createRefundReceipt() {
		refundReceipt = new RefundReceipt();
		refundReceipt.setCouponId(theVoucher.getCouponId());
		refundReceipt.setAmount(refundAmount);
	}

	/**
	 * sets the refund amount
	 * 
	 * @param thereceipt
	 */
	private void calculateRefund(Receipt thereceipt) {
		ticketPrice = thereceipt.getAmount();
		refundAmount = ticketPrice * adminFeeRate;
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

	public void setTheCoupon(Voucher theVoucher) {
		this.theVoucher = theVoucher;
	}

}
