package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.CustomerPlanDetails;
@Repository
public interface CustomePlanDetailsRepo extends JpaRepository<CustomerPlanDetails, Long>{

}
