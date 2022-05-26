package com.ssafy.vue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.UserDto;
import com.ssafy.vue.service.UserService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/idcheck/{id}")
	public ResponseEntity<?> idCheck(@PathVariable String id) throws Exception {
		logger.debug("idCheck");
		return new ResponseEntity<Integer>(userService.idCheck(id), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> userInfo(@PathVariable String id) throws Exception {
		logger.debug("userInfo");
		return new ResponseEntity<UserDto>(userService.getUser(id), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto uDto) throws Exception {
		logger.debug("Login"+uDto.getId());
		UserDto userDto = userService.login(uDto);
		
		if (userDto != null) {
			logger.debug(userDto.toString());
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		}
		logger.debug("fail");
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<?> userRegister(@RequestBody UserDto uDto) throws Exception {
		logger.debug("memberDto info : {}", uDto);
		if(userService.registerUser(uDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	private ResponseEntity<String> deleteUser(@PathVariable String id) throws Exception {
		logger.debug("deleteUser - 호출");
		if(userService.deleteUser(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	private ResponseEntity<String> updateUser(@RequestBody UserDto uDto) throws Exception {
		logger.debug("updateUser - 호출");
		logger.debug(uDto.toString());
		if(userService.updateUser(uDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);		
	}
	
	@PostMapping("/findpw")
	private ResponseEntity<?> findPw(@RequestBody UserDto uDto) throws Exception {
		logger.debug("findPw - 호출");
		String pw = userService.findPw(uDto);
		if (pw != null) {
			return new ResponseEntity<String>(pw, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);	
	}
}
