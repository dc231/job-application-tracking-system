package com.example.jobapplicationtrackingsystem.repository;

import com.example.jobapplicationtrackingsystem.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {
}