package com.myblog1.myblog1.service.impl;

import com.myblog1.myblog1.PayLoad.CommentDto;
import com.myblog1.myblog1.entity.Post;
import com.myblog1.myblog1.entity.Comment;
import com.myblog1.myblog1.exception.ResourceNotFoundException;
import com.myblog1.myblog1.repository.CommentRepository;
import com.myblog1.myblog1.repository.PostRepository;
import com.myblog1.myblog1.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
  private PostRepository pr;
  private CommentRepository cr;
private ModelMapper modelMapper;
    public CommentServiceImpl(PostRepository pr, CommentRepository cr,ModelMapper modelMapper) {
        this.pr = pr;
        this.cr = cr;
        this.modelMapper=modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = pr.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id" + postId)
        );
        Comment comment=new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(post);
        Comment save = cr.save(comment);
        CommentDto dto=new CommentDto();
        dto.setId(save.getId());
        dto.setEmail(save.getEmail());
        dto.setText(save.getText());
        return dto;

    }

    @Override
    public void deleteComment(long id) {
        cr.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
        Post post = pr.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id" + postId)
        );
        Comment comment = cr.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found with id" + id)
        );
        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(comment.getPost());
        Comment save = cr.save(c);
        CommentDto commentDto1 = mapToDto(save);

return commentDto1;
    }
    CommentDto mapToDto(Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;

    }
    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;

    }

}
