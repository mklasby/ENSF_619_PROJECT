package Model.PaymentModel;

public class AnnualFee {
	private double amount;
	private String name = "Annual Fee";
	
	public AnnualFee() {
		amount = 20.00;
	}
	
	
	public String toString() {
		return name + " $ " + amount;
	}
}
