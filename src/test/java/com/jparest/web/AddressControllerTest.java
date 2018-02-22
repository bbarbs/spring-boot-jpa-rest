package com.jparest.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jparest.mapper.AddressMapper;
import com.jparest.model.Address;
import com.jparest.model.dto.AddressDto;
import com.jparest.service.impl.AddressServiceImpl;
import com.jparest.service.impl.CustomerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AddressServiceImpl addressService;

    @Autowired
    AddressMapper addressMapper;

    @Test
    public void testShouldAddCustomerAddress() throws Exception {
        given(this.addressService.addCustomerAddress(anyLong(), any())).willReturn(new Address());
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(new AddressDto());
        this.mockMvc.perform(
                post("/v1/api/customers/1/addresses")
                        .accept(APPLICATION_JSON_VALUE)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(body))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
