package rmiguele.transaction.validation.command;

public class RestrictedListValidationCommand implements Command {

    private String transactionCode;

    private String transactionSenderCode;

    private String transactionReceiverCode;

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

    public String getTransactionReceiverCode() {
        return transactionReceiverCode;
    }

    public void setTransactionReceiverCode(String transactionReceiverCode) {
        this.transactionReceiverCode = transactionReceiverCode;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.RESTRICTED_LIST_VALIDATION;
    }
}
