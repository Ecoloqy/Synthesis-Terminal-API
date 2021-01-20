package eu.ecct.synthesysterminal.security.service;

import eu.ecct.synthesysterminal.entity.Account;
import eu.ecct.synthesysterminal.repository.AccountRepository;
import eu.ecct.synthesysterminal.security.model.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
