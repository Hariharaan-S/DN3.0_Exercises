package com.bookstoreapi.bookstoreapi.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
@Data
@JsonPropertyOrder({"bookName", "bookPrice", "author"})
public class BookDTO {
    private String bookName;
    private String author;
    private Double price;
    @JsonGetter
    public String bookNameDisplay(){
        return this.getBookName();
    }
}
