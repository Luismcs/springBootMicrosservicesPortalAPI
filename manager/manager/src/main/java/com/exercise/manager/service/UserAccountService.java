package com.exercise.manager.service;

import com.exercise.manager.dto.UserAccountDTO;

import java.util.List;

public interface UserAccountService {

    List<UserAccountDTO> getAllUserAccounts();

    UserAccountDTO getUserAccountById(Long id);

    UserAccountDTO createUserAccount(UserAccountDTO userAccountDto);

    UserAccountDTO updateUserAccount(UserAccountDTO userAccountDto);

    void deleteUserAccount(Long id);

}
