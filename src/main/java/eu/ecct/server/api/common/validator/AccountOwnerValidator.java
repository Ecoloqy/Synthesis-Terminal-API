package eu.ecct.server.api.common.validator;

import eu.ecct.server.api.account.AccountService;
import eu.ecct.server.api.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class AccountOwnerValidator<V> {

    private final AccountService accountService;

    @Autowired
    public AccountOwnerValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    public boolean isOwnerIdEqualUriId(V userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return false;
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        if (principal == null) return false;
        Long providerId = ((Integer) principal.getAttributes().get("id")).longValue();
        Optional<Account> accountOptional = accountService.getElementByProviderId(providerId);
        Account account = accountOptional.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return account.getId().equals(userId);
    }

}
