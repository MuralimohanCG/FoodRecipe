package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.model.Items;


public interface FoodRepository extends JpaRepository<Items, Integer> {







}
