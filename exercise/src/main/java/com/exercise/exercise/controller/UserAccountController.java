package com.exercise.exercise.controller;

import com.exercise.exercise.dto.UserAccountDTO;
import com.exercise.exercise.service.impl.UserAccountServiceImpl;
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
@RequestMapping("/user-accounts")
public class UserAccountController {

    private final UserAccountServiceImpl userAccountService;

    public UserAccountController(UserAccountServiceImpl userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Operation(summary = "Gets all the user accounts registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address List successfully returned"),
    })
    @GetMapping
    public List<UserAccountDTO> getAll() {
        return userAccountService.getAll();
    }

    @Operation(summary = "Gets all the user accounts registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully returned"),
            @ApiResponse(responseCode = "404", description = "Address not found"),

    })
    @GetMapping("/{id}")
    public ResponseEntity<UserAccountDTO> getById(@PathVariable long id) {
        UserAccountDTO UserAccountDto = userAccountService.getUserAccountById(id);
        return ResponseEntity.ok(UserAccountDto);
    }

    @Operation(summary = "Gets a page of user accounts searched user account usernames containing a string")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address page successfully returned"),

    })
    @GetMapping("/search")
    public ResponseEntity<Page<UserAccountDTO>> searchUsers(@RequestParam String name,
                                                            @ParameterObject
                                                            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ResponseEntity.ok(userAccountService.searchByName(name, pageable));
    }

    @Operation(summary = "Creates an user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully created"),

    })
    @PostMapping
    public ResponseEntity<UserAccountDTO> createUserAccount(@Valid @RequestBody UserAccountDTO userAccountDto) {
        userAccountService.createUserAccount(userAccountDto);
        return ResponseEntity.ok(userAccountDto);
    }

    @Operation(summary = "Updates an user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Account successfully updated"),
            @ApiResponse(responseCode = "404", description = "User Account not found"),

    })
    @PutMapping()
    public ResponseEntity<UserAccountDTO> updateUserAccount(@Valid @RequestBody UserAccountDTO userAccountDto) {
        UserAccountDTO updatedUserAccount = userAccountService.updateUserAccount(userAccountDto);
        return ResponseEntity.ok(updatedUserAccount);
    }

    @Operation(summary = "Deletes an user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Account successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User Account not found"),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long id) {
        userAccountService.deleteUserAccount(id);
        return ResponseEntity.noContent().build();
    }

}
