package com.ssafy.vue.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.dto.Board;

public interface BoardService {
	public List<Board> retrieveBoard();
	public Board detailBoard(int articleno);
	public boolean writeBoard(Board board);
	public boolean updateBoard(Board board);
	public boolean deleteBoard(int articleno);
	public List<Board> getCategory(String category);
	public List<Board> searchBoard(Map<String, String> map);
	public List<Board> searchBoardAll(String searchKeyword);
}
