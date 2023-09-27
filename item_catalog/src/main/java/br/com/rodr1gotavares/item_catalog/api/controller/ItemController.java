package br.com.rodr1gotavares.item_catalog.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("")
@RequestMapping("/item")
public class ItemController {

    @GetMapping
    ResponseEntity<String> get() {
        return ResponseEntity.ok("Private route.");
    }
}
