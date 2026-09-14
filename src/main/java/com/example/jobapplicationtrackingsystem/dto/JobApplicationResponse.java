package com.example.jobapplicationtrackingsystem.dto;

import com.example.jobapplicationtrackingsystem.model.ApplicationStatus;

import java.time.LocalDate;

public class JobApplicationResponse {

    private Long id;
    private String companyName;
    private String jobTitle;
    private ApplicationStatus status;
    private LocalDate applicationDate;
    private String jobUrl;
    private String location;
    private String notes;

    public JobApplicationResponse() {
    }

    public JobApplicationResponse(
            Long id,
            String companyName,
            String jobTitle,
            ApplicationStatus status,
            LocalDate applicationDate,
            String jobUrl,
            String location,
            String notes
    ) {
        this.id = id;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.status = status;
        this.applicationDate = applicationDate;
        this.jobUrl = jobUrl;
        this.location = location;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}