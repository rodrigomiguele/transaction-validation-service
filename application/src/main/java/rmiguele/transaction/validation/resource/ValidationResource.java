package rmiguele.transaction.validation.resource;

import rmiguele.transaction.validation.model.Validation;
import rmiguele.transaction.validation.service.ValidationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/validation")
public class ValidationResource {

    @Inject
    ValidationService validationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Validation> getViolations(@QueryParam("transaction") String transactionCode) {
        if (transactionCode == null) {
            return validationService.getValidations();
        }
        return validationService.getValidationsByTransactionCode(transactionCode);
    }
}