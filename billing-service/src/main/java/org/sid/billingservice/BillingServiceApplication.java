package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductRestClient;
import org.sid.billingservice.models.Customer;
import org.sid.billingservice.models.Product;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
@CrossOrigin("*")
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository,
                                        ProductItemRepository productItemRepository,
                                        CustomerRestClient customerRestClient,
                                        ProductRestClient productRestClient){
        return args -> {
            Customer customer = customerRestClient.getCustomerById(1L);
            Customer customer2 = customerRestClient.getCustomerById(2L);
            Customer customer3 = customerRestClient.getCustomerById(3L);

            Bill  bill = billRepository.save(new Bill(null, new Date(),null, customer.getId(), null));
            Bill  bill2 = billRepository.save(new Bill(null, new Date(),null, customer2.getId(), null));
            Bill  bill3 = billRepository.save(new Bill(null, new Date(),null, customer3.getId(), null));

            PagedModel<Product> productsPagedModel= productRestClient.pageProducts();

            productsPagedModel.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setProductId(product.getId());
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1+new Random().nextInt(40));
                productItem.setBill(bill);
                productItemRepository.save(productItem);
            });


            PagedModel<Product> productsPagedModel2= productRestClient.pageProducts();

            productsPagedModel2.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setProductId(product.getId());
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1+new Random().nextInt(40));
                productItem.setBill(bill2);
                productItemRepository.save(productItem);
            });

            PagedModel<Product> productsPagedModel3= productRestClient.pageProducts();

            productsPagedModel3.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setProductId(product.getId());
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1+new Random().nextInt(40));
                productItem.setBill(bill3);
                productItemRepository.save(productItem);
            });

/*
            Customer customer1 = customerRestClient.getCustomerById(2L);
            Bill  bill1 = billRepository.save(new Bill(null, new Date(),null, customer1.getId(), null));

            PagedModel<Product> productsPagedModel1= productRestClient.pageProducts();

            productsPagedModel1.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setProductID(product.getId());
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1+new Random().nextInt(40));
                productItem.setBill(bill1);

                productItemRepository.save(productItem);
            });
*/


        };
    }

}
