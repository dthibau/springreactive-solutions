package org.formation.web;


import jakarta.validation.Valid;
import org.formation.model.Account;
import org.formation.model.AccountRepository;
import org.formation.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public AccountController(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public Flux<AccountWithOperations> getAll() {
        return accountService.fullLoads();
    }

    @GetMapping("/{id}")
    public Mono<AccountWithOperations> getById(@PathVariable Long id) {
        return accountService.fullLoad(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Account> save(@RequestBody @Valid Account account) {
        return accountService.save(account);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return accountRepository.deleteById(id);
    }
    @PutMapping
    public Mono<Account> update(@RequestBody @Valid Account account) {
     /*   return accountRepository.existsById(account.getId())
                .flatMap(exists -> {
                    if (exists) {
                        return accountRepository.save(account);
                    } else {
                        return Mono.error(new NotFoundException());
                    }
                });*/
        return accountRepository.findById(account.getId()).switchIfEmpty(Mono.error(new NotFoundException())).then(accountRepository.save(account));
    }
}
