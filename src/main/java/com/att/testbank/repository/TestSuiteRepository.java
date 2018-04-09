package com.att.testbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.att.testbank.entity.TestSuiteEntity;

@Component
public interface TestSuiteRepository extends JpaRepository<TestSuiteEntity, Integer> {

	public List<TestSuiteEntity> findByReleaseId(int releaseId);
	
	public List<TestSuiteEntity> findByName(String name);
}
