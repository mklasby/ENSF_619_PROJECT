package Model.PaymentModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentInfo extends JSONObject{
	
	private int cardNunmber;
	private String creditCardType;
	// do i need a private username here to track? nah
	
    public PaymentInfo(int cardNumber, String cardType){
        setCardNumber(cardNumber);
        setCreditCardType(cardType);
        putFields();

    }
    
    public void putFields() {
		try {
			put("cardNunmber", cardNunmber);
			put("creditCardType", creditCardType);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
	public PaymentInfo(ResultSet resultSet) throws SQLException {
		this.cardNunmber = resultSet.getInt("CreditCardNumber");
		this.creditCardType = resultSet.getString("CreditCardType");
		putFields();
	}
    
	
	public PaymentInfo(JSONObject jsonObj) throws JSONException {
		cardNunmber = jsonObj.getInt("cardNunmber");
		creditCardType = jsonObj.getString("creditCardType");
		putFields();//did we miss this?
	}
	
    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCreditCardType() {
        return cardType;
    }

    public void setCreditCardType(String cardType) {
        this.cardType = cardType;
    }

    private int cardNumber;
    private String cardType;




}
