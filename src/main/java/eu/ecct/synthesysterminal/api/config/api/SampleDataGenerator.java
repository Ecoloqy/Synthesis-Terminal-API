package eu.ecct.synthesysterminal.api.config.api;

import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.entity.account.Provider;
import eu.ecct.synthesysterminal.api.entity.account.UserRole;
import eu.ecct.synthesysterminal.api.entity.key.Key;
import eu.ecct.synthesysterminal.api.service.account.AccountService;
import eu.ecct.synthesysterminal.api.service.key.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Configuration
public class SampleDataGenerator {

    private final AccountService accountService;
    private final KeyService keyService;

    @Autowired
    public SampleDataGenerator(AccountService accountService, KeyService keyService) {
        this.accountService = accountService;
        this.keyService = keyService;
    }

    @PostConstruct
    public void init() {
        Account account = new Account("user", null, "img/guest_avatar.png", null, Provider.LOCAL, UserRole.USER, true, false, "password", 1234, null, null);
        accountService.addNewElement(account);

        Key key = new Key("Key used for connection with arduino", UUID.fromString("114283b1-794e-4eac-80e1-a1153d7c4fda"));
        keyService.addNewElement(key);
    }

}
