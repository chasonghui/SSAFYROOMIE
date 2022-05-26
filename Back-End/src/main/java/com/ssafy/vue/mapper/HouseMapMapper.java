package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String map) throws SQLException;
	List<HouseInfoDto> getAptName(String aptName) throws SQLException;
	List<HouseInfoDto> getAptPrice(Map<String, String> map);
}
