package org.formation.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String id;
    private String owner;
    private Double amount;

    public User(String owner, Double amount) {
        this.owner = owner;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", owner=" + owner + ", amount=" + amount + "]";
    }


}
