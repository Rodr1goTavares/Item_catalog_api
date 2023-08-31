package br.com.rodr1gotavares.item_catalog.service.item;

import br.com.rodr1gotavares.item_catalog.entity.Item;

public interface ItemServices {
    void save(Item item);
    void update(Item item);
    void delete(Long id);
}
