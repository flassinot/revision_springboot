package com.example.taskflow.repository;

import com.example.taskflow.model.Project;
import com.example.taskflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByOwner(User owner);

    List<Project> findByMembersContaining(User member);
}
