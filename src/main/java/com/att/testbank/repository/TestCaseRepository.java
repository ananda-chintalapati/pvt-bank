package com.att.testbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.att.testbank.entity.TestCaseEntity;

@Component
public interface TestCaseRepository extends JpaRepository<TestCaseEntity, Integer> {

	public List<TestCaseEntity> findById(int id);
	
	public List<TestCaseEntity> findByComponent(String component);
	
	public List<TestCaseEntity> findByStatus(String status);
	
	public List<TestCaseEntity> findByResult(String result);
}
