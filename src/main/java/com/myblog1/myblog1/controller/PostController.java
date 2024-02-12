package com.myblog1.myblog1.controller;

import com.myblog1.myblog1.PayLoad.PostDto;
import com.myblog1.myblog1.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
private PostService ps;

    public PostController(PostService ps) {
        this.ps = ps;
    }
@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){

     PostDto dto= ps.createPost(postDto);
     return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/post?id=1
    @GetMapping
    public ResponseEntity<PostDto> getPostById(@RequestParam Long id){

        PostDto dto= ps.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    //http://localhost:8080/api/post/all?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
    @GetMapping("/all")
    public List<PostDto> getAllPosts(
            @RequestParam(name="pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = "id",required = false) String sortDir

    ){

       List<PostDto> postdtos= ps.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return postdtos;
    }

}
