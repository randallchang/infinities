package com.infinities.homework;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.infinities.homework.dao.BookRepository;
import com.infinities.homework.entity.Book;
import com.infinities.homework.model.BookDto;
import com.infinities.homework.service.BookService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomeworkApplication.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void whenRepositorySuccess_createUpdateBookShouldSuccess() {

        BookDto bookDto = new BookDto(null,
                "name123",
                "auther",
                "translator",
                "isbn",
                "publisher",
                LocalDate.of(1993, 01, 24),
                100);

        when(bookRepository.saveAndFlush(
                new Book(null,
                        "name123",
                        "auther",
                        "translator",
                        "isbn",
                        "publisher",
                        LocalDate.of(1993, 01, 24),
                        100)))
                .thenReturn(
                        new Book(1L,
                                "name123",
                                "auther",
                                "translator",
                                "isbn",
                                "publisher",
                                LocalDate.of(1993, 01, 24),
                                100));

        assertTrue(bookService.createUpdateBook(bookDto));
    }

    @Test
    public void whenRepositoryFail_createUpdateBookShouldFail() {

        BookDto bookDto = new BookDto(null,
                "name123",
                "auther",
                "translator",
                "isbn",
                "publisher",
                LocalDate.of(1993, 01, 24),
                100);

        when(bookRepository.saveAndFlush(
                new Book(null,
                        "name123",
                        "auther",
                        "translator",
                        "isbn",
                        "publisher",
                        LocalDate.of(1993, 01, 24),
                        100)))
                .thenReturn(null);

        assertFalse(bookService.createUpdateBook(bookDto));
    }

    @Test
    public void findAllBooks_shouldSuccess() {

        when(bookRepository.findAll())
                .thenReturn(
                        List.of(new Book(
                                1L,
                                "name123",
                                "auther",
                                "translator",
                                "isbn",
                                "publisher",
                                LocalDate.of(1993, 01, 24),
                                100)));

        List<BookDto> bookList = bookService.findAllBooks();

        assertEquals(1, bookList.size());

        BookDto bookDto = bookList.get(0);

        assertEquals(1L, bookDto.getId());
        assertEquals("name123", bookDto.getName());
        assertEquals("auther", bookDto.getAuthor());
        assertEquals("translator", bookDto.getTranslator());
        assertEquals("isbn", bookDto.getIsbn());
        assertEquals("publisher", bookDto.getPublisher());
        assertEquals(LocalDate.of(1993, 01, 24), bookDto.getPublicationDate());
    }
}
