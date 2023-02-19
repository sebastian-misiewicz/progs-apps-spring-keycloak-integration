package com.progsapps.springkeycloakintegration.controller;

import com.progsapps.springkeycloakintegration.model.Product;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("product")
public class ProductController {

    @GetMapping(produces = "application/json")
    public List<Product> getProducts() {
        return Arrays.asList(new Product(1, "First"), new Product(2, "Second"));
    }


}
