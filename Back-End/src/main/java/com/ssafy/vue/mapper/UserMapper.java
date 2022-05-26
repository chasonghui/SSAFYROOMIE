package com.ssafy.vue.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.UserDto;


@Mapper
public interface UserMapper {
	UserDto login(UserDto uDto) throws Exception;
	boolean registerUser(UserDto uDto) throws Exception;
	UserDto getUser(String userId) throws Exception;
	boolean updateUser(UserDto uDto) throws Exception;
	boolean deleteUser(String userId) throws Exception;
	String findPw(UserDto uDto) throws Exception;
	int idCheck(String id) throws Exception;
}
