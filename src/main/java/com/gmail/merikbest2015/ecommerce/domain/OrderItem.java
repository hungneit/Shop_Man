package com.gmail.merikbest2015.ecommerce.domain;

import com.gmail.merikbest2015.ecommerce.domain.product.Product;
import com.gmail.merikbest2015.ecommerce.domain.product.ProductOption;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_option_id", nullable = false)
    private ProductOption productOption;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;



    public String getFormattedTotalPrice() {
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(price.multiply(BigDecimal.valueOf(quantity)));
    }

}
