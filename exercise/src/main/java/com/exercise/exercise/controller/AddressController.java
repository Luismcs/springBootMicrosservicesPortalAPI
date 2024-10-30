package com.exercise.exercise.controller;

import com.exercise.exercise.dto.AddressDTO;
import com.exercise.exercise.service.impl.AddressServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressServiceImpl addressService;

    public AddressController(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Gets al the addresses registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address List successfully returned"),
    })
    @GetMapping
    public List<AddressDTO> getAll() {
        return addressService.getAll();
    }

    @Operation(summary = "Gets a address based on the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully returned"),
            @ApiResponse(responseCode = "404", description = "Address not found"),

    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable long id) {
        AddressDTO addressDto = addressService.getAddressById(id);
        return ResponseEntity.ok(addressDto);
    }

    @Operation(summary = "Gets a page of addresses searched by the address street")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address page successfully returned"),

    })
    @GetMapping("/search")
    public ResponseEntity<Page<AddressDTO>> searchAddresses(@RequestParam String street,
                                                            @ParameterObject
                                                            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ResponseEntity.ok(addressService.searchByStreet(street, pageable));
    }

    @Operation(summary = "Gets a page of addresses searched by user account Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address page successfully returned"),

    })
    @GetMapping("/search-addresses-by-user-account-id")
    public ResponseEntity<Page<AddressDTO>> searchAddressesByUserAccountId(@RequestParam long userAccountId,
                                                                           @ParameterObject
                                                                           @PageableDefault(size = 20) Pageable pageable
    ) {
        return ResponseEntity.ok(addressService.searchByUserAccount_Id(userAccountId, pageable));
    }

    @Operation(summary = "Creates an address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully created"),
            @ApiResponse(responseCode = "404", description = "User Account not found"),

    })
    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDto) {
        addressService.createAddress(addressDto);
        return ResponseEntity.ok(addressDto);
    }

    @Operation(summary = "Updates an address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully updated"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "404", description = "User Account not found"),

    })
    @PutMapping()
    public ResponseEntity<AddressDTO> updateAddress(@Valid @RequestBody AddressDTO addressDto) {
        AddressDTO updatedAddressDTO = addressService.updateAddress(addressDto);
        return ResponseEntity.ok(updatedAddressDTO);
    }

    @Operation(summary = "Delete an address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Address not found"),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

}
