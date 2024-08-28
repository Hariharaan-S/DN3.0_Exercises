package com.bookstoreapi.bookstoreapi.assemblers;

import com.bookstoreapi.bookstoreapi.dto.CustomerDTO;
import com.bookstoreapi.bookstoreapi.entity.Customer;
import com.bookstoreapi.bookstoreapi.controller.CustomerController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CustomerDTOAssembler extends RepresentationModelAssemblerSupport<Customer, CustomerDTO> {

    public CustomerDTOAssembler() {
        super(CustomerController.class, CustomerDTO.class);
    }

    @Override
    public CustomerDTO toModel(Customer entity) {
        CustomerDTO model = instantiateModel(entity);

        model.add(linkTo(methodOn(CustomerController.class).getCustomerByID(entity.getCustomerID())).withSelfRel());
        model.add(linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));

        model.setCustomerID(entity.getCustomerID());
        model.setCustomerName(entity.getCustomerName());
        model.setCustomerEmail(entity.getEmail());

        return model;
    }
}
