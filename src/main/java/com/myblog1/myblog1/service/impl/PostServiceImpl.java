package com.myblog1.myblog1.service.impl;

import com.myblog1.myblog1.PayLoad.PostDto;
import com.myblog1.myblog1.entity.Post;
import com.myblog1.myblog1.exception.ResourceNotFoundException;
import com.myblog1.myblog1.repository.PostRepository;
import com.myblog1.myblog1.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
private PostRepository pr;
    private ModelMapper modelMapper;
    public PostServiceImpl(PostRepository pr,ModelMapper modelMapper) {
        this.pr = pr;
        this.modelMapper=modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post save = pr.save(post);
        PostDto dto=mapToDto(save);




        return dto;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post= pr.findById(id).orElseThrow(

                ()->new ResourceNotFoundException("Post not found with id:"+id)

        );
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort orders = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, orders);
        Page<Post> pagepost = pr.findAll(pageable);
        List<Post> all = pagepost.getContent();
        List<PostDto> collect = all.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return collect;
    }
    PostDto mapToDto (Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);
        return dto;
    }
}
