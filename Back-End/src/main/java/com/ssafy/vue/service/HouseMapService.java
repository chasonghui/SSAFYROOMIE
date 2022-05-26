package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptName(String aptName) throws SQLException;
	List<HouseInfoDto> getAptPrice(Map<String, String> map) throws Exception;
}
