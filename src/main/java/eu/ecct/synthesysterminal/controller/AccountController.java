package eu.ecct.synthesysterminal.controller;

import eu.ecct.synthesysterminal.entity.Account;
import eu.ecct.synthesysterminal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Iterable<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{uuid}")
    public Account getAccount(@PathVariable("uuid") UUID uuid) {
        return accountService.getAccount(uuid);
    }

    @PostMapping
    public Account postAccount(@RequestBody Account account) {
        return accountService.postAccount(account);
    }

    @PutMapping
    public Account putAccount(@RequestBody Account account) {
        return accountService.putAccount(account);
    }

}
