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
    kjACANCCCCNJCNJCAJ
    // Getters và Setters
}
