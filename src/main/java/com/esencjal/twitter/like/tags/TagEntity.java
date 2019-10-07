package com.esencjal.twitter.like.tags;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TAGS")
@Data
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;
    private Long messageId;
    @CreationTimestamp
    private LocalDateTime createTimestamp;
}
