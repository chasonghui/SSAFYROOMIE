package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Comment;


public interface CommentService {
	public List<Comment> commentList(int ano);
	public boolean writeComment(Comment board);
	public boolean deleteComment(int cno);
	public int getCommentsCnt(int bid);
}
