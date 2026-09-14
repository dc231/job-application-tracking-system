package com.example.jobapplicationtrackingsystem.controller;

import com.example.jobapplicationtrackingsystem.dto.CreateJobApplicationRequest;
import com.example.jobapplicationtrackingsystem.dto.JobApplicationResponse;
import com.example.jobapplicationtrackingsystem.dto.UpdateJobApplicationRequest;
import com.example.jobapplicationtrackingsystem.service.JobApplicationService;
import com.example.jobapplicationtrackingsystem.exception.ApplicationNotFoundException;
import com.example.jobapplicationtrackingsystem.model.ApplicationStatus;
import com.example.jobapplicationtrackingsystem.dto.ApplicationPageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(
            JobApplicationService jobApplicationService
    ) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/api/applications")
    public ResponseEntity<ApplicationPageResponse> getAllApplications(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }

        if (size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Page size must be between 1 and 100"
            );
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("applicationDate").descending()
        );

        ApplicationPageResponse applications =
                jobApplicationService.searchApplications(
                        search,
                        status,
                        pageable
                );

        return ResponseEntity.ok(applications);
    }

    @PostMapping("/api/applications")
    public ResponseEntity<JobApplicationResponse> createApplication(
            @Valid @RequestBody CreateJobApplicationRequest request
    ) {
        JobApplicationResponse response =
                jobApplicationService.createApplication(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/api/applications/{id}")
    public JobApplicationResponse getApplicationById(
            @PathVariable Long id
    ) {
        return jobApplicationService.getApplicationById(id)
                .orElseThrow(() -> new ApplicationNotFoundException(id));
    }

    @PutMapping("/api/applications/{id}")
    public JobApplicationResponse updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody UpdateJobApplicationRequest request
    ) {
        return jobApplicationService.updateApplication(id, request)
                .orElseThrow(() -> new ApplicationNotFoundException(id));
    }

    @DeleteMapping("/api/applications/{id}")
    public ResponseEntity<Map<String, String>> deleteApplication(
            @PathVariable Long id
    ) {
        if (jobApplicationService.getApplicationById(id).isEmpty()) {
            throw new ApplicationNotFoundException(id);
        }

        jobApplicationService.deleteApplication(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Job application deleted successfully");
        response.put("id", id.toString());

        return ResponseEntity.ok(response);
    }
}