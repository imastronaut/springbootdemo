package com.stacksimplify.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	//User to users DTO
	
	@Mapping(target = "emailAddress", source = "email")
	UserMsDto userToUserMsDto(User user);
	
	
	
	//List<User> to List<UserMSDTO>
	
	List<UserMsDto> usersToUsersMsDto(List<User> users);
	
	
	
	
	
	
}
