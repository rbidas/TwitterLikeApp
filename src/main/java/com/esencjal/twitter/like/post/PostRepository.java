package com.esencjal.twitter.like.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {
    Page<PostEntity> findAllByCreateTimestampAfterAndCreateTimestampBefore(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
