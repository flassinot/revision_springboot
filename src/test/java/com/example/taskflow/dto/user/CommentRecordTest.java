package com.example.taskflow.dto.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommentRecordTest {

    @Test
    public void test() {
        var commentRecord = new CommentRecord(1L, "content", 1L, 1L, LocalDateTime.now());
        System.out.println(commentRecord);
    }
}