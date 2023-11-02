package br.com.rodr1gotavares.item_catalog.api.dto.item;

import br.com.rodr1gotavares.item_catalog.entity.Item;

import java.math.BigDecimal;

public record ItemRequestDTO(String name, double price, int amount) {
    public Item getItemObject() {
        return new Item(this.name, BigDecimal.valueOf(this.price), this.amount);
    }
}
