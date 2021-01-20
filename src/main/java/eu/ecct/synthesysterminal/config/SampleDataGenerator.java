package eu.ecct.synthesysterminal.config;

import eu.ecct.synthesysterminal.entity.Account;
import eu.ecct.synthesysterminal.entity.details.Provider;
import eu.ecct.synthesysterminal.entity.details.UserRole;
import eu.ecct.synthesysterminal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SampleDataGenerator {

    private final AccountService accountService;

    @Autowired
    public SampleDataGenerator(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void init() {
        Account account = new Account("user", null, "img/guest_avatar.png", null, Provider.LOCAL, UserRole.USER, true, "$2a$10$8iQ3Kk1mfwqb4.LiDKAzv.mV.J8qKsLIQx3SXlQD5vX4oXZ4XX5nO", 1234, null, null);
        accountService.saveAccount(account);
    }

}
