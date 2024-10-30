package com.exercise.exercise.service;

import com.exercise.exercise.dto.UserAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserAccountService {

    List<UserAccountDTO> getAll();

    UserAccountDTO getUserAccountById(Long id);

    UserAccountDTO createUserAccount(UserAccountDTO addressDto);

    UserAccountDTO updateUserAccount(UserAccountDTO addressDto);

    void deleteUserAccount(Long id);

    Page<UserAccountDTO> searchByName(String name, Pageable pageable);

}
