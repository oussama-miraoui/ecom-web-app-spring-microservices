package org.sid.billingservice.web;

import lombok.AllArgsConstructor;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class ProductItemRestController {
    private ProductItemRepository productItemRepository;

    @GetMapping("/productItemsList")
    public Collection<ProductItem> getProductItems(){
        return productItemRepository.findAll();
    }
}
