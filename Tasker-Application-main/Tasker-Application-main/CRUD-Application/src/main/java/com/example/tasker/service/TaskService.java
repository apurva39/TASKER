package com.example.tasker.service;

import com.example.tasker.model.Task;

import java.util.List;

public interface TaskService {
  Task createTask(Task task);

  List<Task> readTask();

  Boolean deleteTask(Long id);

  String updateTask(Long id, Task task);
}
