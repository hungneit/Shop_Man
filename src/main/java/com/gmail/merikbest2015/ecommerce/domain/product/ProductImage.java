package com.gmail.merikbest2015.ecommerce.domain.product;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(String imageUrl, Product product) {
        this.imageUrl = imageUrl;
        this.product = product;
    }
    public ProductImage() {
    }

    // Constructor khác (nếu có)
    public ProductImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

