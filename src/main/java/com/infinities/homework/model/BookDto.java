package com.infinities.homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;

    @NotBlank(message = "name is required.")
    private String name;

    @NotBlank(message = "author is required.")
    private String author;

    @NotBlank(message = "translator is required.")
    private String translator;

    @NotBlank(message = "isbn is required.")
    private String isbn;

    @NotBlank(message = "publisher is required.")
    private String publisher;

    @NotNull(message = "publicationDate is required.")
    private LocalDate publicationDate;

    @NotNull(message = "price is required.")
    private Integer price;
}
