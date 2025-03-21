package com.task_manager.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.task_manager.app.model.Task;
import com.task_manager.app.repository.TaskRepository;
import com.task_manager.app.repository.UserRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getUserTasks(String userEmail) {
        return taskRepository.findByExecutorEmail(userEmail);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long taskId, Task taskDetails, String userEmail) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getExecutor().getEmail().equals(userEmail)) {
            throw new RuntimeException("You can only edit your tasks");
        }

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setPriority(taskDetails.getPriority());
        task.setStatus(taskDetails.getStatus());

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId, String userEmail) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getExecutor().getEmail().equals(userEmail)) {
            throw new RuntimeException("You can only delete your tasks");
        }

        taskRepository.delete(task);
    }
}
