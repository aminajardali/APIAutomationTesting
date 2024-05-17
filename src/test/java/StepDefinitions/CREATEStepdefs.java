package StepDefinitions;

import Utility.CreateDepositRequest;
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
public class CREATEStepdefs {

    private String shorthash;
    private CreateDepositRequest requestBody;
    private Response response;

    @Given("a new user with shorthash {string}")
    public void a_new_user_with_shorthash(String shorthash) {
        this.shorthash = shorthash;
        System.out.println("Given a new user with shorthash: " + shorthash);
    }

    @Given("the user wants to create a wallet and place tokens in it")
    public void the_user_wants_to_create_a_wallet_and_place_tokens_in_it() {
        System.out.println("Given the user wants to create a wallet and place tokens in it");
    }

    @Given("the following request body:")
    public void the_following_request_body(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the following request body: " + docString);
    }

    @Given("the following request body missing the beneficiary field:")
    public void the_following_request_body_missing_the_beneficiary_field(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the following request body missing the beneficiary field: " + docString);
    }

    @Given("the following request body with invalid tokenizedBalance:")
    public void the_following_request_body_with_invalid_tokenized_balance(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the following request body with invalid tokenizedBalance: " + docString);
    }

    @Given("the following request body with unsupported account type:")
    public void the_following_request_body_with_unsupported_account_type(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the following request body with unsupported account type: " + docString);
    }

    @Given("the following request body with invalid account number:")
    public void the_following_request_body_with_invalid_account_number(String docString) {
        this.requestBody = parseRequestBody(docString);
        System.out.println("Given the following request body with invalid account number: " + docString);
    }

    @When("the user submits a POST request to create the tokenized deposit")
    public void the_user_submits_a_post_request_to_create_the_tokenized_deposit() {
        String urlString = "http://127.0.0.1:1336/tokenized_deposit/create/" + shorthash; // Ensure this points to your test server
        System.out.println("Sending POST request to URL: " + urlString);
        System.out.println("Request Body: " + new Gson().toJson(requestBody));

        this.response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(urlString);

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer status) {
        int actualStatus = response.getStatusCode();
        if (actualStatus != status) {
            System.out.println("Expected response code: " + status + " but got: " + actualStatus);
            System.out.println("Response Body: " + response.getBody().asString());
            throw new AssertionError("Expected response code: " + status + " but got: " + actualStatus);
        }
        System.out.println("Then the response status should be: " + status);
    }

    @Then("the response body should indicate {string}")
    public void the_response_body_should_indicate(String message) {
        String responseBody = response.getBody().asString();
        if (!responseBody.contains(message)) {
            System.out.println("Expected response body to contain: " + message + " but got: " + responseBody);
            throw new AssertionError("Expected response body to contain: " + message + " but got: " + responseBody);
        }
        System.out.println("Then the response body should indicate: " + message);
    }

    private CreateDepositRequest parseRequestBody(String docString) {
        return new Gson().fromJson(docString, CreateDepositRequest.class);
    }
}
