package MockControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Utility.CreateDepositRequest;

@RestController
@RequestMapping("/tokenized_deposit")
public class MockTokenizedDepositController {

    @PostMapping("/create/{shorthash}")
    public ResponseEntity<?> createTokenizedDeposit(@PathVariable String shorthash, @RequestBody CreateDepositRequest request) {
        if (request.getBeneficiary() == null) {
            return new ResponseEntity<>("Failed to convert request body to class webserver.models.tokenized_deposit.CreateDepositRequest", HttpStatus.BAD_REQUEST);
        }
        if (!isValidTokenizedBalance(request.getTokenizedBalance())) {
            return new ResponseEntity<>("Failed to convert request body to class webserver.models.tokenized_deposit.CreateDepositRequest", HttpStatus.BAD_REQUEST);
        }
        if (!isValidAccountType(request.getAccountType())) {
            return new ResponseEntity<>("Unsupported account type", HttpStatus.BAD_REQUEST);
        }
        if (!isValidAccountNumber(request.getAccountNumber())) {
            return new ResponseEntity<>("Invalid account number", HttpStatus.BAD_REQUEST);
        }
        // Handle the request and create the tokenized deposit
        return new ResponseEntity<>("COMPLETED", HttpStatus.OK);
    }

    private boolean isValidTokenizedBalance(String tokenizedBalance) {
        try {
            Integer.parseInt(tokenizedBalance);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidAccountType(String accountType) {
        return "TOKENIZED_CHECKING".equals(accountType) || "TOKENIZED_SAVINGS".equals(accountType);
    }

    private boolean isValidAccountNumber(String accountNumber) {
        try {
            Integer.parseInt(accountNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
