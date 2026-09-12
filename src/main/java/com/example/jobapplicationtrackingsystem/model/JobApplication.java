package com.example.jobapplicationtrackingsystem.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "company_name")
    private String companyName;

    @NotBlank
    @Column(name = "job_title")
    private String jobTitle;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;

    @NotNull
    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Column(name = "job_url")
    private String jobUrl;

    @Column(name = "location")
    private String location;

    @Size(max = 2000)
    @Column(name = "notes")
    private String notes;

    public JobApplication() {
    }

    public JobApplication(
            String companyName,
            String jobTitle,
            ApplicationStatus status,
            LocalDate applicationDate,
            String jobUrl,
            String location,
            String notes
    ) {
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