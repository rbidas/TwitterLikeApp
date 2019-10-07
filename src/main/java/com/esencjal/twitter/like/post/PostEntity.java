package com.esencjal.twitter.like.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POSTS")
@Data
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Long userId;
    @CreationTimestamp
    private LocalDateTime createTimestamp;

    public PostEntity(String message, Long userId) {
        this.message = message;
        this.userId = userId;
    }
}
