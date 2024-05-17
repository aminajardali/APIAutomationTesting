package StepDefinitions;

import Utility.TransferTokenRequest;
import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import MockControllers.TestContextConfiguration;

import static io.restassured.RestAssured.given;

@SpringBootTest
@ContextConfiguration(classes = {TestContextConfiguration.class})
public class TRANSFERstepdefs {

    private String shorthash;
    private TransferTokenRequest requestBody;
    private Response response;

    @Given("a user with transfer shorthash {string}")
    public void a_user_with_transfer_shorthash(String shorthash) {
        this.shorthash = shorthash;
        System.out.println("Given a user with transfer shorthash: " + shorthash);
    }

    @Given("the token transfer request body:")
    public void the_token_transfer_request_body(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the token transfer request body: " + docString);
    }

    @Given("the token transfer request body with negative amount:")
    public void the_token_transfer_request_body_with_negative_amount(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the token transfer request body with negative amount: " + docString);
    }

    @Given("the token transfer request body with missing fields:")
    public void the_token_transfer_request_body_with_missing_fields(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the token transfer request body with missing fields: " + docString);
    }

    @Given("the token transfer request body with invalid recipient:")
    public void the_token_transfer_request_body_with_invalid_recipient(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the token transfer request body with invalid recipient: " + docString);
    }

    @When("the user sends a POST request to transfer the token")
    public void the_user_sends_a_post_request_to_transfer_the_token() {
        String urlString = "http://127.0.0.1:1336/token/transfer/" + shorthash;
        System.out.println("Sending POST request to URL: " + urlString);
        System.out.println("Request Body: " + new Gson().toJson(requestBody));

        this.response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(urlString);

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Then("the transfer response status should be {int}")
    public void the_transfer_response_status_should_be(Integer status) {
        int actualStatus = response.getStatusCode();
        if (actualStatus != status) {
            System.out.println("Expected response code: " + status + " but got: " + actualStatus);
            System.out.println("Response Body: " + response.getBody().asString());
            throw new AssertionError("Expected response code: " + status + " but got: " + actualStatus);
        }
        System.out.println("Then the transfer response status should be: " + status);
    }

    @Then("the transfer response body should indicate {string}")
    public void the_transfer_response_body_should_indicate(String message) {
        String responseBody = response.getBody().asString();
        if (!responseBody.contains(message)) {
            System.out.println("Expected response body to contain: " + message + " but got: " + responseBody);
            throw new AssertionError("Expected response body to contain: " + message + " but got: " + responseBody);
        }
        System.out.println("Then the transfer response body should indicate: " + message);
    }

    private TransferTokenRequest parseRequestBody(String docString) {
        return new Gson().fromJson(docString, TransferTokenRequest.class);
    }
}
