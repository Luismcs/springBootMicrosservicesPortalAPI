package com.exercise.exercise.service;

import com.exercise.exercise.dto.AddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {

    List<AddressDTO> getAll();

    AddressDTO getAddressById(Long id);

    AddressDTO createAddress(AddressDTO addressDto);

    AddressDTO updateAddress(AddressDTO addressDto);

    void deleteAddress(Long id);

    Page<AddressDTO> searchByStreet(String street, Pageable pageable);
}
