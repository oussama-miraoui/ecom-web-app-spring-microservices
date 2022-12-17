package org.sid.billingservice.web;

import lombok.AllArgsConstructor;
import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductRestClient;
import org.sid.billingservice.models.Customer;
import org.sid.billingservice.models.Product;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BillRestController{
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill = billRepository.findById(id).orElseThrow(()-> new RuntimeException("Bill not found"));
        Customer customer = customerRestClient.getCustomerById(bill.getCustomerId());

        bill.getProductItems().forEach(item->{
            Product product = productRestClient.getProductById(item.getProductId());
            //item.setProduct(product);
            item.setProductName(product.getName());
        });

        bill.setCustomer(customer);

        return bill;
    }
}
