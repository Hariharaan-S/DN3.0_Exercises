package com.bookstoreapi.bookstoreapi.CustomerController;

import com.bookstoreapi.bookstoreapi.assemblers.CustomerDTOAssembler;
import com.bookstoreapi.bookstoreapi.controller.BookController;
import com.bookstoreapi.bookstoreapi.controller.CustomerController;
import com.bookstoreapi.bookstoreapi.dto.CustomerDTO;
import com.bookstoreapi.bookstoreapi.entity.Customer;
import com.bookstoreapi.bookstoreapi.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerDTOAssembler customerDTOAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        customer.setCustomerID(1);
        customer.setCustomerName("Sample Name");
        customer.setEmail("sampleemail");
        customer.setPhoneNumber("90909092999");
        customer.setPassword("hahahahaha");

        customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(1);
        customerDTO.setCustomerName("Sample Name");
        customerDTO.setCustomerEmail("sampleemail");

        given(customerDTOAssembler.toModel(ArgumentMatchers.any(Customer.class))).willReturn(customerDTO);
    }

    @Test
    public void BookController_CreateBook_ReturnCreated() throws Exception {
        given(customerService.convertToCustomerDto(customerService.createNewCustomer(ArgumentMatchers.any(Customer.class)))).willReturn(customerDTO);

        ResultActions response = mockMvc.perform(post("/create/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)));

        response.andExpect(status().isCreated());
    }
}
