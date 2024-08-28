package com.bookstoreapi.bookstoreapi.assemblers;

import com.bookstoreapi.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.bookstoreapi.entity.Book;
import com.bookstoreapi.bookstoreapi.controller.BookController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BookDTOAssembler extends RepresentationModelAssemblerSupport<Book, BookDTO> {

    public BookDTOAssembler() {
        super(BookController.class, BookDTO.class);
    }

    @Override
    public BookDTO toModel(Book entity) {
        BookDTO model = instantiateModel(entity);

        model.add(linkTo(methodOn(BookController.class).getSingleBook(entity.getBookID())).withSelfRel());
        model.add(linkTo(methodOn(BookController.class).getAllBooksFromDatabase()).withRel("books"));

        model.setBookID(entity.getBookID());
        model.setBookName(entity.getBookName());
        model.setAuthor(entity.getAuthor());
        model.setPrice(entity.getBookPrice());

        return model;
    }
}
