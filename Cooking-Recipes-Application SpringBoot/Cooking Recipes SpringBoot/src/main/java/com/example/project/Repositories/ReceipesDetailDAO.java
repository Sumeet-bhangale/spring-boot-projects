package com.example.project.Repositories;

import com.example.project.Models.ReceipesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceipesDetailDAO extends JpaRepository<ReceipesDetail, Long> {
}
