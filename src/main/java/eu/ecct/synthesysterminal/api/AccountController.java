package eu.ecct.synthesysterminal.api;

import eu.ecct.synthesysterminal.entity.Account;
import eu.ecct.synthesysterminal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountRepository;

    @Autowired
    public AccountController(AccountService accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public Iterable<Account> getAllAccounts() {
        return accountRepository.getAllAccount();
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable("id") UUID id) {
        return accountRepository.getAccountById(id);
    }

    @PostMapping
    public Account postAccount(@RequestBody Account account) {
        return accountRepository.saveAccount(account);
    }

    @PutMapping
    public Account putAccount(@RequestBody Account account) {
        return accountRepository.saveAccount(account);
    }

}
