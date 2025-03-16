package com.gmail.merikbest2015.ecommerce.domain.product;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private int rating;
    private String comment;
    private LocalDate reviewDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    public void prePersist() {
        this.reviewDate = LocalDate.now(); // Gán ngày hiện tại cho reviewDate
    }
}

