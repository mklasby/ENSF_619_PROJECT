package Model.PaymentModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentInfo extends JSONObject {

    private int cardNumber;
    private String creditCardType;
    // do i need a private username here to track? nah

    public PaymentInfo(int cardNumber, String cardType) {
        setCardNumber(cardNumber);
        setCreditCardType(cardType);
        putFields();

    }

    public void putFields() {
        try {
            put("cardNumber", cardNumber);
            put("creditCardType", creditCardType);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public PaymentInfo(ResultSet resultSet) throws SQLException {
        this.cardNumber = resultSet.getInt("CreditCardNumber");
        this.creditCardType = resultSet.getString("CreditCardType");
        putFields();
    }

    public PaymentInfo(JSONObject jsonObj) throws JSONException {
        cardNumber = jsonObj.getInt("cardNumber");
        creditCardType = jsonObj.getString("creditCardType");
        putFields();// did we miss this?
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String cardType) {
        this.creditCardType = cardType;
    }

}
