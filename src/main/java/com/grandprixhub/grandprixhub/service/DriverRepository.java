package com.grandprixhub.grandprixhub.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grandprixhub.grandprixhub.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}