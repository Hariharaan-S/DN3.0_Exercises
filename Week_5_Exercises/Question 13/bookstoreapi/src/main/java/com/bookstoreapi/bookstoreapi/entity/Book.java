package com.bookstoreapi.bookstoreapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookID;
    @NotNull(message = "ISBN should not be empty")
    private String ISBN;
    @NotNull(message = "Title should not be empty")
    private String bookName;
    @NotNull(message = "Author should be empty")
    private String author;
    @Min(value = 0, message = "Price should be positive")
    private Double bookPrice;

    @Version
    private Integer version;

}