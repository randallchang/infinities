package com.infinities.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(length = 32, nullable = false)
    private String author;

    @Column(length = 32, nullable = false)
    private String translator;

    @Column(length = 17, nullable = false)
    private String isbn;

    @Column(length = 32, nullable = false)
    private String publisher;

    @Column(nullable = false)
    private LocalDate publicationDate;

    @Column(nullable = false)
    private Integer price;
}
