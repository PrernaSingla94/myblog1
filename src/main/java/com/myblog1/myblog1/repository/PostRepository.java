package com.myblog1.myblog1.repository;

import com.myblog1.myblog1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post,Long>{
}
