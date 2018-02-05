package com.jparest.repository;

import com.jparest.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Credentials findByUsername(String username);

    Credentials findByCustomer_Id(Long customerId);

    List<Credentials> findAllByCustomer_Id(Long customerId);
}
