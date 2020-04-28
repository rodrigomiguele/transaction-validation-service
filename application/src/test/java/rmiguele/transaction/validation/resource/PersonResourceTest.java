package rmiguele.transaction.validation.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import rmiguele.transaction.validation.model.PersonSituation;
import rmiguele.transaction.validation.vo.CreatePersonVO;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

@QuarkusTest
class PersonResourceTest {

    @Test
    void testCreatePerson() {
        var vo = new CreatePersonVO();
        vo.setPersonSituation(PersonSituation.LEGAL);
        given()
                .body(vo)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .post("/person")
                .then()
                .statusCode(204);
    }

    @Test
    void testGetPersons() {
        given()
                .when()
                .get("/person")
                .then()
                .statusCode(200);
    }
}