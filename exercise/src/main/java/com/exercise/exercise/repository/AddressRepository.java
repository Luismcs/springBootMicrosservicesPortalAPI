package com.exercise.exercise.repository;

import com.exercise.exercise.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Page<Address> findByStreetContainingIgnoreCase(String street, Pageable pageable);

    Page<Address> findByUserAccount_Id(long id, Pageable pageable);
}
