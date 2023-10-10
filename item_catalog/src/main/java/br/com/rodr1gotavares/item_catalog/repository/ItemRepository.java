package br.com.rodr1gotavares.item_catalog.repository;

import br.com.rodr1gotavares.item_catalog.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByOwnerName(String ownerName);
}
