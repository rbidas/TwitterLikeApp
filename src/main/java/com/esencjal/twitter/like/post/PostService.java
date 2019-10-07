package com.esencjal.twitter.like.post;

import com.esencjal.twitter.like.post.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {
    Iterable<PostDto> getList(List<Long> id);

    Page<PostDto> getAllPostList(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);

    PostDto create(PostDto body);

    PostDto get(Long id);


}
