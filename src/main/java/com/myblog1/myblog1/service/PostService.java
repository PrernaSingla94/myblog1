package com.myblog1.myblog1.service;

import com.myblog1.myblog1.PayLoad.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPostById(Long id);

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
