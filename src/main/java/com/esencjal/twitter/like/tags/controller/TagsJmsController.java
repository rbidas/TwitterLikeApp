package com.esencjal.twitter.like.tags.controller;

import com.esencjal.twitter.like.jms.JmsPostMessage;
import com.esencjal.twitter.like.tags.TagService;
import com.esencjal.twitter.like.tags.dto.TagDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TagsJmsController {

    private TagService tagService;

    @Autowired
    public TagsJmsController(TagService tagService) {
        this.tagService = tagService;
    }

    @JmsListener(destination = "tags", containerFactory = "myFactory")
    public void receiveMessage(JmsPostMessage message) {
        log.debug("Received <" + message.getMessage() + ">");
        List<TagDto> extract = tagService.extract(message.getMessage(), message.getMessageId());
        tagService.save(extract);
        log.debug("<Done>");
    }

}