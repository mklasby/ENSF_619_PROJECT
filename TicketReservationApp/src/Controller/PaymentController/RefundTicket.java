package Controller.PaymentController;

import Model.PaymentModel.Coupon;
import Model.PaymentModel.Receipt;
import Model.PaymentModel.RefundReceipt;

import java.util.Calendar;
import java.util.Date;

public class RefundTicket {
	private double adminFeeRate = 0.15;
	private double ticketPrice;
	double refundAmount;
	private Coupon theCoupon;
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
	 * @param thereceipt ticket purchase receipt
	 */
	public void createCoupon(Receipt thereceipt) {
		theCoupon = new Coupon();
		theCoupon.setAmount(refundAmount);
		Date receiptDate = thereceipt.getTheDate();
		Calendar c = Calendar.getInstance();
		c.setTime(receiptDate);
		c.add(Calendar.YEAR, 1);
		Date expiryDate = c.getTime();
		theCoupon.setExpiryDate(expiryDate); // sets expiry date of coupon
	}
	
	/**
	 * creates the refund receipt
	 */
	public void createRefundReceipt() {
		refundReceipt = new RefundReceipt();
		refundReceipt.setCouponId(theCoupon.getCouponId());
		refundReceipt.setAmount(refundAmount);
	}
	
	/**
	 * sets the refund amount 
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
	public Voucher getTheCoupon() {
		return theCoupon;
	}
	public void setTheCoupon(Coupon theCoupon) {
		this.theCoupon = theCoupon;
	}
	

}
