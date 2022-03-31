package com.infinities.homework.service;

import com.infinities.homework.dao.BookRepository;
import com.infinities.homework.entity.Book;
import com.infinities.homework.model.BookDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public boolean createUpdateBook(BookDto bookDto) {

        Book book = null;

        if (bookDto != null) {
            book = bookRepository.saveAndFlush(Book.builder()
                    .name(bookDto.getName())
                    .author(bookDto.getAuthor())
                    .translator(bookDto.getTranslator())
                    .isbn(bookDto.getIsbn())
                    .publisher(bookDto.getPublisher())
                    .publicationDate(bookDto.getPublicationDate())
                    .price(bookDto.getPrice())
                    .build());
        }

        return Objects.nonNull(book);
    }

    public void deletebook(Long id) {
        if (id != null) {
            bookRepository.deleteById(id);
        }
    }

    public List<BookDto> findAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .author(book.getAuthor())
                        .translator(book.getTranslator())
                        .isbn(book.getIsbn())
                        .publisher(book.getPublisher())
                        .publicationDate(book.getPublicationDate())
                        .price(book.getPrice())
                    .build())
                .collect(Collectors.toList());
    }

    public boolean existById(Long id) {
        return bookRepository.findById(id).isPresent();
    }
}
