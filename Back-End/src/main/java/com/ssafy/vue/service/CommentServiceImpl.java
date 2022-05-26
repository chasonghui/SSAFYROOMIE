package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.Comment;
import com.ssafy.vue.mapper.CommentMapper;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public List<Comment> commentList(int ano) {
		// TODO Auto-generated method stub
		return commentMapper.selectComment(ano);
	}

	@Override
	public boolean writeComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentMapper.insertComment(comment)==1;
	}

	@Override
	public boolean deleteComment(int cno) {
		// TODO Auto-generated method stub
		return commentMapper.deleteComment(cno)==1;
	}

	@Override
	public int getCommentsCnt(int bid) {
		// TODO Auto-generated method stub
		return commentMapper.getCommentsCnt(bid);
	}
	
}