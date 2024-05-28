package org.formation.web;


import org.formation.model.AccountRepository;
import org.formation.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Flux<AccountWithOperations> getAll() {
        return accountService.fullLoads();
    }

    @GetMapping("/{id}")
    public Mono<AccountWithOperations> getById(@PathVariable Long id) {
        return accountService.fullLoad(id);
    }
}
