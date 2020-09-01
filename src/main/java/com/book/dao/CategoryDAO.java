package com.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.pojo.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}