package com.att.testbank.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "test_suite")
public class TestSuiteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEST_SUITE_ID")
	private int testSuiteId;
	
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(mappedBy = "testSuite", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
	private Set<TestCaseEntity> testCases;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RLS_ID")
	private ReleaseEntity release;


	public int getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(int testSuiteId) {
		this.testSuiteId = testSuiteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TestCaseEntity> getTestCases() {
		return testCases;
	}

	public void setTestCases(Set<TestCaseEntity> testCases) {
		this.testCases = testCases;
	}

	public ReleaseEntity getRelease() {
		return release;
	}

	public void setRelease(ReleaseEntity release) {
		this.release = release;
	}
	
}
