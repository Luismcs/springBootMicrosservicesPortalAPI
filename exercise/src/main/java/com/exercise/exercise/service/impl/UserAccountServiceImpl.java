package com.exercise.exercise.service.impl;

import com.exercise.exercise.dto.UserAccountDTO;
import com.exercise.exercise.exception.CustomException;
import com.exercise.exercise.exception.ErrorMessage;
import com.exercise.exercise.mappers.UserAccountMapper;
import com.exercise.exercise.model.UserAccount;
import com.exercise.exercise.repository.UserAccountRepository;
import com.exercise.exercise.service.UserAccountService;

import static com.exercise.exercise.util.LoggerPrinter.printLog; //static

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, UserAccountMapper userAccountMapper) {
        this.userAccountRepository = userAccountRepository;
        this.userAccountMapper = userAccountMapper;
    }

    public List<UserAccountDTO> getAll() {
        return userAccountMapper.toDtoList(userAccountRepository.findAll());
    }

    public UserAccountDTO getUserAccountById(Long id) {
        UserAccount userAccount = userAccountRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ACCOUNT_NOT_FOUND));

        return userAccountMapper.toDto(userAccount);
    }

    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDto) {
        UserAccount userAccount = userAccountMapper.toEntity(userAccountDto);
        setAddressesToUserAccount(userAccount);
        userAccountRepository.save(userAccount);
        printLog("UserAccount created with id: " + userAccount.getId());
        return userAccountMapper.toDto(userAccount);
    }

    private void setAddressesToUserAccount(UserAccount userAccount) {
        if (userAccount.getAddresses() != null) {
            userAccount.getAddresses().forEach(address -> address.setUserAccount(userAccount));
        }
    }

    public UserAccountDTO updateUserAccount(UserAccountDTO userAccountDto) {

        UserAccount userAccount = userAccountRepository.findById(userAccountDto.getId()).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ACCOUNT_NOT_FOUND));

        UserAccount updatedUserAccount = userAccountMapper.toEntity(userAccountDto);
        updatedUserAccount.setAddresses(userAccount.getAddresses());

        printLog("UserAccount updated with id: " + userAccount.getId());
        return userAccountMapper.toDto(userAccountRepository.save(updatedUserAccount));
    }

    public void deleteUserAccount(Long id) {
        UserAccount userAccount = userAccountRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ACCOUNT_NOT_FOUND));

        printLog("UserAccount deleted with id: " + userAccount.getId());
        userAccountRepository.delete(userAccount); //delete
    }

    public Page<UserAccountDTO> searchByName(String name, Pageable pageable) {

        Page<UserAccount> userAccounts = userAccountRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name, pageable);

        return new PageImpl<>(userAccountMapper.toDtoList(userAccounts.getContent()), userAccounts.getPageable(),
                userAccounts.getTotalElements());
    }


}
