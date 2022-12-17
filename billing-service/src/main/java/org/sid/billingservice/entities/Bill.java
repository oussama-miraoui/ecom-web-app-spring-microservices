package org.sid.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.billingservice.models.Customer;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
    private Long customerId;
    @Transient
    private Customer customer;

    public double getTotal(){
        double total=0;
        for (ProductItem item:productItems) {
            total+=item.getAmount();
        }
        return total;
    }
}
