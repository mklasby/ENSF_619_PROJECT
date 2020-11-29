package Model.PaymentModel;

public class PaymentInfo {

    public PaymentInfo(int cardNumber, String cardType){
        setCardNumber(cardNumber);
        setCardType(cardType);

    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    private int cardNumber;
    private String cardType;




}
