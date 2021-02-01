package eu.ecct.server.security;

import eu.ecct.server.api.account.AccountService;
import eu.ecct.server.api.account.entity.Account;
import eu.ecct.server.api.account.entity.Provider;
import eu.ecct.server.api.account.entity.UserRole;
import eu.ecct.server.api.address.entity.Address;
import eu.ecct.server.api.address.entity.Country;
import eu.ecct.server.api.device.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class SessionAuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final AccountService accountService;

    @Autowired
    public SessionAuthenticationSuccessListener(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        OAuth2User principal = (OAuth2User) event.getAuthentication().getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        Long providerId = ((Integer) attributes.get("id")).longValue();
        if (!accountService.isExistsByProviderId(providerId)) {
            String username = (String) attributes.get("login");
            String email = (String) attributes.get("email");
            String avatarUrl = (String) attributes.get("avatar_url");
            Provider provider = Provider.GITHUB;
            UserRole userRole = UserRole.USER;
            Address address = new Address(Country.POLAND, "", "", "");
            List<Device> devices = new LinkedList<>();
            Account account = new Account(username, email, avatarUrl, providerId, provider, userRole, false, address, devices);
            accountService.addNewElement(account);
        }
    }

}
