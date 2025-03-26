package com.gmail.merikbest2015.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime createdAt;

    private String shortDescription;

    @Lob
    private String content; // Lưu nội dung HTML từ Summernote

    private String coverImageUrl; // URL ảnh bìa

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters và Setters
}

//package com.gmail.merikbest2015.ecommerce.domain;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.*;
//        import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@ToString(exclude = "content") // Loại bỏ content khỏi toString để tránh in dữ liệu lớn
//@Entity
//@Table(name = "blogs")
//public class Blog {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", updatable = false, nullable = false)
//    private Long id;
//
//    @NotBlank(message = "Title cannot be empty")
//    @Size(max = 255, message = "Title cannot exceed 255 characters")
//    @Column(name = "title", nullable = false)
//    private String title;
//
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @Size(max = 500, message = "Short description cannot exceed 500 characters")
//    @Column(name = "short_description")
//    private String shortDescription;
//
//    @Lob
//    @Column(name = "content")
//    private String content; // Nội dung HTML từ Summernote
//
//    @Size(max = 1000, message = "Cover image URL cannot exceed 1000 characters")
//    @Column(name = "cover_image_url")
//    private String coverImageUrl;
//
//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = LocalDateTime.now();
//    }
//}
