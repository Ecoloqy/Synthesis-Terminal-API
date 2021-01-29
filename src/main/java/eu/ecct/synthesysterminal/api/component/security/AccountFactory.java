package eu.ecct.synthesysterminal.api.component.security;

import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.entity.account.Provider;
import eu.ecct.synthesysterminal.api.entity.account.UserRole;
import eu.ecct.synthesysterminal.api.entity.address.Address;
import eu.ecct.synthesysterminal.security.security.register.RegisterDetails;
import org.springframework.stereotype.Component;

@Component
public final class AccountFactory {

    public Account createNewLocalAccount(RegisterDetails details) {
        return new Account(details.getUsername(),
                null,
                "img/guest_avatar.png",
                null,
                Provider.LOCAL,
                UserRole.USER,
                false,
                false,
                details.getPassword(),
                0,
                new Address(null, null, null, null),
                null);
    }

}
