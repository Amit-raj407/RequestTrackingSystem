package com.project.RequestTrackingSystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.RequestTrackingSystem.models.Requests;


@Repository
public interface RequestRepo extends JpaRepository<Requests, Integer> {

}
