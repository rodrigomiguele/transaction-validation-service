package rmiguele.transaction.validation.command;

import java.util.Date;

public class ValidateTransactionCommand {

    private String transactionCode;

    private String transactionType;

    private Double transactionValue;

    private Date transactionDate;

    private String transactionSenderCode;

    private String transactionReceiverCode;

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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

    public String getTransactionSenderCode() {
        return transactionSenderCode;
    }

    public void setTransactionSenderCode(String transactionSenderCode) {
        this.transactionSenderCode = transactionSenderCode;
    }

    public String getTransactionReceiverCode() {
        return transactionReceiverCode;
    }

    public void setTransactionReceiverCode(String transactionReceiverCode) {
        this.transactionReceiverCode = transactionReceiverCode;
    }
}
