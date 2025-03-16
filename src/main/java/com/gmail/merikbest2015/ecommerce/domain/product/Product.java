package com.gmail.merikbest2015.ecommerce.domain.product;

import com.gmail.merikbest2015.ecommerce.domain.SubCategory;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productTitle;
    private int quantity;
    private int sold;
    private boolean popular;

    private int displayOrder;

    @Column(length = 500)
    private String shortDescription;

    @Lob
    private String detailedDescription;

    @Lob
    private String faqs; // FAQs lưu dưới dạng văn bản

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOption> options = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    private transient String formattedPrice;

    // Get the starting price based on the options
    public BigDecimal getStartingPrice() {
        return options.isEmpty() ? BigDecimal.ZERO : options.get(0).getPrice();
    }

    // Format the price with a period as the thousand separator
//    public String getFormattedPrice() {
//        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
//        symbols.setGroupingSeparator('.');
//        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
//        formattedPrice = decimalFormat.format(getStartingPrice());
//        return formattedPrice;
//    }
    public String getFormattedStartingPrice() {
        BigDecimal price = getStartingPrice();
        NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return currencyFormatter.format(price);
    }

    // Method to set a custom formatted price if necessary
    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    // Default getPrice to get the starting price directly
    public BigDecimal getPrice() {
        return getStartingPrice();
    }

    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 4.9;
        }
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }

}
