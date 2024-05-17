Feature: Token Transfer

  Scenario: Successful Token Transfer
    Given a user with transfer shorthash "71F02D3F9872"
    Given the token transfer request body:
      """
      {
        "symbol": "AEDT",
        "issuer": "SHA-256:3BC51062973C458D5A6F2D8D64A023246354AD7E064B1E4E009EC8A0699A3043",
        "amount": "60",
        "transferTo": "CN=Charlie, OU=Test Dept, O=R3, L=London, C=GB",
         "walletAddress": "GARW"
      }
      """
    When the user sends a POST request to transfer the token
    Then the transfer response status should be 200
    And the transfer response body should indicate "COMPLETED"

  Scenario: Invalid Parameter (Negative Testing)
    Given a user with transfer shorthash "78C86D135089"
    Given the token transfer request body with negative amount:
      """
      {
        "symbol": "AEDT",
        "issuer": "SHA-256:3BC51062973C458D5A6F2D8D64A023246354AD7E064B1E4E009EC8A0699A3043",
        "amount": "-10",
        "transferTo": "CN=Bob, OU=Test Dept, O=R3, L=London, C=GB",
        "walletAddress": "G..."
      }
      """
    When the user sends a POST request to transfer the token
    Then the transfer response status should be 400
    And the transfer response body should indicate "Amount is under 0"

  Scenario: Invalid Request Body (Missing fields)
    Given a user with transfer shorthash "78C86D135089"
    Given the token transfer request body with missing fields:
      """
      {
        "symbol": "AEDT",
        "issuer": "SHA-256:3BC51062973C458D5A6F2D8D64A023246354AD7E064B1E4E009EC8A0699A3043",
        "amount": "10"
      }
      """
    When the user sends a POST request to transfer the token
    Then the transfer response status should be 400
    And the transfer response body should indicate "Failed to convert request body to class webserver.models.token.TransferTokenRequest"

  Scenario: Insufficient Balance
    Given a user with transfer shorthash "71F02D3F9872"
    Given the token transfer request body:
      """
      {
        "symbol": "AEDT",
        "issuer": "SHA-256:3BC51062973C458D5A6F2D8D64A023246354AD7E064B1E4E009EC8A0699A3043",
        "amount": "10000000",
        "transferTo": "CN=Bob, OU=Test Dept, O=R3, L=London, C=GB",
        "walletAddress": "G..."
      }
      """
    When the user sends a POST request to transfer the token
    Then the transfer response status should be 400
    And the transfer response body should indicate "{type=FLOW_FAILED, message=No token found for AEDT}"

  Scenario: Invalid Recipient
    Given a user with transfer shorthash "78C86D135089"
    Given the token transfer request body with invalid recipient:
      """
      {
        "symbol": "AEDT",
        "issuer": "SHA-256:3BC51062973C458D5A6F2D8D64A023246354AD7E064B1E4E009EC8A0699A3043",
        "amount": "10",
        "transferTo": "InvalidRecipient",
        "walletAddress": "G..."
      }
      """
    When the user sends a POST request to transfer the token
    Then the transfer response status should be 400
    And the transfer response body should indicate "{type=FLOW_FAILED, message=improperly specified input name: InvalidRecipient}"

  Scenario: Negative Amount
    Given a user with transfer shorthash "78C86D135089"
    Given the token transfer request body with negative amount:
      """
      {
        "symbol": "AEDT",
        "issuer": "SHA-256:3BC51062973C458D5A6F2D8D64A023246354AD7E064B1E4E009EC8A0699A3043",
        "amount": "-10",
        "transferTo": "CN=Bob, OU=Test Dept, O=R3, L=London, C=GB",
        "walletAddress": "G..."
      }
      """
    When the user sends a POST request to transfer the token
    Then the transfer response status should be 400
    And the transfer response body should indicate "Negative amount"
