package br.com.rodr1gotavares.item_catalog.api.dto;

import br.com.rodr1gotavares.item_catalog.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String ownerName;
    private String name;
    private BigDecimal unitValue;
    private int amount;
    private BigDecimal totalValue;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public ItemDTO(String name, double unitValue, int amount) {
        this.name = name;
        this.unitValue = BigDecimal.valueOf(unitValue);
        this.amount = amount;
    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.ownerName = item.getOwnerName();
        this.name = item.getName();
        this.unitValue = item.getUnitValue();
        this.amount = item.getAmount();
        this.totalValue = item.getTotalValue();
        this.createdAt = item.getCreatedAt();
        this.updatedAt = item.getUpdatedAt();
    }

    public Item toItemObject() {
        return new Item(this.name, this.unitValue, this.amount);
    }
}
