package com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {


}
