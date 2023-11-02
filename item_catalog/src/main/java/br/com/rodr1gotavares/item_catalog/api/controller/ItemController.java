package br.com.rodr1gotavares.item_catalog.api.controller;

import br.com.rodr1gotavares.item_catalog.api.dto.item.ItemRequestDTO;
import br.com.rodr1gotavares.item_catalog.api.dto.item.ItemResponseDTO;
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
    public ResponseEntity<List<ItemResponseDTO>> get(Authentication authentication) {
        UserDetails owner = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok().body(itemService.readByOwnerName(owner.getUsername()));
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<List<ItemResponseDTO>> getWithPagination(@PathVariable int page) {
        return ResponseEntity.ok().body(this.itemService.readWithPagination(page, 10));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getById(@PathVariable Long id, Authentication authentication) {
        UserDetails owner = (UserDetails) authentication.getPrincipal();
        Optional<ItemResponseDTO> searchedItem = itemService.readById(id);
        if (searchedItem.isEmpty() || !Objects.equals(searchedItem.get().ownerName(), owner.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(searchedItem.get());
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody ItemRequestDTO itemDTO, Authentication authentication) {
        UserDetails authenticatedUser = (UserDetails) authentication.getPrincipal();
        Item recivedItem = itemDTO.toItemObject();
        recivedItem.setOwnerName(authenticatedUser.getUsername());
        this.itemService.create(recivedItem);
        return ResponseEntity.ok().body("Item created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> put(@PathVariable Long id, @RequestBody ItemRequestDTO itemDTO, Authentication auth) {
        UserDetails authenticatedUser = (UserDetails) auth.getPrincipal();
        boolean isValidUpdate = this.itemService.readById(id).isEmpty() || !Objects.equals(
                this.itemService.readById(id).get().ownerName(), authenticatedUser.getUsername()
        );
        if (!isValidUpdate) return ResponseEntity.notFound().build();
        this.itemService.update(itemDTO.toItemObject());
        return ResponseEntity.ok().body(this.itemService.readById(id).get());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.itemService.delete(id);
    }
}
