package com.esencjal.twitter.like.tags.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDto {
    private Long id;
    private String tag;
    private Long messageId;
    @CreationTimestamp
    private LocalDateTime createDate;
}
