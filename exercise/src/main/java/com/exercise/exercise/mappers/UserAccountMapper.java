package com.exercise.exercise.mappers;

import com.exercise.exercise.dto.UserAccountDTO;
import com.exercise.exercise.model.UserAccount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserAccountMapper {

    UserAccount toEntity(UserAccountDTO dto);

    UserAccountDTO toDto(UserAccount userAccount);

    List<UserAccountDTO> toDtoList(List<UserAccount> entityList);
}
