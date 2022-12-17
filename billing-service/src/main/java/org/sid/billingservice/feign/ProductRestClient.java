package org.sid.billingservice.feign;

import org.sid.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient("INVENTORY-SERVICE")
public interface ProductRestClient {
    @GetMapping(path="/products/{id}")
    Product getProductById(@PathVariable("id") Long id);

    @GetMapping(path="/products")
    PagedModel<Product> pageProducts();
}
