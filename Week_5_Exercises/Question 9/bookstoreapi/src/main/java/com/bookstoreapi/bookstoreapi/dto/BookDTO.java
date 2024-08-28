package com.bookstoreapi.bookstoreapi.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@JsonPropertyOrder({"bookName", "bookPrice", "author"})
public class BookDTO extends RepresentationModel<BookDTO> {
    private Integer bookID;
    private String bookName;
    private String author;
    private Double price;
    @JsonGetter
    public String bookNameDisplay(){
        return this.getBookName();
    }
}
