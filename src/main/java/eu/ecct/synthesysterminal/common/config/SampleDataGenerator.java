package eu.ecct.synthesysterminal.common.config;

import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.account.entity.Provider;
import eu.ecct.synthesysterminal.account.entity.UserRole;
import eu.ecct.synthesysterminal.key.entity.Key;
import eu.ecct.synthesysterminal.account.AccountService;
import eu.ecct.synthesysterminal.key.KeyService;
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
