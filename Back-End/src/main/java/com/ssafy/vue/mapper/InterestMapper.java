package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.InterestDto;

@Mapper
public interface InterestMapper {
	boolean addInterest(InterestDto interestDto);
	int checkInterest(InterestDto interestDto);
	List<InterestDto> getInterestList(String userid);
	boolean deleteInterest(InterestDto interestDto);
}
