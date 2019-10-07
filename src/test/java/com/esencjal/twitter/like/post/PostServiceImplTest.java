package com.esencjal.twitter.like.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PostServiceImplTest {
    PostRepository postRepository;
    JmsTemplate jmsTemplate;
    private PostService objectUnderTest;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        jmsTemplate = mock(JmsTemplate.class);
        objectUnderTest = new PostServiceImpl(jmsTemplate, postRepository);
    }

    @Test
    void test_get() {
        List<Long> testData = Arrays.asList(1L, 2L);
        objectUnderTest.getList(testData);
        verify(postRepository).findAllById(testData);
    }
}