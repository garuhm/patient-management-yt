import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class PatientIntegrationTest {
    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void getPatients_UsingValidToken_ReturnsOK() {
        String loginPayload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;

        Response loginResponse = RestAssured.given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        String token = loginResponse.jsonPath().getString("token");

        Response patientResponse = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/v1/patients")
                .then()
                .statusCode(200)
                .body("patients", notNullValue())
                .body("patients.size()", greaterThan(0))
                .extract()
                .response();
    }

    @Test
    public void getPatients_NoToken_ReturnsUnauthorized() {
        String loginPayload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;

        Response loginResponse = RestAssured.given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        String token = loginResponse.jsonPath().getString("token");

        Response patientResponse = RestAssured.given()
                .when()
                .get("/api/v1/patients")
                .then()
                .statusCode(401)
                .extract()
                .response();
    }
}
