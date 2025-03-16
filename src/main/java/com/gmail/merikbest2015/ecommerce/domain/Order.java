package com.gmail.merikbest2015.ecommerce.domain;

import com.gmail.merikbest2015.ecommerce.enums.OrderStatus;
import com.gmail.merikbest2015.ecommerce.enums.PaymentMethod;
import com.gmail.merikbest2015.ecommerce.enums.PaymentStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", initialValue = 6, allocationSize = 1)
    private Long id;

    @Column(name = "order_code", nullable = false, unique = true, updatable = false)
    private String orderCode;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "date", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime date = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod = PaymentMethod.CASH; // Mặc định là COD

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID; // Mặc định là chưa thanh toán


    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Phương thức sinh mã đơn hàng
    private String generateOrderCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase(); // 8 ký tự độc nhất
    }
    private String formattedPrice;

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

}
