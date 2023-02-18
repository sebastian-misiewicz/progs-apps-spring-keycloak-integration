package com.progsapps.springkeycloakintegration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @GetMapping(produces = "application/json")
    public List<String> getProducts() {
        return Arrays.asList("A", "B", "C");
    }


}
