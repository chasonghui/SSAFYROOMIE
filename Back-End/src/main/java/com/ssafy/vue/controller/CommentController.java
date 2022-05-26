package com.ssafy.vue.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Comment;
import com.ssafy.vue.service.CommentService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/comment")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value = "모든 댓글의 정보를 반환한다.", response = List.class)
	@GetMapping("{ano}")
	public ResponseEntity<List<Comment>> commentList(@PathVariable int ano) throws Exception {
		logger.debug("retrieveComment - 호출");
		List<Comment> comments = commentService.commentList(ano);
		logger.debug("comments",comments.get(0).toString());
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeComment(@RequestBody Comment comment) {
		logger.debug("writeComment - 호출");
		logger.debug(comment.toString());
		if (commentService.writeComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시물에 해당하는 댓글 리스트 총 수 ", response = Integer.class)
	@GetMapping("/cnt/{cno}")
	public ResponseEntity<Integer> getCommentsCnt(@PathVariable int cno) throws Exception {
		logger.info("1-------------getCommentsCnt-----------------------------"+new Date());
		
		int totCnt = commentService.getCommentsCnt(cno);
		return new ResponseEntity<Integer>(totCnt, HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글번호에 해당하는 댓글 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{cno}")
	public ResponseEntity<String> deleteComment(@PathVariable int cno) {
		logger.debug("deleteComment - 호출");
		if (commentService.deleteComment(cno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
