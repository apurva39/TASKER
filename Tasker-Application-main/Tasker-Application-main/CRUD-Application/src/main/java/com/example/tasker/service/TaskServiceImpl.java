package com.example.tasker.service;

import com.example.tasker.model.Task;
import com.example.tasker.model.TaskEntity;
import com.example.tasker.repository.TaskRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        BeanUtils.copyProperties(task, taskEntity);

        taskRepository.save(taskEntity);

        task.setId(taskEntity.getId()); // Set the ID of the created task
        return task;
    }

    @Override
    public List<Task> readTask() {
        List<TaskEntity> taskList = taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();

        for (TaskEntity taskEntity : taskList) {
            Task task = new Task();
            BeanUtils.copyProperties(taskEntity, task);
            tasks.add(task);
        }

        return tasks;
    }

    @Override
    public Boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String updateTask(Long id, Task task) {
        Optional<TaskEntity> existingTaskOpt = taskRepository.findById(id);
        if (!existingTaskOpt.isPresent()) {
            return "Task not found";
        }

        TaskEntity existingTask = existingTaskOpt.get();
        BeanUtils.copyProperties(task, existingTask, "id");

        taskRepository.save(existingTask);
        return "Updated Successfully";
    }

}
