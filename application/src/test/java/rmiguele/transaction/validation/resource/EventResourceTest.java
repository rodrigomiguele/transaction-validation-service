package rmiguele.transaction.validation.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class EventResourceTest {

    @Test
    void testGetEvents() {
        given()
                .when()
                .get("/event")
                .then()
                .statusCode(200);
    }
}