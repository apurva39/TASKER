package com.example.tasker.controller;

import com.example.tasker.model.Task;
import com.example.tasker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @GetMapping
  public List<Task> getTasks() {
    return taskService.readTask();
  }

  @PostMapping
  public Task createTask(@RequestBody Task task) {
    return taskService.createTask(task);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task task) {
    String response = taskService.updateTask(id, task);
    if (response.equals("Updated Successfully")) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.status(404).body(response);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTask(@PathVariable Long id) {
    if (taskService.deleteTask(id)) {
      return ResponseEntity.ok("Task deleted successfully");
    } else {
      return ResponseEntity.status(404).body("Task not found");
    }
  }
}
