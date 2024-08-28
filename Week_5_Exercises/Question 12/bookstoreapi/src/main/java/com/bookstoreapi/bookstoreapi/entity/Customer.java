package com.bookstoreapi.bookstoreapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "customers")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerID;
    @NotNull(message = "Customer name should not be empty")
    private String customerName;
    @NotNull(message = "Customer email should not be empty")
    private String email;
    @NotNull(message = "Customer phone number should not be empty")
    private String phoneNumber;
    @NotNull(message = "Password should not be empty")
    @Size(min = 8, max = 16, message = "Password should contain 8 to 16 characters")
    private String password;

    @Version
    private Integer version;
}
