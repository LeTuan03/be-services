package com.example.demo.controller;

import com.example.demo.entity.Project;
import com.example.demo.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ProjectController {
    @Autowired
    private ProjectRepo projectsRepo;

    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Project>> getAllProjects() {
        try {
            List<Project> projectsList = new ArrayList<>();
            projectsRepo.findAll().forEach(projectsList::add);
            if(projectsList.isEmpty()) {
                return new ResponseEntity<>( HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projectsList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getProjectById/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> projectData = projectsRepo.findById(id);

        if(projectData.isPresent()) {
            return new ResponseEntity<>(projectData.get(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addProject")
    public ResponseEntity<Project>  addProject(@RequestBody Project projects) {
        Project projectObj = projectsRepo.save(projects);

        return new ResponseEntity<>(projectObj, HttpStatus.OK);
    }

    @PutMapping("/updateProjectById/{id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable Long id, @RequestBody Project newProjectData) {
        Optional<Project> oldProjectDataOptional = projectsRepo.findById(id);

        if (oldProjectDataOptional.isPresent()) {
            Project oldProjectData = oldProjectDataOptional.get();

            if (newProjectData.getName() != null) {
                oldProjectData.setName(newProjectData.getName());
            }

            if (newProjectData.getStartDate() != null) {
                oldProjectData.setStartDate(newProjectData.getStartDate());
            }

            if (newProjectData.getEndDate() != null) {
                oldProjectData.setEndDate(newProjectData.getEndDate());
            }

            if (newProjectData.getNote() != null) {
                oldProjectData.setNote(newProjectData.getNote());
            }

            if (newProjectData.getStatus() != null) {
                oldProjectData.setStatus(newProjectData.getStatus());
            }

            if (newProjectData.getDescription() != null) {
                oldProjectData.setDescription(newProjectData.getDescription());
            }

            oldProjectData.setUpdatedAt(new Date());

            Project updatedProjectData = projectsRepo.save(oldProjectData);

            return new ResponseEntity<>(updatedProjectData, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/deleteProjectById/{id}")
    public ResponseEntity<HttpStatus> deleteProjectById(@PathVariable Long id) {
        projectsRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getProjectsByAccountId/{accountId}")
    public ResponseEntity<List<Project>> getProjectsByAccountId(@PathVariable Integer accountId) {
        List<Project> projectsData = projectsRepo.findByAccountId(accountId);

        if (!projectsData.isEmpty()) {
            return new ResponseEntity<>(projectsData, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}