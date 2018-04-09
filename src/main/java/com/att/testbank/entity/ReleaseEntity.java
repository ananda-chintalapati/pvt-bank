package com.att.testbank.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rls_info")
public class ReleaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RLS_ID")
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "RLS_DESC")
	private String description;
	
	@OneToMany(mappedBy = "release", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<TestSuiteEntity> testSuite;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRelease() {
		return name;
	}

	public void setRelease(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<TestSuiteEntity> getTestSuite() {
		return testSuite;
	}

	public void setTestSuite(Set<TestSuiteEntity> testSuite) {
		this.testSuite = testSuite;
	}
	
	
}
