package com.fa.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String publisher;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    private Book(Long id, String title, String publisher, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.thumbnailUrl = thumbnailUrl;
    }
}
