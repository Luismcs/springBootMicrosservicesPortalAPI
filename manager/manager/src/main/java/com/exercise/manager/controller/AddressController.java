package com.exercise.manager.controller;

import com.exercise.manager.dto.AddressDTO;
import com.exercise.manager.service.impl.AddressServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@SecurityRequirement(name = "bearerAuth")
public class AddressController {

    AddressServiceImpl addressService;

    public AddressController(AddressServiceImpl apiManagerService) {
        this.addressService = apiManagerService;
    }

    @Operation(summary = "Gets al the addresses registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address List successfully returned"),
    })
    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addressDtoList = addressService.getAllAddresses();
        return ResponseEntity.ok(addressDtoList);
    }

    //Colocar service dentro do .ok(addressService.getAddressById(id))
    @Operation(summary = "Gets a address based on the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully returned"),
            @ApiResponse(responseCode = "404", description = "Address not found"),

    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        AddressDTO addressDto = addressService.getAddressById(id);
        return ResponseEntity.ok(addressDto);
    }

    @Operation(summary = "Gets a page of addresses searched by the address street")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address page successfully returned"),

    })
    @GetMapping("/search")
    public ResponseEntity<Page<AddressDTO>> search(@RequestParam String street,
                                                   @ParameterObject
                                                   @PageableDefault(size = 20) Pageable pageable) {
        Page<AddressDTO> addressDtoPage = addressService.searchByStreet(street, pageable);
        return ResponseEntity.ok(addressDtoPage);
    }

    @Operation(summary = "Gets a page of addresses searched by user account Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address page successfully returned"),

    })
    @GetMapping("/search-addresses-by-user-account-id")
    public ResponseEntity<Page<AddressDTO>> searchAddressesByUserAccountId(@RequestParam Long userAccountId,
                                                                           @ParameterObject
                                                                           @PageableDefault(size = 20) Pageable pageable) {
        Page<AddressDTO> addressDtoPage = addressService.searchByUserAccount_Id(userAccountId, pageable);
        return ResponseEntity.ok(addressDtoPage);
    }

    @Operation(summary = "Creates an address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully created"),
            @ApiResponse(responseCode = "404", description = "User Account not found"),

    })
    @PostMapping()
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDto) {
        AddressDTO addressDtoCreated = addressService.createAddress(addressDto);
        return ResponseEntity.ok(addressDtoCreated);
    }

    @Operation(summary = "Updates an address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully updated"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "404", description = "User Account not found"),

    })
    @PutMapping()
    public ResponseEntity<AddressDTO> updateAddress(@Valid @RequestBody AddressDTO addressDto) {
        AddressDTO addressDtoUpdated = addressService.updateAddress(addressDto);
        return ResponseEntity.ok(addressDtoUpdated);
    }

    @Operation(summary = "Deletes an address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Address not found"),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AddressDTO> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }

}
