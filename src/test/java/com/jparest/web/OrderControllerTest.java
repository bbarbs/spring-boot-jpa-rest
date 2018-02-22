package com.jparest.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jparest.mapper.ItemMapper;
import com.jparest.mapper.OrderMapper;
import com.jparest.model.Items;
import com.jparest.model.Orders;
import com.jparest.model.dto.OrderDto;
import com.jparest.model.enums.OrderStatusEnum;
import com.jparest.model.request.OrderRequest;
import com.jparest.service.impl.ItemServiceImpl;
import com.jparest.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.BDDMockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ItemMapper itemMapper;

    @MockBean
    OrderServiceImpl orderService;

    @MockBean
    ItemServiceImpl itemService;

    @Test
    public void testShouldAddOrder() throws Exception {
        OrderRequest requestWrapper = new OrderRequest();
        requestWrapper.setItemIds(new ArrayList<>());
        requestWrapper.setOrder(new OrderDto());
        when(this.itemService.getItemById(anyLong())).thenReturn(new Items());
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(requestWrapper);
        this.mockMvc.perform(
                post("/v1/api/customers/1/orders")
                        .accept(APPLICATION_JSON_VALUE)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(body)
        )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testShouldGetOrdersByCustomerId() throws Exception {
        when(this.orderService.getOrdersByCustomerId(anyLong())).thenReturn(Arrays.asList(new Orders()));
        this.mockMvc.perform(
                get("/v1/api/customers/1/orders")
                        .contentType(APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testShouldGetOrdersByStatus() throws Exception {
        when(this.orderService.getOrdersByStatus(any())).thenReturn(Arrays.asList(new Orders()));
        this.mockMvc.perform(
                get("/v1/api/orders")
                        .contentType(APPLICATION_JSON_VALUE)
                        .param("status", String.valueOf(OrderStatusEnum.NEW))
        )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
