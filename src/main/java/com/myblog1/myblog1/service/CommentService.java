package com.myblog1.myblog1.service;

import com.myblog1.myblog1.PayLoad.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    void deleteComment(long id);

    CommentDto updateComment(long id, CommentDto commentDto, long postId);

}
