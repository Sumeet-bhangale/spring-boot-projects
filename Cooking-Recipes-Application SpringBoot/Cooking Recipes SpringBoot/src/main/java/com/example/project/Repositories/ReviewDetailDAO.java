package com.example.project.Repositories;


import com.example.project.Models.ReviewDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDetailDAO extends JpaRepository<ReviewDetail, Long> {
}
