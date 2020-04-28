package rmiguele.transaction.validation.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import rmiguele.transaction.validation.model.TransactionType;
import rmiguele.transaction.validation.vo.CreateTransactionVO;

import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@QuarkusTest
class TransactionResourceTest {

    @Test
    void testCreateTransaction() {
        var vo = new CreateTransactionVO();
        vo.setTransactionCode(UUID.randomUUID().toString());
        vo.setTransactionType(TransactionType.CREDIT_CARD);
        vo.setTransactionValue(100.00);
        vo.setTransactionDate(new Date().getTime());
        vo.setTransactionSenderCode(UUID.randomUUID().toString());
        vo.setTransactionReceiverCode(UUID.randomUUID().toString());
        given()
                .body(vo)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .post("/transaction")
                .then()
                .statusCode(204);
    }
}