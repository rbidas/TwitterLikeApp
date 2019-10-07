package com.esencjal.twitter.like.post;

import com.esencjal.twitter.like.jms.JmsPostMessage;
import com.esencjal.twitter.like.post.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class PostServiceImpl implements PostService {
    private JmsTemplate template;
    private PostRepository repository;

    @Autowired
    public PostServiceImpl(JmsTemplate template, PostRepository repository) {
        this.template = template;
        this.repository = repository;
    }

    public Iterable<PostDto> getList(List<Long> id) {
        Iterator<PostEntity> iterator = repository.findAllById(id).iterator();
        Iterable<PostEntity> valueIterable = () -> iterator;
        return StreamSupport
                .stream(valueIterable.spliterator(), false)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PostDto> getAllPostList(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findAllByCreateTimestampAfterAndCreateTimestampBefore(startDate, endDate, pageable).map(this::convert);
    }

    private PostDto convert(PostEntity postEntity) {
        return PostDto
                .builder()
                .userId(postEntity.getUserId())
                .message(postEntity.getMessage())
                .messageId(postEntity.getId())
                .createDate(postEntity.getCreateTimestamp())
                .build();
    }

    public PostDto create(PostDto body) {
        log.info("Save a message to DB.");
        PostEntity save = repository.save(new PostEntity(body.getMessage(), body.getUserId()));
        log.info("Sending an message to queue.");
        template.convertAndSend("tags", JmsPostMessage
                .builder()
                .message(save.getMessage())
                .messageId(save.getId())
                .userId(body.getUserId())
                .build());
        return convert(save);
    }

    @Override
    public PostDto get(Long id) {
        return convert(repository.findById(id).get());
    }
}
