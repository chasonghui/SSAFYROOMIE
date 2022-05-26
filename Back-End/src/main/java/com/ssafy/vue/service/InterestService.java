package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.InterestDto;

public interface InterestService {
	
	boolean addInterest(InterestDto interestDto);
	int checkInterest(InterestDto interestDto);
	List<InterestDto> getInterestList(String userid);
	boolean deleteInterest(InterestDto interestDto);
}
