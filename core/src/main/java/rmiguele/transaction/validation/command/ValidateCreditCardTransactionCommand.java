package rmiguele.transaction.validation.command;

import java.util.Date;

public class ValidateCreditCardTransactionCommand implements Command {

    private String transactionCode;

    private String transactionSenderCode;

    private Double transactionValue;

    private Date transactionDate;

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionSenderCode() {
        return transactionSenderCode;
    }

    public void setTransactionSenderCode(String transactionSenderCode) {
        this.transactionSenderCode = transactionSenderCode;
    }

    public Double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Double transactionValue) {
        this.transactionValue = transactionValue;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.VALIDATE_CREDIT_CART_TRANSACTION;
    }
}
