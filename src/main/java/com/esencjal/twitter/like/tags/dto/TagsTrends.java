package com.esencjal.twitter.like.tags.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TagsTrends {
    String name;
    List<TagsTrendElement> elements;
}
