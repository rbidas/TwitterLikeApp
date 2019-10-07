package com.esencjal.twitter.like.jms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JmsPostMessage {
    private long userId;
    private long messageId;
    private String message;
}
