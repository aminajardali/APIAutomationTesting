Feature: Tokenized Deposit Creation

  Scenario: Successful creation of a tokenized deposit for a new user
    Given a new user with shorthash "78C86D135089"
    Given the user wants to create a wallet and place tokens in it
    Given the following request body:
      """
      {
        "beneficiary":"CN=Alice, OU=Test Dept, O=R3, L=London, C=GB",
        "tokenizedBalance":"20",
        "commercialDepositCurrency":"USD",
        "tokenizedDepositCurrency":"AEDT",
        "accountNumber":"131s0",
        "accountType":"TOKENIZED_CHECKING"
      }
      """
    When the user submits a POST request to create the tokenized deposit
    Then the response status should be 200
    Then the response body should indicate "COMPLETED"

  Scenario: Failed creation of a tokenized deposit due to missing beneficiary for a new user
    Given a new user with shorthash "78C86D135089"
    Given the user wants to create a wallet and place tokens in it
    Given the following request body missing the beneficiary field:
      """
      {
        "tokenizedBalance":"20",
        "commercialDepositCurrency":"USD",
        "tokenizedDepositCurrency":"AEDT",
        "accountNumber":"9111",
        "accountType":"TOKENIZED_CHECKING"
      }
      """
    When the user submits a POST request to create the tokenized deposit
    Then the response status should be 400
    Then the response body should indicate "Failed to convert request body to class webserver.models.tokenized_deposit.CreateDepositRequest"

  Scenario: Failed creation of a tokenized deposit due to invalid tokenizedBalance for a new user
    Given a new user with shorthash "78C86D135089"
    Given the user wants to create a wallet and place tokens in it
    Given the following request body with invalid tokenizedBalance:
      """
      {
        "beneficiary":"CN=Alice, OU=Test Dept, O=R3, L=London, C=GB",
        "tokenizedBalance":"invalid",
        "commercialDepositCurrency":"USD",
        "tokenizedDepositCurrency":"AEDT",
        "accountNumber":"9113",
        "accountType":"TOKENIZED_CHECKING"
      }
      """
    When the user submits a POST request to create the tokenized deposit
    Then the response status should be 400
    Then the response body should indicate "Failed to convert request body to class webserver.models.tokenized_deposit.CreateDepositRequest"

  Scenario: Failed creation of a tokenized deposit due to unsupported account type for a new user
    Given a new user with shorthash "78C86D135089"
    Given the user wants to create a wallet and place tokens in it
    Given the following request body with unsupported account type:
      """
      {
        "beneficiary":"CN=Alice, OU=Test Dept, O=R3, L=London, C=GB",
        "tokenizedBalance":"20",
        "commercialDepositCurrency":"USD",
        "tokenizedDepositCurrency":"AEDT",
        "accountNumber":"131",
        "accountType":"UNSUPPORTED_TYPE"
      }
      """
    When the user submits a POST request to create the tokenized deposit
    Then the response status should be 400
    Then the response body should indicate "Unsupported account type"

  Scenario: Failed creation of a tokenized deposit due to invalid account number for a new user
    Given a new user with shorthash "78C86D135089"
    Given the user wants to create a wallet and place tokens in it
    Given the following request body with invalid account number:
      """
      {
        "beneficiary":"CN=Alice, OU=Test Dept, O=R3, L=London, C=GB",
        "tokenizedBalance":"20",
        "commercialDepositCurrency":"USD",
        "tokenizedDepositCurrency":"AEDT",
        "accountNumber":"invalid",
        "accountType":"TOKENIZED_CHECKING"
      }
      """
    When the user submits a POST request to create the tokenized deposit
    Then the response status should be 400
    Then the response body should indicate "Invalid account number"
