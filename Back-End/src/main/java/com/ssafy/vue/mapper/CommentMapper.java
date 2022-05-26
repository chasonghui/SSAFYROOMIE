package com.ssafy.vue.mapper;

import java.util.List;

import com.ssafy.vue.dto.Comment;

public interface CommentMapper {

	public List<Comment> selectComment(int ano);
	public int insertComment(Comment comment);
	public int deleteComment(int cno);
	public int getCommentsCnt(int bid);

}
