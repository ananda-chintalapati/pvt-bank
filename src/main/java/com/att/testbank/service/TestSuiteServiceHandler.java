package com.att.testbank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.att.testbank.entity.ReleaseEntity;
import com.att.testbank.entity.TestCaseEntity;
import com.att.testbank.entity.TestSuiteEntity;
import com.att.testbank.model.TestCase;
import com.att.testbank.model.TestSuite;
import com.att.testbank.pvt.transform.BeanTransformer;
import com.att.testbank.repository.ReleaseRepository;
import com.att.testbank.repository.TestCaseRepository;
import com.att.testbank.repository.TestSuiteRepository;

@Component
@Transactional
public class TestSuiteServiceHandler {

	@Autowired
	private TestSuiteRepository testSuiteRepository;
	
	@Autowired
	private ReleaseRepository releaseRepository;
	
	@Autowired
	private TestCaseRepository testCaseRepository;
	
	@Autowired
	private BeanTransformer beanMapper;
	
	@Transactional
	public void createTestSuite(TestSuite testSuite) {
		List<TestCase> testCaseList = testSuite.getTestCaseList();
		List<TestCaseEntity> testCaseEntityList = new ArrayList<TestCaseEntity>();
		if(CollectionUtils.isNotEmpty(testCaseList)) {
			for(TestCase testCase:testSuite.getTestCaseList()) {
				TestCaseEntity testCaseEntity = beanMapper.map(testCase, TestCaseEntity.class);
				TestSuiteEntity testSuiteEntity = testSuiteRepository.findOne(testSuite.getTestSuiteId());
				if(testSuiteEntity == null) {
				    testSuiteEntity = new TestSuiteEntity();
					testSuiteEntity.setName(testSuite.getName());
					testSuiteEntity.setRelease(releaseRepository.findById(testSuite.getReleaseId()));
					testSuiteEntity = testSuiteRepository.save(testSuiteEntity);
				}
				testCaseEntity.setTestSuite(testSuiteEntity);
				testCaseRepository.save(testCaseEntity);
				//testCaseEntityList.add(testCaseEntity);
			}
			//testCaseRepository.save(testCaseEntityList);
		} else {
			    TestSuiteEntity testSuiteEntity = new TestSuiteEntity();
				testSuiteEntity.setName(testSuite.getName());
				testSuiteEntity.setRelease(releaseRepository.findById(testSuite.getReleaseId()));
				testSuiteEntity = testSuiteRepository.save(testSuiteEntity);
		}
	}
	
	@Transactional
	public TestSuite cloneTestSuite(int testSuiteId, int releaseId, String testSuiteName) {
		TestSuiteEntity testSuiteEntity = testSuiteRepository.findOne(testSuiteId);
		ReleaseEntity releaseEntity = releaseRepository.findById(releaseId);
		
		List<TestCaseEntity> testCaseEntityList = new ArrayList<TestCaseEntity>();
		testCaseEntityList.addAll(testSuiteEntity.getTestCases());
		List<TestCaseEntity> cloneTestCaseEntityList = new ArrayList<TestCaseEntity>();
		TestSuiteEntity cloneTestSuiteEntity = new TestSuiteEntity();
		for(TestCaseEntity testCaseEntity:testCaseEntityList) {
			TestCaseEntity cloneTestCaseEntity = new TestCaseEntity();
			cloneTestCaseEntity.setComponent(testCaseEntity.getComponent());
			cloneTestCaseEntity.setDescription(testCaseEntity.getDescription());
			cloneTestCaseEntity.setExecutedBy(testCaseEntity.getExecutedBy());
			cloneTestCaseEntity.setSteps(testCaseEntity.getSteps());
			
			cloneTestSuiteEntity.setName(testSuiteName);
			cloneTestSuiteEntity.setRelease(releaseEntity);
			cloneTestSuiteEntity = testSuiteRepository.save(cloneTestSuiteEntity);
			
			cloneTestCaseEntity.setTestSuite(cloneTestSuiteEntity);
			//cloneTestCaseEntityList.add(cloneTestCaseEntity);
			testCaseRepository.save(cloneTestCaseEntity);
		}
		//testCaseRepository.save(cloneTestCaseEntityList);
		return findTestSuite(cloneTestSuiteEntity.getTestSuiteId());
	}
	
	public TestSuite findTestSuite(int id) {
		TestSuiteEntity testSuiteEntity = testSuiteRepository.findOne(id);
		return mapTestSuite(testSuiteEntity);
	}
	
	public List<TestSuite> getCompleteTestSuiteList() {
		List<TestSuiteEntity> testSuiteEntityList = testSuiteRepository.findAll();
		List<TestSuite> testSuiteList = new ArrayList<TestSuite>();
		for(TestSuiteEntity testSuiteEntity:testSuiteEntityList) {
			testSuiteList.add(mapTestSuite(testSuiteEntity));
		}
		return testSuiteList; 
	}
	
	public void updateTestCase(TestCase testCase) {
		TestCaseEntity testCaseEntity = testCaseRepository.findOne(testCase.getId());
		testCaseEntity = beanMapper.map(testCase, TestCaseEntity.class);
		testCaseRepository.save(testCaseEntity);
	}
	
	public void deleteTestCase(int testCaseId) {
		testCaseRepository.delete(testCaseId);
	}
	
	public List<TestSuite> getTestSuiteForRelease(int releaseId) {
		List<TestSuiteEntity> testSuiteEntityList = testSuiteRepository.findByReleaseId(releaseId);
		List<TestSuite> testSuiteList = new ArrayList<TestSuite>();
		for(TestSuiteEntity testSuiteEntity:testSuiteEntityList) {
			testSuiteList.add(mapTestSuite(testSuiteEntity));
		}
		return testSuiteList;
	}
	
	private TestSuite mapTestSuite(TestSuiteEntity testSuiteEntity) {
		TestSuite testSuite = new TestSuite();
		testSuite.setTestSuiteId(testSuiteEntity.getTestSuiteId());
		testSuite.setName(testSuiteEntity.getName());
		testSuite.setReleaseId(testSuiteEntity.getRelease().getId());
		List<TestCaseEntity> testCaseEntityList = new ArrayList<TestCaseEntity>();
		testCaseEntityList.addAll(testSuiteEntity.getTestCases());
		List<TestCase> testCaseList = beanMapper.map(testCaseEntityList, TestCase.class);
		testSuite.setTestCaseList(testCaseList);
		return testSuite;
	}
	
	@Transactional
	public void deleteTestSuite(int testSuiteId) {
		testSuiteRepository.delete(testSuiteId);
	}
	
}
