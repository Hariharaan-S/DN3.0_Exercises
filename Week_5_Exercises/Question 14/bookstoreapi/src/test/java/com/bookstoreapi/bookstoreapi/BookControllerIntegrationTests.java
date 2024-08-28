package com.bookstoreapi.bookstoreapi;

import com.bookstoreapi.bookstoreapi.entity.Book;
import com.bookstoreapi.bookstoreapi.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = BookstoreapiApplication.class)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll();
    }

    @Test
    @WithMockUser
    public void givenBookObject_whenCreateBook_thenReturnCreatedBook() throws Exception {
        Book book = new Book();
        book.setBookID(1);
        book.setBookName("Sample Book");
        book.setAuthor("John Doe");
        book.setBookPrice(29.99);
        book.setISBN("www-333-eee-333");

        ResultActions response = mockMvc.perform(post("/add/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));

            response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookID").exists())
                .andExpect(jsonPath("$.bookName").value("Sample Book"))
                .andExpect(jsonPath("$.author").value("John Doe"));
    }

    @Test
    @WithMockUser
    public void givenBookId_whenGetBookById_thenReturnBookObject() throws Exception {
        Book book = new Book();
        book.setBookID(2);
        book.setBookName("Sample Book");
        book.setAuthor("John Doe");
        book.setBookPrice(29.99);
        bookRepository.save(book);
        ResultActions response = mockMvc.perform(get("/bookstoreapi/book/{id}", book.getBookID()));


        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.bookID").value(book.getBookID()))
                .andExpect(jsonPath("$.bookName").value(book.getBookName()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()));
    }
}
