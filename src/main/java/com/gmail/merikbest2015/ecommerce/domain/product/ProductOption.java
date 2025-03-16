package com.gmail.merikbest2015.ecommerce.domain.product;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Data
@Entity
@Table(name = "product_option")
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionImageUrl;

    private String optionName;
    private BigDecimal price;

    public String getFormattedPrice() {
        NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return currencyFormatter.format(price);
    }

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

