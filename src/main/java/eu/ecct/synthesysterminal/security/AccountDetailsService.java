package eu.ecct.synthesysterminal.security;

import eu.ecct.synthesysterminal.account.AccountService;
import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.security.entity.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountDetailsService implements UserDetailsService {

    private final AccountService accountService;

    @Autowired
    public AccountDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountService.getElementByUsername(username);
        return new AccountDetails(account.orElseThrow(
                () -> new UsernameNotFoundException("Username not found in database."))
        );
    }

    public Optional<Account> getActiveAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            UUID accountId = ((AccountDetails) authentication.getPrincipal()).getUserId();
            return accountService.getElementById(accountId);
        }
        return Optional.empty();
    }

}
