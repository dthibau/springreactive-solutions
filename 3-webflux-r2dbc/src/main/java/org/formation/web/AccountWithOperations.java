package org.formation.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.formation.model.Account;
import org.formation.model.Operation;
import reactor.core.publisher.Flux;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountWithOperations {
    private Account account;
    private List<Operation> operations;
}
