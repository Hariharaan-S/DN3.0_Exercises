package com.bookstoreapi.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"customerID","customerName","email"})
public class CustomerDTO {
    private String customerName;
    private Integer customerID;
    private String customerEmail;
    @JsonGetter
    public String customerNameDisplay(){
        return this.getCustomerName();
    }
}
