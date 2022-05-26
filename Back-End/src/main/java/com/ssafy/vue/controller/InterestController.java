package com.ssafy.vue.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.InterestDto;
import com.ssafy.vue.service.InterestService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/interest")
public class InterestController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private InterestService interestService;
	
	@PostMapping("/check")
	private ResponseEntity<?> checkInterest(@RequestBody InterestDto idto){
		logger.debug("checkInterest");
		return new ResponseEntity<Integer>(interestService.checkInterest(idto), HttpStatus.OK);
	}
	
	@GetMapping("{userid}")
	private ResponseEntity<?> getInterestList(@PathVariable String userid){
		logger.debug("getInterestList");
		return new ResponseEntity<List<InterestDto>>(interestService.getInterestList(userid), HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<?> addInterest(@RequestBody InterestDto idto){
		logger.debug("addInterest");
		if(interestService.addInterest(idto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);		
	}
	
	@DeleteMapping
	private ResponseEntity<?> deleteInterest(@RequestBody InterestDto idto){
		logger.debug("deleteInterest");
		if(interestService.deleteInterest(idto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);		
	}
	
}
