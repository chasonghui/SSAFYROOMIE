package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.InterestDto;
import com.ssafy.vue.mapper.InterestMapper;

@Service
public class InterestServiceImpl implements InterestService {

	@Autowired
	private InterestMapper interestMapper;
	
	@Override
	public boolean addInterest(InterestDto interestDto) {
		return interestMapper.addInterest(interestDto);
	}

	@Override
	public int checkInterest(InterestDto interestDto) {
		return interestMapper.checkInterest(interestDto);
	}

	@Override
	public List<InterestDto> getInterestList(String userid) {
		return interestMapper.getInterestList(userid);
	}

	@Override
	public boolean deleteInterest(InterestDto interestDto) {
		return interestMapper.deleteInterest(interestDto);
	}

}