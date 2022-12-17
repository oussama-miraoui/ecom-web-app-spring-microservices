package sid.org.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import sid.org.inventoryservice.entities.Product;
import sid.org.inventoryservice.repositories.ProductRepository;

import java.util.stream.Stream;

@SpringBootApplication
@CrossOrigin("*")
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository,
                                        RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Product.class);
        return args -> {
          Stream.of("Laptop Dell D30", "iPhone 14 Pro", "Huawei Mate 10 Pro").forEach(name->{
              Product product = new Product();
              product.setName(name);
              product.setPrice(Math.floor(1200+Math.random()*10000));
              product.setQuantity((int)Math.floor(Math.random()+10*3));

              productRepository.save(product);
          });
          productRepository.findAll().forEach(System.out::println);
        };
    }
}
