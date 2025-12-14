package org.govnorganization.taskmweb.taskmweb.controllers;

import java.util.ArrayList;
import java.util.List;
import org.govnorganization.taskmweb.taskmweb.model.TaskModel;
import org.govnorganization.taskmweb.taskmweb.repositories.TaskModelRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskmController implements CommandLineRunner {
  private final TaskModelRepository taskModelRepository;

  public TaskmController(TaskModelRepository taskModelRepository) {
    this.taskModelRepository = taskModelRepository;
  }

  @GetMapping
  public String index(Model model) {
    List<TaskModel> all_tasks = taskModelRepository.findAll();

    model.addAttribute("all_tasks", all_tasks);
    model.addAttribute("new_task", new TaskModel());

    model.addAttribute("data", "someshitlolsss");
    return "index";
  }

  @PostMapping("/search")
  public String search(@RequestParam("searchTerm") String searchTerm, Model model){

    List<TaskModel> all_tasks = taskModelRepository.findAll();
    List<TaskModel> results = new ArrayList<>();

    for(TaskModel i : all_tasks){
      if (i.getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
        results.add(i);
      }
    }

    model.addAttribute("all_tasks", results);
    model.addAttribute("new_task", new TaskModel());
    model.addAttribute("searchTerm", searchTerm);

    model.addAttribute("data", "someshitlolsss");
    return "index";

  }

  @PostMapping("/add")
  public String add(@ModelAttribute TaskModel task){
    taskModelRepository.save(task);
    return "redirect:/";
  }
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Long id){
    taskModelRepository.deleteById(id);


    return "redirect:/";
  }

  @PostMapping("/deleteAll")
  public String delete_all(){

   taskModelRepository.deleteAll();
    return "redirect:/";
  }

  @Override
  public void run(String @NonNull ... args) {
  }
}
