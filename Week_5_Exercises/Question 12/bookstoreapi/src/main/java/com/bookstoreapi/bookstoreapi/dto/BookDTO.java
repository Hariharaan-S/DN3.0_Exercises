package com.bookstoreapi.bookstoreapi.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@JsonPropertyOrder({"bookID", "bookName", "author", "price"})
@JsonRootName(value = "book")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement()
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
