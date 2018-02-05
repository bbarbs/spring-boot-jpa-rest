package com.jparest.repository;

import com.jparest.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCustomer_Id(Long customerId);

    List<Address> findAllByCustomer_Id(Long customerId);
}
