package org.formation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Operation {
    @Id
    private Long id;
    private LocalDateTime dateOperation = LocalDateTime.now();
    private String description;
    private Double amount;

    private Long accountId;

}
