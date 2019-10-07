package com.esencjal.twitter.like.tags.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class TagsTrendElement {
    Long count;
    LocalDate date;
}
