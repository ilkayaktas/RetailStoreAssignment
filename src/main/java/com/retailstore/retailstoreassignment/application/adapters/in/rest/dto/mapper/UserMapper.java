package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.mapper;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.UserRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.UserResponseDto;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "password", ignore = true)
	UserResponseDto toUserRestDto(User user);

	User toUser(UserResponseDto userResponseDto);

	User toUser(UserRequestDto userRequesteDto);
}
