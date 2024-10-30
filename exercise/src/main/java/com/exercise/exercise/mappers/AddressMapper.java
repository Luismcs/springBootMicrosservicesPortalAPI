package com.exercise.exercise.mappers;

import com.exercise.exercise.dto.AddressDTO;
import com.exercise.exercise.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserAccountMapper.class})
public interface AddressMapper {

    @Mapping(target = "userAccount.id", source = "userAccountId")
    Address toEntity(AddressDTO dto);

    @Mapping(target = "userAccountId", source = "userAccount.id")
    AddressDTO toDto(Address address);

    List<AddressDTO> toDtoList(List<Address> entityList);

}
