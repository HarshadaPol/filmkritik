package com.Filmkritik.authservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.Filmkritik.authservice.entities.SecurityQuestionsEntity;


public interface SecurityQuestionsRepository extends JpaRepository<SecurityQuestionsEntity, Long>{



	SecurityQuestionsEntity findById(long sq_id);

}
