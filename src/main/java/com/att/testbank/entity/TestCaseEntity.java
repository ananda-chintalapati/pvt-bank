package com.att.testbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "test_case")
public class TestCaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "COMPONENT")
	private String component;
	
	@Column(name = "STEPS")
	private String steps;
	
	@Column(name = "EXECUTED_BY")
	private String executedBy;
	
	@Column(name = "EXECUTION_DT")
	private String timestamp;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "RESULT")
	private String result;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEST_SUITE_ID")
	private TestSuiteEntity testSuite;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(String executedBy) {
		this.executedBy = executedBy;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public TestSuiteEntity getTestSuite() {
		return testSuite;
	}

	public void setTestSuite(TestSuiteEntity testSuite) {
		this.testSuite = testSuite;
	}
	
}
