package org.example.tasktracker.Controller;


import lombok.AllArgsConstructor;
import org.example.tasktracker.ApiResponse.ApiResponse;
import org.example.tasktracker.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/Task")
public class TaskController {

    ArrayList<Task> tasks=new ArrayList<>();



    @GetMapping("/getAllTask")
    public ArrayList<Task> getTask(){
        return tasks;
    }



    @PostMapping("/addTask")
    public ApiResponse addTask(@RequestBody Task task){
        tasks.add(task);
        return new ApiResponse("Successfully added task");
    }


    @PutMapping("/updateTask/{id}")
    public ApiResponse updateTask(@PathVariable int id,@RequestBody Task task){
        for(int j=0;j<tasks.size();j++){
            if(tasks.get(j).getId()==id){
                tasks.set(j,task);
                return new ApiResponse("Successfully updated task");
            }
        }
        return new ApiResponse("task not found");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteTask(@PathVariable int id){
        for(Task task:tasks){
            if(task.getId()==id){
                tasks.remove(task);
                return new ApiResponse("Successfully deleted task");
            }
        }
        return new ApiResponse("task not found");
    }

    @PutMapping("/updateTaskStatus/{id}")
    public ApiResponse updateTaskStatus(@PathVariable int id){
        for(Task task:tasks){
            if(task.getId()==id){
                task.setStatus(!task.isStatus());
                return  new ApiResponse("Successfully updated task status");
            }
        }
        return new ApiResponse("Task status not found");
    }

    @GetMapping("/searchByTitle/{title}")
    public Task getTaskByTitle(@PathVariable String title){
        for(Task task:tasks){
            if(task.getTitle().equals(title)){
                return task;
            }
        }
        return null;
    }






}
