package com.exercise.exercise.service.impl;

import com.exercise.exercise.dto.AddressDTO;
import com.exercise.exercise.exception.CustomException;
import com.exercise.exercise.exception.ErrorMessage;
import com.exercise.exercise.mappers.AddressMapper;
import com.exercise.exercise.model.Address;
import com.exercise.exercise.model.UserAccount;
import com.exercise.exercise.repository.AddressRepository;
import com.exercise.exercise.repository.UserAccountRepository;
import com.exercise.exercise.service.AddressService;

import static com.exercise.exercise.util.LoggerPrinter.printLog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserAccountRepository userAccountRepository;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper,
                              UserAccountRepository userAccountRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.userAccountRepository = userAccountRepository;
    }

    public List<AddressDTO> getAll() {
        return addressMapper.toDtoList(addressRepository.findAll());
    }

    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ADDRESS_NOT_FOUND));
        return addressMapper.toDto(address);
    }

    public AddressDTO createAddress(AddressDTO addressDto) {
        Address address = addressMapper.toEntity(addressDto);

        UserAccount userAccount = userAccountRepository.findById(address.getUserAccount().getId()).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ACCOUNT_NOT_FOUND));
        address.setUserAccount(userAccount);

        printLog("Address created with id: " + address.getId());
        return addressMapper.toDto(addressRepository.save(address));
    }

    public AddressDTO updateAddress(AddressDTO addressDto) {
        Address address = addressRepository.findById(addressDto.getId()).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ADDRESS_NOT_FOUND));

        UserAccount userAccount = userAccountRepository.findById(addressDto.getUserAccountId()).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ACCOUNT_NOT_FOUND));

        Address updatedAddress = addressMapper.toEntity(addressDto);
        updatedAddress.setUserAccount(userAccount);

        printLog("Address updated with id: " + address.getId());
        return addressMapper.toDto(addressRepository.save(updatedAddress));

    }

    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ADDRESS_NOT_FOUND));
        printLog("Address deleted with id: " + address.getId());
        addressRepository.deleteById(id);
    }

    public Page<AddressDTO> searchByStreet(String street, Pageable pageable) {
        Page<Address> addresses = addressRepository.findByStreetContainingIgnoreCase(street, pageable);

        return new PageImpl<>(addressMapper.toDtoList(addresses.getContent()), addresses.getPageable(),
                addresses.getTotalElements());
    }

    public Page<AddressDTO> searchByUserAccount_Id(long id, Pageable pageable) {
        Page<Address> addresses = addressRepository.findByUserAccount_Id(id, pageable);

        return new PageImpl<>(addressMapper.toDtoList(addresses.getContent()), addresses.getPageable(),
                addresses.getTotalElements());
    }


}
