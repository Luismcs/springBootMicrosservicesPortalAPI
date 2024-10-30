package com.exercise.manager.service.impl;

import com.exercise.manager.client.AddressClient;
import com.exercise.manager.dto.AddressDTO;
import com.exercise.manager.service.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressClient addressClient;

    public AddressServiceImpl(AddressClient addressClient) {
        this.addressClient = addressClient;
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        return addressClient.getAllAddresses();
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        return addressClient.getAddressById(id);
    }

    @Override
    public Page<AddressDTO> searchByStreet(String street, Pageable pageable) {
        return addressClient.searchAddresses(street, pageable);
    }

    @Override
    public Page<AddressDTO> searchByUserAccount_Id(Long userAccountId, Pageable pageable) {
        return addressClient.searchAddressesByUserAccountId(userAccountId, pageable);
    }

    @Override
    public AddressDTO createAddress(AddressDTO addressDto) {
        return addressClient.createAddress(addressDto);
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDto) {
        return addressClient.updateAddress(addressDto);
    }

    @Override
    public AddressDTO deleteAddress(Long id) {
        return addressClient.deleteAddress(id);
    }


}
