package com.exercise.authentication.mapper;

import com.exercise.authentication.dto.UserCredentialsDTO;
import com.exercise.authentication.model.UserCredentials;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserCredentialsMapper {

    UserCredentials toEntity(UserCredentialsDTO dto);

    UserCredentialsDTO toDto(UserCredentials entity);

    //
    List<UserCredentialsDTO> toDtoList(List<UserCredentials> entityList);

}
