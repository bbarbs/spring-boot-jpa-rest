package com.jparest.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jparest.mapper.CustomerMapper;
import com.jparest.model.Customer;
import com.jparest.model.dto.CustomerDto;
import com.jparest.service.impl.CustomerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CustomerMapper customerMapper;

    @MockBean
    CustomerServiceImpl customerService;

    @Test
    public void testShouldAddCustomer() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(new CustomerDto());
        this.mockMvc.perform(
                post("/v1/api/customers")
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(body)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testShouldGetCustomerById() throws Exception {
        when(this.customerService.getCustomerById(anyLong())).thenReturn(new Customer());
        this.mockMvc.perform(
                get("/v1/api/customers/1")
                        .contentType(APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testShouldDeleteCustomer() throws Exception {
        this.mockMvc.perform(
                delete("/v1/api/customers/1")
                        .contentType(APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testShouldUpdateCustomer() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(new CustomerDto());
        when(this.customerService.updateCustomer(1L, new Customer())).thenReturn(new Customer());
        this.mockMvc.perform(
                put("/v1/api/customers/1")
                        .accept(APPLICATION_JSON_VALUE)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(body)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
