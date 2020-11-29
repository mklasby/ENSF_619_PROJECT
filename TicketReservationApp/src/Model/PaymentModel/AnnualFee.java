package Model.PaymentModel;

public class AnnualFee {
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	private double amount;
	private String name = "Annual Fee";
	
	public AnnualFee() {
		amount = 20.00;
	}
	
	
	public String toString() {
		return name + " $ " + amount;
	}
}
