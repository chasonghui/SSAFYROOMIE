package com.ssafy.vue.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Board;
@Mapper
public interface BoardMapper {
	public List<Board> selectBoard();
	public Board selectBoardByNo(int articleno);
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(int articleno);
	public List<Board> getCategory(String category);
	public List<Board> searchBoard(Map<String, String> map);
	public List<Board> searchBoardAll(String searchKeyword);
}