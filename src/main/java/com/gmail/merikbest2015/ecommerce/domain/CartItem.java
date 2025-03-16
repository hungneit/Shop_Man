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
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_option_id", nullable = false)
    private ProductOption productOption;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "item_total_price", nullable = false)
    private BigDecimal itemTotalPrice;
    public String getFormattedItemTotalPrice() {
        NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return currencyFormatter.format(itemTotalPrice);
    }

    public BigDecimal calculateTotalPrice() {
        return productOption.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
