package com.ssafy.vue.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
    @Autowired
	private BoardMapper boardMapper;

    @Override
	public List<Board> retrieveBoard() {
		return boardMapper.selectBoard();
	}
    
  	@Override
	public boolean writeBoard(Board board) {
		return boardMapper.insertBoard(board) == 1;
	}

	@Override
	public Board detailBoard(int articleno) {
		return boardMapper.selectBoardByNo(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(Board board) {
		return boardMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}

	@Override
	public List<Board> getCategory(String category) {
		// TODO Auto-generated method stub
		return boardMapper.getCategory(category);
	}

	@Override
	public List<Board> searchBoard(Map<String, String> map) {
		// TODO Auto-generated method stub
		return boardMapper.searchBoard(map);
	}

	@Override
	public List<Board> searchBoardAll(String searchKeyword) {
		// TODO Auto-generated method stub
		return boardMapper.searchBoardAll(searchKeyword);
	}
}