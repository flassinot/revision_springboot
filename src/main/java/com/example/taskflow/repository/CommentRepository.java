package com.example.taskflow.repository;

import com.example.taskflow.model.Comment;
import com.example.taskflow.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTask(Task task);
}
