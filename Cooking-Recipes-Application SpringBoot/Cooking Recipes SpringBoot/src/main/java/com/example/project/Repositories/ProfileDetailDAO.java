package com.example.project.Repositories;


import com.example.project.Models.ProfileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDetailDAO extends JpaRepository<ProfileDetail, Long> {
    // Additional query methods can be added here if needed
}
