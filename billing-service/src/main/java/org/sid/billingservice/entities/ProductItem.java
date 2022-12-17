package org.sid.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.sid.billingservice.models.Product;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double price;
    private Long productId;
    @Transient
    private String productName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Bill bill;
    @Transient
    @JsonIgnore
    private Product product;

    public double getAmount(){
        return this.price * this.quantity;
    }
}
