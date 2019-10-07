package com.esencjal.twitter.like.tags;

import com.esencjal.twitter.like.tags.utils.TagExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestTagExtractor {

    @Test
    public void test_should_return_empty_list_when_message_without_tags() {
        List<String> extract = TagExtractor.extract("");
        assertEquals(0, extract.size());
    }

    @Test
    public void test_should_return_two_element_list_when_message_contains_two_tags() {
        List<String> extract = TagExtractor.extract("Simple message with #some #awesome tags");
        assertEquals(2, extract.size());
        assertTrue(extract.contains("some"));
        assertTrue(extract.contains("awesome"));
    }

    @Test
    public void test_should_return_two_element_list_when_message_contains_two_tags_with_non_ascii_char() {
        List<String> extract = TagExtractor.extract("To jest #ładna polska #wiadomość");
        assertEquals(2, extract.size());
        assertTrue(extract.contains("ładna"));
        assertTrue(extract.contains("wiadomość"));
    }
}