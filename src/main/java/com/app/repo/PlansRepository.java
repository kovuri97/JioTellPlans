package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Plans;
@Repository
public interface PlansRepository extends JpaRepository<Plans, Integer>{

}
