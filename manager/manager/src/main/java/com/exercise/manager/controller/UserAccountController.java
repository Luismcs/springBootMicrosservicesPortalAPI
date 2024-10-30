package com.exercise.manager.controller;

import com.exercise.manager.dto.UserAccountDTO;
import com.exercise.manager.service.impl.UserAccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-accounts")
@SecurityRequirement(name = "bearerAuth")
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
    public ResponseEntity<List<UserAccountDTO>> getAllUserAccounts() {
        List<UserAccountDTO> userAccountDtoList = userAccountService.getAllUserAccounts();
        return ResponseEntity.ok(userAccountDtoList);
    }

    @Operation(summary = "Gets all the user accounts registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully returned"),
            @ApiResponse(responseCode = "404", description = "Address not found"),

    })
    @GetMapping("/{id}")
    public ResponseEntity<UserAccountDTO> getUserAccountById(@PathVariable Long id) {
        UserAccountDTO userAccountDto = userAccountService.getUserAccountById(id);
        return ResponseEntity.ok(userAccountDto);
    }

    @Operation(summary = "Gets a page of user accounts searched user account usernames containing a string")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address page successfully returned"),

    })
    @GetMapping("/search")
    public ResponseEntity<Page<UserAccountDTO>> searchByName(@RequestParam String name,
                                                             @ParameterObject
                                                             @PageableDefault(size = 20) Pageable pageable
    ) {
        Page<UserAccountDTO> userAccountDtoPage = userAccountService.searchByName(name, pageable);
        return ResponseEntity.ok(userAccountDtoPage);
    }

    @Operation(summary = "Creates an user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully created"),

    })
    @PostMapping
    public ResponseEntity<UserAccountDTO> createUserAccount(@RequestBody UserAccountDTO userAccountDto) {
        UserAccountDTO userAccountDtoCreated = userAccountService.createUserAccount(userAccountDto);
        return ResponseEntity.ok(userAccountDtoCreated);
    }

    @Operation(summary = "Updates an user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Account successfully updated"),
            @ApiResponse(responseCode = "404", description = "User Account not found"),

    })
    @PutMapping
    public ResponseEntity<UserAccountDTO> updateUserAccount(@RequestBody UserAccountDTO userAccountDto) {
        UserAccountDTO userAccountUpdated = userAccountService.updateUserAccount(userAccountDto);
        return ResponseEntity.ok(userAccountUpdated);
    }

    @Operation(summary = "Deletes an user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Account successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User Account not found"),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long id) {
        userAccountService.deleteUserAccount(id);
        return ResponseEntity.ok().build();
    }

}
