package rmiguele.transaction.validation.command;

import rmiguele.transaction.validation.model.ValidationType;

public class CreateValidationCommand {

    private String transactionCode;

    private ValidationType validationType;

    private String message;

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public ValidationType getValidationType() {
        return validationType;
    }

    public void setValidationType(ValidationType validationType) {
        this.validationType = validationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
