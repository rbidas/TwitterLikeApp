package com.esencjal.twitter.like.tags;

import com.esencjal.twitter.like.tags.dto.TagDto;
import com.esencjal.twitter.like.tags.dto.TagsTrends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    List<TagDto> extract(String message, Long messageId);

    List<TagDto> save(List<TagDto> tagDtos);

    TagsTrends trends(String tagName, String startDate, String endDate);

    Page<TagDto> getAllByTagName(Pageable pageable, String name);
}
