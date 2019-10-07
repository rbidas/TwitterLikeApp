package com.esencjal.twitter.like.tags.controller;

import com.esencjal.twitter.like.tags.TagService;
import com.esencjal.twitter.like.tags.dto.TagDto;
import com.esencjal.twitter.like.tags.dto.TagsTrends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    private TagService service;

    @Autowired
    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping("/trends")
    TagsTrends trends(@RequestParam String tagName, @RequestParam String startDate, @RequestParam String endDate) {
        return service.trends(tagName, startDate, endDate);
    }

    @GetMapping(value = "/tags")
    Page<TagDto> getPaginationByTagList(Pageable pageable, @RequestParam String tagName) {
        return service.getAllByTagName(pageable, tagName);
    }
}
