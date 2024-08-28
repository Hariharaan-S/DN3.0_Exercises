package com.bookstoreapi.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@JsonPropertyOrder({"customerID","customerName","email"})
public class CustomerDTO extends RepresentationModel<CustomerDTO> {
    private String customerName;
    private Integer customerID;
    private String customerEmail;
    @JsonGetter
    public String customerNameDisplay(){
        return this.getCustomerName();
    }
}
