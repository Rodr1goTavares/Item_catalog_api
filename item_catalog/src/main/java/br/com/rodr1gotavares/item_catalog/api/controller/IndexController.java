package br.com.rodr1gotavares.item_catalog.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/")
public class IndexController {

    private String[] test = new String[10];
    private int index = 0;

    @GetMapping
    public ResponseEntity<String> get() {
        return new ResponseEntity<>(Arrays.toString(test), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody String word) {
        if (index == test.length) return ResponseEntity.badRequest().body("Failed to add item: The array is full\n" +  Arrays.toString(test));
        test[index] = word;
        index++;
        return ResponseEntity.ok().body(Arrays.toString(test));
    }
}
