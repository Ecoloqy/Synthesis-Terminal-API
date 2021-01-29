package eu.ecct.synthesysterminal.security;

import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.repository.account.AccountRepository;
import eu.ecct.synthesysterminal.security.security.account.AccountDetails;
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

    private final AccountRepository accountRepository;

    @Autowired
    public AccountDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        return new AccountDetails(account.orElseThrow(
                () -> new UsernameNotFoundException("Username not found in database."))
        );
    }

    public Optional<Account> getActiveAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            UUID accountId = ((AccountDetails) authentication.getPrincipal()).getUserId();
            return accountRepository.getByIdAndArchived(accountId, false);
        }
        return Optional.empty();
    }

}
