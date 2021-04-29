package com.labs.luizalabschallenge.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping(value = "blah", method = RequestMethod.GET)
    public ResponseEntity<String> getResult() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}
