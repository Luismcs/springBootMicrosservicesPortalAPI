package com.exercise.manager.service;

import com.exercise.manager.dto.AddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {

    List<AddressDTO> getAllAddresses();

    AddressDTO getAddressById(Long id);

    Page<AddressDTO> searchByStreet(String street, Pageable pageable);

    Page<AddressDTO> searchByUserAccount_Id(Long userAccountId, Pageable pageable);

    AddressDTO createAddress(AddressDTO addressDto);

    AddressDTO updateAddress(AddressDTO addressDto);

    AddressDTO deleteAddress(Long id);

}
