public class Card {

    private String cardNumber;
    private String holderName;
    private String expiryDate;
    private String cvv;
    private String cardType;
    private boolean hasDebitCard;
    private double balance;

    public Card(String cardNumber, String holderName, String expiryDate, String cvv, String cardType, boolean hasDebitCard, double balance)
    {
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardType = cardType;
        this.hasDebitCard = hasDebitCard;
        this.balance = balance;
    }

    public String getCardNumber() { return cardNumber; }
    public String getHolderName() { return holderName; }
    public String getExpiryDate() { return expiryDate; }
    public String getCVV() { return cvv; }
    public String getCardType() { return cardType; }
    public boolean getHasDebitCard() { return hasDebitCard; }
    public double getBalance() { return balance; }

}
