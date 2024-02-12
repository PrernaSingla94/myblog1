package com.myblog1.myblog1.controller;

import com.myblog1.myblog1.PayLoad.CommentDto;
import com.myblog1.myblog1.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService cs;

    public CommentController(CommentService cs) {
        this.cs = cs;
    }
    //http://localhost:8080/api/comments?postId=1
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @RequestParam long postId){
        CommentDto dto=cs.createComment(commentDto,postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    //http://localhost:8080/api/comments/2
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id){
        cs.deleteComment(id);
        return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }
    //http://localhost:8080/api/comments/1/post/1
    @PutMapping("{id}/post/{postId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable long id,
            @RequestBody CommentDto commentDto,
            @PathVariable long postId

    ){
        CommentDto dto  =cs.updateComment(id,commentDto,postId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
