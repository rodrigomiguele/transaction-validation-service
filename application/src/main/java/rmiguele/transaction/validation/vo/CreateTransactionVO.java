package rmiguele.transaction.validation.vo;

import rmiguele.transaction.validation.model.TransactionType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CreateTransactionVO implements Serializable {

    @NotNull
    private String transactionCode;

    @NotNull
    private TransactionType transactionType;

    @NotNull
    private Double transactionValue;

    @NotNull
    private Long transactionDate;

    @NotNull
    private String transactionSenderCode;

    @NotNull
    private String transactionReceiverCode;

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Double transactionValue) {
        this.transactionValue = transactionValue;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
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
