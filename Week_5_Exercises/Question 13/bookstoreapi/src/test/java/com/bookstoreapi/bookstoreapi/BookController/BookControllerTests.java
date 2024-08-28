package com.bookstoreapi.bookstoreapi.BookController;

import com.bookstoreapi.bookstoreapi.assemblers.BookDTOAssembler;
import com.bookstoreapi.bookstoreapi.controller.BookController;
import com.bookstoreapi.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.bookstoreapi.entity.Book;
import com.bookstoreapi.bookstoreapi.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookDTOAssembler bookDTOAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    public void setup() {
        book = new Book();
        book.setBookID(1);
        book.setBookName("Sample Book");
        book.setAuthor("John Doe");
        book.setBookPrice(29.99);

        bookDTO = new BookDTO();
        bookDTO.setBookID(1);
        bookDTO.setBookName("Sample Book");
        bookDTO.setAuthor("John Doe");
        bookDTO.setPrice(29.99);

        given(bookDTOAssembler.toModel(ArgumentMatchers.any(Book.class))).willReturn(bookDTO);
    }

    @Test
    public void BookController_CreateBook_ReturnCreated() throws Exception {
        given(bookService.convertToBookDto(bookService.saveBook(ArgumentMatchers.any(Book.class)))).willReturn(bookDTO);

        ResultActions response = mockMvc.perform(post("/add/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)));

        response.andExpect(status().isCreated());
    }
}
