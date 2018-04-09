package com.att.testbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.att.testbank.entity.ReleaseEntity;

@Component
public interface ReleaseRepository extends JpaRepository<ReleaseEntity, Integer> {

	public ReleaseEntity findById(int id);
	
	public List<ReleaseEntity> findByNameLike(String releaseName);
	
}
