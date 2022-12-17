package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.stream.Stream;

@SpringBootApplication
@CrossOrigin("*")
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                        RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            Stream.of("Oussama", "Ahmed","Mehdi").forEach(name->{
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");

                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(System.out::println);
        };
    }
}
