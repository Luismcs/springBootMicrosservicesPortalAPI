package com.exercise.manager.service.impl;

import com.exercise.manager.client.UserAccountClient;
import com.exercise.manager.dto.UserAccountDTO;
import com.exercise.manager.service.UserAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountClient userAccountClient;

    public UserAccountServiceImpl(UserAccountClient userAccountClient) {
        this.userAccountClient = userAccountClient;
    }

    @Override
    public List<UserAccountDTO> getAllUserAccounts() {

        return userAccountClient.getAllUserAccounts();

    }

    @Override
    public UserAccountDTO getUserAccountById(Long id) {
        return userAccountClient.getUserAccountById(id);

    }

    public Page<UserAccountDTO> searchByName(String name, Pageable pageable) {
        return userAccountClient.searchByName(name, pageable);
    }

    @Override
    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDto) {
        return userAccountClient.createUserAccount(userAccountDto);

    }

    @Override
    public UserAccountDTO updateUserAccount(UserAccountDTO userAccountDto) {
        return userAccountClient.updateUserAccount(userAccountDto);

    }

    @Override
    public void deleteUserAccount(Long id) {
        userAccountClient.deleteUserAccount(id);
    }

}
