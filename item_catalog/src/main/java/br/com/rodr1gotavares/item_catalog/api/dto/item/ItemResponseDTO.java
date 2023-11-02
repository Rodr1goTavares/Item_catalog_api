package br.com.rodr1gotavares.item_catalog.api.dto.item;

import br.com.rodr1gotavares.item_catalog.entity.Item;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ItemResponseDTO(
        Long id,
        String ownerName,
        String name,
        BigDecimal unitValue,
        int amount,
        BigDecimal totalValue,
        LocalDate createdAt,
        LocalDate updatedAt
        ) {
    public ItemResponseDTO(Item item) {
        this(
                item.getId(),
                item.getOwnerName(),
                item.getName(),
                item.getUnitValue(),
                item.getAmount(),
                item.getTotalValue(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}
