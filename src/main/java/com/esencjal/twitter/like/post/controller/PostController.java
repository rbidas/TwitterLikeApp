package com.esencjal.twitter.like.post.controller;

import com.esencjal.twitter.like.post.PostService;
import com.esencjal.twitter.like.post.PostServiceImpl;
import com.esencjal.twitter.like.post.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PostController {
    private PostService postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    PostDto createPost(@RequestBody PostDto body) {
        PostDto filteredBody = filterBody(body);
        return postService.create(filteredBody);
    }

    @GetMapping(value = "/post")
    Page<PostDto> getPaginationPostList(Pageable pageable, @RequestParam(required = false) String start, @RequestParam(required = false) String end) {
        LocalDateTime startDate;
        LocalDateTime endDate;
        startDate = start == null ? LocalDateTime.parse("1970-01-01T00:00:01.000") : LocalDateTime.parse(start);
        endDate = end == null ? LocalDateTime.now() : LocalDateTime.parse(end);

        return postService.getAllPostList(pageable, startDate, endDate);
    }

    @GetMapping("/post/")
    Iterable<PostDto> getPostList(@RequestParam List<Long> id) {
        return postService.getList(id);
    }

    @GetMapping("/post/{id}")
    PostDto getPost(@PathVariable Long id) {
        return postService.get(id);
    }


    private PostDto filterBody(PostDto body) {
        return PostDto
                .builder()
                .message(body.getMessage())
                .userId(1L) // TODO add user id from token
                .build();
    }
}
