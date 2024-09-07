package com.fa.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    private String name;
    @OneToOne
    private Publisher publisher;
    @OneToOne
    private Student holder;


}
