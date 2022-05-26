package com.ssafy.vue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.vue.dto.Board;
import com.ssafy.vue.service.BoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Board>> retrieveBoard() throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.retrieveBoard(), HttpStatus.OK);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<?> categoryInfo(@PathVariable String category) throws Exception {
		return new ResponseEntity<List<Board>>(boardService.getCategory(category), HttpStatus.OK);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)
	@GetMapping("{articleno}")
	public ResponseEntity<Board> detailBoard(@PathVariable int articleno) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<Board>(boardService.detailBoard(articleno), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Board board) {
		logger.debug("writeBoard - 호출");
		if (boardService.writeBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{articleno}")
	public ResponseEntity<String> updateBoard(@RequestBody Board board) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + board);

		if (boardService.updateBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int articleno) {
		logger.debug("deleteBoard - 호출");
		if (boardService.deleteBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 전체검색", response = List.class)
	@GetMapping("search/{searchKeyword}")
	public ResponseEntity<List<Board>> searchBoardAll(@PathVariable String searchKeyword ) {
		logger.debug("searchBoardAll - 호출");
		return new ResponseEntity<List<Board>>(boardService.searchBoardAll(searchKeyword), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 카테고리별 검색(제목, 내용, 아이디 포함)", response = List.class)
	@GetMapping("search/{category}/{searchKeyword}")
	public ResponseEntity<List<Board>> searchBoard(@PathVariable String category, @PathVariable String searchKeyword ) {
		logger.debug("searchBoard - 호출");
		Map<String,String> map = new HashMap<String,String>();
		map.put("category",category);
		map.put("searchKeyword",searchKeyword);
		System.out.println("category: "+category+"  searchKeyword "+searchKeyword);
		return new ResponseEntity<List<Board>>(boardService.searchBoard(map), HttpStatus.OK);
	}
}