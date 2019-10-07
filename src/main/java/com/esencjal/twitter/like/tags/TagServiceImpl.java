package com.esencjal.twitter.like.tags;

import com.esencjal.twitter.like.tags.dto.TagDto;
import com.esencjal.twitter.like.tags.dto.TagsTrendElement;
import com.esencjal.twitter.like.tags.dto.TagsTrends;
import com.esencjal.twitter.like.tags.utils.TagExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class TagServiceImpl implements TagService {
    private TagsRepository tagsRepository;

    @Autowired
    public TagServiceImpl(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    @Override
    public List<TagDto> extract(String message, Long messageId) {
        return TagExtractor.extract(message)
                .stream()
                .map(x -> TagDto.builder().tag(x).messageId(messageId).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<TagDto> save(List<TagDto> tagDtos) {
        List<TagEntity> tagEntities = tagDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
        Iterator<TagEntity> iterator = tagsRepository.saveAll(tagEntities).iterator();
        Iterable<TagEntity> valueIterable = () -> iterator;
        return StreamSupport
                .stream(valueIterable.spliterator(), false)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public TagsTrends trends(String tagName, String startDate, String endDate) {
        List<TagsTrendElement> collect = tagsRepository
                .findCountPerDay(tagName, LocalDateTime.parse(startDate), LocalDateTime.parse(endDate))
                .stream()
                .map(this::mapToTagTrend)
                .collect(Collectors.toList());

        return TagsTrends.builder().name(tagName).elements(collect).build();
    }

    @Override
    public Page<TagDto> getAllByTagName(Pageable pageable, String name) {
        return tagsRepository.findAllByTag(pageable, name).map(this::convert);
    }

    private TagsTrendElement mapToTagTrend(Object[] objects) {
        return TagsTrendElement
                .builder()
                .count(((BigInteger) objects[0]).longValue())
                .date(LocalDate.parse((String) objects[1]))
                .build();
    }

    private TagEntity convertToEntity(TagDto tagDto) {
        TagEntity entity = new TagEntity();

        entity.setMessageId(tagDto.getMessageId());
        entity.setTag(tagDto.getTag());

        return entity;
    }

    private TagDto convert(TagEntity tagEntity) {
        return TagDto
                .builder()
                .createDate(tagEntity.getCreateTimestamp())
                .messageId(tagEntity.getMessageId())
                .id(tagEntity.getId())
                .tag(tagEntity.getTag())
                .build();
    }
}
