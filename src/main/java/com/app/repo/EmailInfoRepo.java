package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.EmailsInfo;
@Repository
public interface EmailInfoRepo extends JpaRepository<EmailsInfo, Integer> {

}
