package com.ssafy.vue.service;

import com.ssafy.vue.dto.UserDto;


public interface UserService {
	UserDto login(UserDto uDto) throws Exception;
	boolean registerUser(UserDto uDto) throws Exception;
	UserDto getUser(String userId) throws Exception;
	boolean updateUser(UserDto uDto) throws Exception;
	boolean deleteUser(String userId) throws Exception;
	String findPw(UserDto uDto) throws Exception;
	int idCheck(String id) throws Exception;
}
