package com.infinities.homework;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infinities.homework.controller.BookController;
import com.infinities.homework.model.BookDto;
import com.infinities.homework.service.BookService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomeworkApplication.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.bookController).build();
    }

    @Test
    public void whenCreateBook_shouldSuccess() throws Exception {
        when(this.bookService.createUpdateBook(
                new BookDto(null,
                        "name",
                        "auther",
                        "translator",
                        "isbn",
                        "publisher",
                        LocalDate.of(1993, 01, 24),
                        100)))
                .thenReturn(true);

        mockMvc.perform(
                post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(
                                new BookDto(null,
                                        "name",
                                        "auther",
                                        "translator",
                                        "isbn",
                                        "publisher",
                                        LocalDate.of(1993, 01, 24),
                                        100))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"));
    }

    @Test
    public void whenCreateBookWithId_shouldReturnBadRequest() throws Exception {
        when(this.bookService.createUpdateBook(
                new BookDto(null,
                        "name",
                        "auther",
                        "translator",
                        "isbn",
                        "publisher",
                        LocalDate.of(1993, 01, 24),
                        100)))
                .thenReturn(true);

        mockMvc.perform(
                post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(
                                new BookDto(1L,
                                        "name",
                                        "auther",
                                        "translator",
                                        "isbn",
                                        "publisher",
                                        LocalDate.of(1993, 01, 24),
                                        100))))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("id must not present."));
    }

    @Test
    public void whenUpdateBook_shouldSuccess() throws Exception {
        when(this.bookService.createUpdateBook(
                new BookDto(1L,
                        "name",
                        "auther",
                        "translator",
                        "isbn",
                        "publisher",
                        LocalDate.of(1993, 01, 24),
                        100)))
                .thenReturn(true);

        when(this.bookService.existById(1L)).thenReturn(true);

        mockMvc.perform(
                put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(
                                new BookDto(null,
                                        "name",
                                        "auther",
                                        "translator",
                                        "isbn",
                                        "publisher",
                                        LocalDate.of(1993, 01, 24),
                                        100))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"));
    }

    @Test
    public void whenUpdateBookWithIdInPayload_shouldReturnBadRequest() throws Exception {
        when(this.bookService.createUpdateBook(
                new BookDto(1L,
                        "name",
                        "auther",
                        "translator",
                        "isbn",
                        "publisher",
                        LocalDate.of(1993, 01, 24),
                        100)))
                .thenReturn(true);

        when(this.bookService.existById(1L)).thenReturn(true);

        mockMvc.perform(
                put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(
                                new BookDto(1L,
                                        "name",
                                        "auther",
                                        "translator",
                                        "isbn",
                                        "publisher",
                                        LocalDate.of(1993, 01, 24),
                                        100))))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("id must not present."));
    }

    @Test
    public void whenUpdateBookWithBookNotExist_shouldReturnMethodNotAllow() throws Exception {
        when(this.bookService.createUpdateBook(
                new BookDto(1L,
                        "name",
                        "auther",
                        "translator",
                        "isbn",
                        "publisher",
                        LocalDate.of(1993, 01, 24),
                        100)))
                .thenReturn(true);

        when(this.bookService.existById(1L)).thenReturn(false);

        mockMvc.perform(
                put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(
                                new BookDto(1L,
                                        "name",
                                        "auther",
                                        "translator",
                                        "isbn",
                                        "publisher",
                                        LocalDate.of(1993, 01, 24),
                                        100))))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("id must not present."));
    }

    @Test
    public void whenUpdateBookWithServiceFail_shouldFail() throws Exception {
        when(this.bookService.createUpdateBook(
                new BookDto(1L,
                        "name",
                        "auther",
                        "translator",
                        "isbn",
                        "publisher",
                        LocalDate.of(1993, 01, 24),
                        100)))
                .thenReturn(false);

        when(this.bookService.existById(1L)).thenReturn(true);

        mockMvc.perform(
                put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(
                                new BookDto(null,
                                        "name",
                                        "auther",
                                        "translator",
                                        "isbn",
                                        "publisher",
                                        LocalDate.of(1993, 01, 24),
                                        100))))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void whenDeletebook_shouldSuccess() throws Exception {

        when(this.bookService.existById(1L)).thenReturn(true);

        mockMvc.perform(
                delete("/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeletebookWithIdNotExist_shouldFail() throws Exception {

        when(this.bookService.existById(1L)).thenReturn(false);

        mockMvc.perform(
                delete("/books/1"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    public static String asJsonString(final Object obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
