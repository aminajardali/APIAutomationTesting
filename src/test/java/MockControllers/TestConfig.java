package MockControllers;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public MockTokenizedDepositController mockTokenizedDepositController() {
        return new MockTokenizedDepositController();
    }
}
