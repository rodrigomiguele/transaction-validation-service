package rmiguele.transaction.validation.resource;

import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.model.Transaction;
import rmiguele.transaction.validation.service.TransactionService;
import rmiguele.transaction.validation.vo.CreateTransactionVO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("/transaction")
public class TransactionResource {

    @Inject
    TransactionService transactionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createTransaction(CreateTransactionVO vo) {
        var createTransactionCommand = new CreateTransactionCommand();
        createTransactionCommand.setTransactionCode(vo.getTransactionCode());
        createTransactionCommand.setTransactionType(vo.getTransactionType());
        createTransactionCommand.setTransactionValue(vo.getTransactionValue());
        createTransactionCommand.setTransactionDate(new Date(vo.getTransactionDate()));
        createTransactionCommand.setTransactionSenderCode(vo.getTransactionSenderCode());
        createTransactionCommand.setTransactionReceiverCode(vo.getTransactionReceiverCode());

        transactionService.createTransaction(createTransactionCommand);
    }
}