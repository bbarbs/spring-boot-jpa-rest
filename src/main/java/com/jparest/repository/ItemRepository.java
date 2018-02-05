package com.jparest.repository;

import com.jparest.model.Items;
import com.jparest.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long> {
    List<Items> findAllByOrders(Orders orders);
}
