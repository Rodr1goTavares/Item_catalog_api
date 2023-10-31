package br.com.rodr1gotavares.item_catalog.api.controller;

import br.com.rodr1gotavares.item_catalog.api.dto.ItemDTO;
import br.com.rodr1gotavares.item_catalog.entity.Item;
import br.com.rodr1gotavares.item_catalog.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public ResponseEntity<List<ItemDTO>> get(Authentication authentication) {
        UserDetails owner = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok().body(itemService.readByOwnerName(owner.getUsername()));
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<List<ItemDTO>> getWithPagination(@PathVariable int page) {
        return ResponseEntity.ok().body(this.itemService.readWithPagination(page, 10));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getById(@PathVariable Long id, Authentication authentication) {
        UserDetails owner = (UserDetails) authentication.getPrincipal();
        Optional<ItemDTO> searchedItem = itemService.readById(id);
        if (searchedItem.isEmpty() || !Objects.equals(searchedItem.get().getOwnerName(), owner.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(searchedItem.get());
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody ItemDTO itemDTO, Authentication authentication) {
        UserDetails authenticatedUser = (UserDetails) authentication.getPrincipal();
        Item recivedItem = itemDTO.toItemObject();
        recivedItem.setOwnerName(authenticatedUser.getUsername());
        this.itemService.create(recivedItem);
        return ResponseEntity.ok().body("Item created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> put(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        if (this.itemService.readById(id).isEmpty() || !Objects.equals(id, itemDTO.getId())) return ResponseEntity.notFound().build();
        this.itemService.update(itemDTO.toItemObject());
        return ResponseEntity.ok().body(this.itemService.readById(id).get());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.itemService.delete(id);
    }
}
