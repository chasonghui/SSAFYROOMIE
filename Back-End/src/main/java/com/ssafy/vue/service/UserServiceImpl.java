package com.ssafy.vue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.UserDto;
import com.ssafy.vue.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean registerUser(UserDto uDto) throws Exception {
		return userMapper.registerUser(uDto);
	}

	@Override
	public UserDto login(UserDto uDto) throws Exception {
		return userMapper.login(uDto);
	}

	@Override
	public UserDto getUser(String userId) throws Exception {
		return userMapper.getUser(userId);
	}

	@Override
	public boolean updateUser(UserDto uDto) throws Exception {
		return userMapper.updateUser(uDto);
	}

	@Override
	public boolean deleteUser(String userId) throws Exception {
		return userMapper.deleteUser(userId);
	}

	@Override
	public String findPw(UserDto uDto) throws Exception {
		return userMapper.findPw(uDto);
	}

	@Override
	public int idCheck(String id) throws Exception {
		return userMapper.idCheck(id);
	}
}