package com.infinities.homework.controller;

import com.infinities.homework.model.BookDto;
import com.infinities.homework.model.RestResult;
import com.infinities.homework.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController()
public class BookController {

    @Autowired
    private BookService bookService;

    private static final String SECCUSS_MSG = "success";

    @PostMapping("/books")
    public ResponseEntity<RestResult> createBook(@RequestBody @Validated BookDto bookDto) {

        if (bookDto.getId() != null) {
            return ResponseEntity.badRequest().body(new RestResult("id must not present."));
        }

        if (bookService.createUpdateBook(bookDto)) {
            return ResponseEntity.ok(new RestResult(SECCUSS_MSG));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<RestResult> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {

        if (bookDto.getId() != null) {
            return ResponseEntity.badRequest().body(new RestResult("id must not present."));
        }

        if (!bookService.existById(id)) {
            return  ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).build();
        }

        bookDto.setId(id);

        if (bookService.createUpdateBook(bookDto)) {
            return ResponseEntity.ok(new RestResult(SECCUSS_MSG));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<RestResult> deletebook(@PathVariable Long id) {

        if (!bookService.existById(id)) {
            return  ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).build();
        }

        bookService.deletebook(id);

        return ResponseEntity.ok(new RestResult(SECCUSS_MSG));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }
}
