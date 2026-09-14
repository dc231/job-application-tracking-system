package com.example.jobapplicationtrackingsystem.service;

import com.example.jobapplicationtrackingsystem.dto.CreateJobApplicationRequest;
import com.example.jobapplicationtrackingsystem.dto.JobApplicationResponse;
import com.example.jobapplicationtrackingsystem.dto.UpdateJobApplicationRequest;
import com.example.jobapplicationtrackingsystem.model.JobApplication;
import com.example.jobapplicationtrackingsystem.repository.JobApplicationRepository;
import com.example.jobapplicationtrackingsystem.model.ApplicationStatus;
import com.example.jobapplicationtrackingsystem.dto.ApplicationPageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public JobApplicationResponse createApplication(
            CreateJobApplicationRequest request
    ) {
        JobApplication jobApplication = new JobApplication();

        jobApplication.setCompanyName(request.getCompanyName());
        jobApplication.setJobTitle(request.getJobTitle());
        jobApplication.setStatus(request.getStatus());
        jobApplication.setApplicationDate(request.getApplicationDate());
        jobApplication.setJobUrl(request.getJobUrl());
        jobApplication.setLocation(request.getLocation());
        jobApplication.setNotes(request.getNotes());

        JobApplication savedApplication =
                jobApplicationRepository.save(jobApplication);

        return convertToResponse(savedApplication);
    }

    public List<JobApplicationResponse> getAllApplications() {
        return jobApplicationRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Optional<JobApplicationResponse> getApplicationById(Long id) {
        return jobApplicationRepository.findById(id)
                .map(this::convertToResponse);
    }

    public Optional<JobApplicationResponse> updateApplication(
            Long id,
            UpdateJobApplicationRequest request
    ) {
        return jobApplicationRepository.findById(id)
                .map(existingApplication -> {
                    existingApplication.setCompanyName(request.getCompanyName());
                    existingApplication.setJobTitle(request.getJobTitle());
                    existingApplication.setStatus(request.getStatus());
                    existingApplication.setApplicationDate(
                            request.getApplicationDate()
                    );
                    existingApplication.setJobUrl(request.getJobUrl());
                    existingApplication.setLocation(request.getLocation());
                    existingApplication.setNotes(request.getNotes());

                    JobApplication savedApplication =
                            jobApplicationRepository.save(existingApplication);

                    return convertToResponse(savedApplication);
                });
    }

    public void deleteApplication(Long id) {
        jobApplicationRepository.deleteById(id);
    }

    public JobApplicationResponse convertToResponse(
            JobApplication jobApplication
    ) {
        return new JobApplicationResponse(
                jobApplication.getId(),
                jobApplication.getCompanyName(),
                jobApplication.getJobTitle(),
                jobApplication.getStatus(),
                jobApplication.getApplicationDate(),
                jobApplication.getJobUrl(),
                jobApplication.getLocation(),
                jobApplication.getNotes()
        );
    }

    public ApplicationPageResponse searchApplications(
            String search,
            ApplicationStatus status,
            Pageable pageable
    ) {
        String normalizedSearch = search == null ? "" : search.trim();

        Page<JobApplicationResponse> result =
                jobApplicationRepository
                        .searchApplications(normalizedSearch, status, pageable)
                        .map(this::convertToResponse);

        return new ApplicationPageResponse(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isFirst(),
                result.isLast()
        );
    }
}