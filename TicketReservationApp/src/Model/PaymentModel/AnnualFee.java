package Model.PaymentModel;

import java.io.ObjectOutputStream.PutField;

import org.json.JSONException;
import org.json.JSONObject;

public class AnnualFee extends JSONObject {

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
		putFields();
	}

	private void putFields() {
		try {
			put("amount", amount);
			put("name", name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private double amount;
	private String name = "Annual Fee";

	public AnnualFee() {
		amount = 20.00;
		putFields();
	}

	public String toString() {
		putFields();
		return name + " $ " + amount;
	}
}
