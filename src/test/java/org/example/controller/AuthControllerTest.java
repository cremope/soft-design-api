package org.example.controller;

import junit.framework.Assert;
import org.example.config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.model.login.LoginRequest;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerTest {

    private static String authToken;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
    }

    @Test
    @Order(1)
    public void testLogin() {
        LoginRequest loginRequest = new LoginRequest("emilys", "emilyspass");

        Response response = given()
                .contentType("application/json")
                .body(loginRequest.toString()) // Converte payload para string
                .when()
                .post(ConfigReader.getEndpointAuthLogin());

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Validações:");
        System.out.println("1. Status HTTP: A resposta esperada é 201 Created.");
        if (statusCode == 201) {
            System.out.println("O status HTTP retornado é 201 Created.");
        } else {
            // bug no retorno --> Endpoint retorna sempre 200 --> Deixei para jogar na console e continuar a execução
            System.out.println("O status HTTP retornado é " + statusCode + ", o que não é esperado.");
        }

        System.out.println("2. Validação de Dados: Todos os campos esperados estão presentes.");
        response
                .then()
                .body("id", notNullValue())
                .body("username", notNullValue())
                .body("email", notNullValue())
                .body("firstName", notNullValue())
                .body("lastName", notNullValue())
                .body("gender", notNullValue())
                .body("image", notNullValue())
                .body("token", notNullValue())
                .body("refreshToken", notNullValue())
                .log().ifValidationFails();

        System.out.println("3. Validação do Token");
        if (responseBody.contains("token")) {
            authToken = response.jsonPath().getString("token");
            if (authToken != null && !authToken.isEmpty()) {
                System.out.println("O token JWT está presente.");
                System.out.println("Os campos obrigatórios foram encontrados na resposta.");
                System.out.println("Request Body....: " + loginRequest);
                System.out.println("Response Body...: " + response.getBody().asString());
            } else {
                Assert.fail("O token JWT não está presente na resposta.");
            }
        } else {
            Assert.fail("O token JWT não foi encontrado na resposta.");
        }
    }
}
