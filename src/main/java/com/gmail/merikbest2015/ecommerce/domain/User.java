package com.gmail.merikbest2015.ecommerce.domain;

import com.gmail.merikbest2015.ecommerce.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", initialValue = 4, allocationSize = 1)
    private Long id;


    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "active", nullable = false)
    private boolean active = true; // Người dùng mặc định là hoạt động

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "device")
    private String device;

    @Column(name = "address")
    private String address;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @Service
    public class UserService {
        private final UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public long countUsers() {
            return userRepository.count();
        }
    }

    @PrePersist
    protected void onCreate() {
        if (createDate == null) {
            createDate = LocalDateTime.now();
        }
        if (birthDate == null) {
            birthDate = LocalDate.of(2000, 1, 1); // Giá trị hợp lệ mặc định cho ngày sinh
        }
    }

    // Phương thức format để lấy ngày-tháng-năm khi cần
    public String getFormattedBirthDate() {
        return birthDate != null ? birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }


    // Phương thức để ban người dùng (ngưng hoạt động)
    public void banUser() {
        this.active = false;
    }

    // Phương thức để kích hoạt lại người dùng
    public void unbanUser() {
        this.active = true;
    }

    public void setCity(String city) {
    }

}
