package com.example.jobapplicationtrackingsystem.service;

import com.example.jobapplicationtrackingsystem.model.JobApplication;
import com.example.jobapplicationtrackingsystem.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public JobApplication createApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    public Optional<JobApplication> getApplicationById(Long id) {
        return jobApplicationRepository.findById(id);
    }

    public Optional<JobApplication> updateApplication(
            Long id,
            JobApplication updatedApplication
    ) {
        return jobApplicationRepository.findById(id)
                .map(existingApplication -> {
                    existingApplication.setCompanyName(updatedApplication.getCompanyName());
                    existingApplication.setJobTitle(updatedApplication.getJobTitle());
                    existingApplication.setStatus(updatedApplication.getStatus());
                    existingApplication.setApplicationDate(updatedApplication.getApplicationDate());
                    existingApplication.setJobUrl(updatedApplication.getJobUrl());
                    existingApplication.setLocation(updatedApplication.getLocation());
                    existingApplication.setNotes(updatedApplication.getNotes());

                    return jobApplicationRepository.save(existingApplication);
                });
    }

    public void deleteApplication(Long id) {
        jobApplicationRepository.deleteById(id);
    }
}