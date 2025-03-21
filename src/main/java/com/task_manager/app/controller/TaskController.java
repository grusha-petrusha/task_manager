package com.task_manager.app.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_manager.app.model.Task;
import com.task_manager.app.repository.TaskRepository;

@RestController
@RequestMapping("/api/tasks")

public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task, Authentication authentication) {
        Task existingTask = taskRepository.findById(id).orElseThrow();
        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) ||
            existingTask.getAssignee().getEmail().equals(authentication.getName())) {
                
                existingTask.setTitle(task.getTitle());
                existingTask.setDescription(task.getDescription());
                existingTask.setPriority(task.getPriority());
                existingTask.setStatus(task.getStatus());
                return taskRepository.save(existingTask);
            }

            throw new SecurityException("Not authorized to edit this task");
    }
    
}
