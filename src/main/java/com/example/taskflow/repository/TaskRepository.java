package com.example.taskflow.repository;

import com.example.taskflow.model.Task;
import com.example.taskflow.model.Project;
import com.example.taskflow.model.User;
import com.example.taskflow.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject(Project project);

    List<Task> findByAssignee(User user);

    List<Task> findByStatus(TaskStatus status);
}
