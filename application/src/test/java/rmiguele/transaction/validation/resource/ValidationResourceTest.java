package rmiguele.transaction.validation.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ValidationResourceTest {

    @Test
    void testGetValidations() {
        given()
                .when()
                .get("/validation")
                .then()
                .statusCode(200);
    }
}