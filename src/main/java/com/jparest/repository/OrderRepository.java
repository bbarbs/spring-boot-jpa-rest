package com.jparest.repository;

import com.jparest.model.Orders;
import com.jparest.model.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByCustomer_Id(Long customerId);

    List<Orders> findAllByStatus(OrderStatusEnum status);
}
