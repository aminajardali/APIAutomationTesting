package StepDefinitions;

import Utility.IssueTokenRequest;
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
public class ISSUEstepdefs {

    private String shorthash;
    private IssueTokenRequest requestBody;
    private Response response;

    @Given("a user with token issuance shorthash {string}")
    public void a_user_with_token_issuance_shorthash(String shorthash) {
        this.shorthash = shorthash;
        System.out.println("Given a user with token issuance shorthash: " + shorthash);
    }

    @Given("the user has created a tokenized deposit")
    public void the_user_has_created_a_tokenized_deposit() {
        // Implement logic to ensure a tokenized deposit has been created if necessary
        System.out.println("Given the user has created a tokenized deposit");
    }

    @Given("the token issuance request body:")
    public void the_token_issuance_request_body(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the token issuance request body: " + docString);
    }

    @Given("the token issuance request body with an invalid wallet address:")
    public void the_token_issuance_request_body_with_an_invalid_wallet_address(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the token issuance request body with an invalid wallet address: " + docString);
    }

    @Given("the token issuance request body with missing fields:")
    public void the_token_issuance_request_body_with_missing_fields(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the token issuance request body with missing fields: " + docString);
    }

    @Given("the token issuance request body with an excessive amount:")
    public void the_token_issuance_request_body_with_an_excessive_amount(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the token issuance request body with an excessive amount: " + docString);
    }

    @Given("the token issuance request body with invalid syntax:")
    public void the_token_issuance_request_body_with_invalid_syntax(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the token issuance request body with invalid syntax: " + docString);
    }

    @When("the user sends a POST request to issue the token")
    public void the_user_sends_a_post_request_to_issue_the_token() {
        String urlString = "http://127.0.0.1:1336/token/issue/" + shorthash;
        System.out.println("Sending POST request to URL: " + urlString);
        System.out.println("Request Body: " + new Gson().toJson(requestBody));

        this.response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(urlString);

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Then("the token issuance response status should be {int}")
    public void the_token_issuance_response_status_should_be(Integer status) {
        int actualStatus = response.getStatusCode();
        if (actualStatus != status) {
            System.out.println("Expected response code: " + status + " but got: " + actualStatus);
            System.out.println("Response Body: " + response.getBody().asString());
            throw new AssertionError("Expected response code: " + status + " but got: " + actualStatus);
        }
        System.out.println("Then the token issuance response status should be: " + status);
    }

    @Then("the token issuance response body should indicate {string}")
    public void the_token_issuance_response_body_should_indicate(String message) {
        String responseBody = response.getBody().asString();
        if (!responseBody.contains(message)) {
            System.out.println("Expected response body to contain: " + message + " but got: " + responseBody);
            throw new AssertionError("Expected response body to contain: " + message + " but got: " + responseBody);
        }
        System.out.println("Then the token issuance response body should indicate: " + message);
    }

    private IssueTokenRequest parseRequestBody(String docString) {
        return new Gson().fromJson(docString, IssueTokenRequest.class);
    }
}
