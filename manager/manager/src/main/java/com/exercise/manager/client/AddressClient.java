package com.exercise.manager.client;

import com.exercise.manager.dto.AddressDTO;
import com.exercise.manager.exception.errorHandler.AddressClientErrorDecoder;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "userAccountInformationClient", url = "${address.service.url}", configuration = AddressClientErrorDecoder.class)
public interface AddressClient {

    @GetMapping
    List<AddressDTO> getAllAddresses();

    @GetMapping("/{id}")
    AddressDTO getAddressById(@PathVariable Long id);

    @GetMapping("/search")
    Page<AddressDTO> searchAddresses(@RequestParam String street,
                                     @ParameterObject
                                     @PageableDefault(size = 20) Pageable pageable);

    @GetMapping("/search-addresses-by-user-account-id")
    Page<AddressDTO> searchAddressesByUserAccountId(@RequestParam Long userAccountId,
                                                    @ParameterObject
                                                    @PageableDefault(size = 20) Pageable pageable);

    @PostMapping
    AddressDTO createAddress(@Valid @RequestBody AddressDTO addressDto);

    @PutMapping
    AddressDTO updateAddress(@Valid @RequestBody AddressDTO addressDto);

    @DeleteMapping("/{id}")
    AddressDTO deleteAddress(@PathVariable Long id);

}
