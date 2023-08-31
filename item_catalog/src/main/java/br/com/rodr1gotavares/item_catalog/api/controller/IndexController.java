package br.com.rodr1gotavares.item_catalog.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public ResponseEntity<String> get() {
        return new ResponseEntity<>("fds", HttpStatus.OK);
    }
}
