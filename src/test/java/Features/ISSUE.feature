Feature: Token Issuance

  Scenario: Successful Token Issuance
    Given a user with token issuance shorthash "78C86D135089"
    And the user has created a tokenized deposit
    Given the token issuance request body:
      """
      {
         "owner": "CN=Bob, OU=Test Dept, O=R3, L=London, C=GB",
         "symbol": "AEDT",
         "amount": "1000",
         "name": "United Arab Emirates Dirham",
         "walletAddress": "GARW",
         "accountNumber": "123",
         "tokenizedDepositBeneficiary": "CN=Bob, OU=Test Dept, O=R3, L=London, C=GB"
      }
      """
    When the user sends a POST request to issue the token
    Then the token issuance response status should be 200
    And the token issuance response body should indicate "COMPLETED"


  Scenario: Invalid Request Body (Missing fields)
    Given a user with token issuance shorthash "78C86D135089"
    And the user has created a tokenized deposit
    Given the token issuance request body with missing fields:
      """
      {
         "owner": "CN=Bob, OU=Test Dept, O=R3, L=London, C=GB",
         "symbol": "AEDT",
         "amount": "1000"
      }
      """
    When the user sends a POST request to issue the token
    Then the token issuance response status should be 400
    And the token issuance response body should indicate "Failed to convert request body to class webserver.models.token.IssueRequest"

  Scenario: Excessive Amount
    Given a user with token issuance shorthash "78C86D135089"
    And the user has created a tokenized deposit
    Given the token issuance request body with an excessive amount:
      """
      {
         "owner": "CN=Bob, OU=Test Dept, O=R3, L=London, C=GB",
         "symbol": "AEDT",
         "amount": "1000000000",
         "name": "United Arab Emirates Dirham",
         "walletAddress": "GARW",
         "accountNumber": "123",
         "tokenizedDepositBeneficiary": "CN=Bob, OU=Test Dept, O=R3, L=London, C=GB"
      }
      """
    When the user sends a POST request to issue the token
    Then the token issuance response status should be 400
    And the token issuance response body should indicate "{type=FLOW_FAILED, message=Insufficient Tokenized Balance}"

