package com.example.jobapplicationtrackingsystem.repository;

import com.example.jobapplicationtrackingsystem.model.ApplicationStatus;
import com.example.jobapplicationtrackingsystem.model.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    @Query("""
        SELECT application
        FROM JobApplication application
        WHERE
            (
                :search = ''
                OR LOWER(application.companyName) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(application.jobTitle) LIKE LOWER(CONCAT('%', :search, '%'))
            )
        AND
            (
                :status IS NULL
                OR application.status = :status
            )
        """)
    Page<JobApplication> searchApplications(
            @Param("search") String search,
            @Param("status") ApplicationStatus status,
            Pageable pageable
    );
}