package br.com.rodr1gotavares.item_catalog.service;

import br.com.rodr1gotavares.item_catalog.api.dto.ItemDTO;
import br.com.rodr1gotavares.item_catalog.entity.Item;
import br.com.rodr1gotavares.item_catalog.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private List<ItemDTO> toDTOList(List<Item> itemList) {
        return itemList.stream()
                .map(ItemDTO::new)
                .collect(Collectors.toList());
    }

    private Item calcItemPrice(Item item) {
        item.setTotalValue(item.getUnitValue().multiply(BigDecimal.valueOf(item.getAmount())));
        return item;
    }

    public void create(Item item) {
        this.itemRepository.save(calcItemPrice(item));
    }

    public List<ItemDTO> read() {
        return toDTOList(this.itemRepository.findAll());
    }

    public List<ItemDTO> readWithPagination(int page, int size) {
        Page<Item> itemPage = this.itemRepository.findAll(PageRequest.of(page, size));
        List<Item> itemList = itemPage.stream().toList();
        return toDTOList(itemList);
    }

    public List<ItemDTO> readByOwnerName(String ownerName) {
        return toDTOList(this.itemRepository.findByOwnerName(ownerName));
    }

    public Optional<ItemDTO> readById(Long id) {
        Optional<Item> searchedProduct = this.itemRepository.findById(id);
        return searchedProduct.map(ItemDTO::new);
    }

    public void update(Item item) {
        this.itemRepository.saveAndFlush(calcItemPrice(item));
    }

    public void delete(Long id) {
        this.itemRepository.deleteById(id);
    }

    public void deleteAll() {
        this.itemRepository.deleteAll();
    }
}
