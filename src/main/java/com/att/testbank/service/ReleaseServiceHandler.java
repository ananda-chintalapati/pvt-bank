package com.att.testbank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.att.testbank.entity.ReleaseEntity;
import com.att.testbank.model.Release;
import com.att.testbank.pvt.transform.BeanTransformer;
import com.att.testbank.repository.ReleaseRepository;

@Component
@Transactional
public class ReleaseServiceHandler {

	@Autowired
	private ReleaseRepository releaseRepository;
	
	@Autowired
	private BeanTransformer beanMapper;
	
	public void createRelease(Release release) {
		ReleaseEntity releaseEntity = null;
		if(release.getId() == 0) {
			releaseEntity = new ReleaseEntity();
		} else {
			releaseEntity = releaseRepository.findById(release.getId());
			releaseEntity.setId(release.getId());
		}
		releaseEntity.setDescription(release.getDescription());
		releaseEntity.setRelease(release.getReleaseName());
		releaseRepository.save(releaseEntity);
	}
	
	public Release findRelease(int id) {
		ReleaseEntity releaseEntity = releaseRepository.findById(id);
		Release release = new Release();
		release.setDescription(releaseEntity.getDescription());
		release.setId(releaseEntity.getId());
		release.setReleaseName(releaseEntity.getRelease());
		return release;
	}
	
	public List<Release> findMatchingRelease(String releaseName) {
		List<ReleaseEntity> releaseEntityList = releaseRepository.findByNameLike(releaseName);
		List<Release> releaseList = new ArrayList<Release>();
		for(ReleaseEntity releaseEntity:releaseEntityList) {
			Release release = new Release();
			release.setDescription(releaseEntity.getDescription());
			release.setId(releaseEntity.getId());
			release.setReleaseName(releaseEntity.getRelease());
			releaseList.add(release);
		}
		return releaseList;
	}
	
	public List<Release> findAllReleases() {
		List<ReleaseEntity> releaseEntityList = releaseRepository.findAll();
		List<Release> releaseList = new ArrayList<Release>();
		for(ReleaseEntity releaseEntity:releaseEntityList) {
			Release release = new Release();
			release.setDescription(releaseEntity.getDescription());
			release.setId(releaseEntity.getId());
			release.setReleaseName(releaseEntity.getRelease());
			releaseList.add(release);
		}
		return releaseList;
	}
	
}
