package br.com.rodr1gotavares.item_catalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private BigDecimal unitValue;
    private int amount;
    private BigDecimal totalValue;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Item(String name, BigDecimal unitValue, int amount) {
        this.name = name;
        this.unitValue = unitValue;
        this.amount = amount;
    }
}
