package com.example.jobapplicationtrackingsystem.controller;

import com.example.jobapplicationtrackingsystem.model.JobApplication;
import com.example.jobapplicationtrackingsystem.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Job Application Tracking System!";
    }

    @GetMapping("/api/applications")
    public List<JobApplication> getAllApplications() {
        return jobApplicationService.getAllApplications();
    }

    @PostMapping("/api/applications")
    public JobApplication createApplication(@RequestBody JobApplication jobApplication) {
        return jobApplicationService.createApplication(jobApplication);
    }

    @GetMapping("/api/applications/{id}")
    public ResponseEntity<JobApplication> getApplicationById(@PathVariable Long id) {
        return jobApplicationService.getApplicationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/api/applications/{id}")
    public ResponseEntity<JobApplication> updateApplication(
            @PathVariable Long id,
            @RequestBody JobApplication updatedApplication
    ) {
        return jobApplicationService.updateApplication(id, updatedApplication)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        if (jobApplicationService.getApplicationById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        jobApplicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}