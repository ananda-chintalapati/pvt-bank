package com.att.testbank.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.att.testbank.model.Release;
import com.att.testbank.model.TestCase;
import com.att.testbank.model.TestSuite;
import com.att.testbank.service.ReleaseServiceHandler;
import com.att.testbank.service.TestSuiteServiceHandler;

@Service
@Path("/")
public class TestBankResource {

	@Autowired
	TestSuiteServiceHandler testSuiteService;
	
	@Autowired
	ReleaseServiceHandler releaseService;
	
	@POST
	@Transactional
	@Path("/CreateRelease")
	public Response createRelease(Release release) {
		releaseService.createRelease(release);
		return Response.status(Status.CREATED).build();
	}
	
	@POST
	@Transactional
	@Path("/TestSuite/{releaseId}/{testSuiteName}")
	public Response createTestSuite(@PathParam("releaseId") int releaseId, @PathParam("testSuiteName") String testSuiteName) {
		TestSuite testSuite = new TestSuite();
		testSuite.setName(testSuiteName);
		testSuite.setReleaseId(releaseId);
		testSuiteService.createTestSuite(testSuite);
		return Response.status(Status.CREATED).build();
	}
	
	@POST
	@Transactional
	@Path("/TestSuite")
	public Response createTestSuite(TestSuite testSuite) {
		testSuiteService.createTestSuite(testSuite);
		return Response.status(Status.CREATED).build();
	}
	
		
	
	@DELETE
	@Transactional
	@Path("/TestSuite/{testSuiteId}")
	public void deleteTestSuite(@PathParam("testSuiteId") int testSuiteId) {
		testSuiteService.deleteTestSuite(testSuiteId);
	}
	
	@GET
	@Transactional
	@Path("/TestSuite")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<TestSuite> getAllTestSuite() {
		return testSuiteService.getCompleteTestSuiteList();
	}
	
	@GET
	@Transactional
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/TestSuite/release/{releaseId}")
	public List<TestSuite> getTestSuiteForRelease(@PathParam("releaseId") int releaseId) {
		return testSuiteService.getTestSuiteForRelease(releaseId);
	}
	
	@GET
	@Transactional
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/TestSuite/{id}")
	public TestSuite getTestSuite(@PathParam("id") int id) {
		return testSuiteService.findTestSuite(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/Release")
	public List<Release> getCompleteReleaseList() {
		return releaseService.findAllReleases();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/Release/search/{key}")
	public List<Release> getMatchingReleaseList(@PathParam("key") String key) {
		return releaseService.findMatchingRelease(key);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/Release/{id}")
	public Release getMatchingReleaseList(@PathParam("id") int id) {
		return releaseService.findRelease(id);
	}
	
	@POST
	@Path("/TestSuite/fromTS/{testSuiteId}/release/{releaseId}/name/{testSuiteName}")
	public TestSuite cloneTestSuite(@PathParam("testSuiteId") int testSuiteId, 
			@PathParam("releaseId") int releaseId, @PathParam("testSuiteName") String testSuiteName) {
		return testSuiteService.cloneTestSuite(testSuiteId, releaseId, testSuiteName);
	}
	
	@DELETE
	@Path("/TestCase/{testCaseId}")
	public void deleteTestCase(@PathParam("testCaseId") int testCaseId) {
		testSuiteService.deleteTestCase(testCaseId);
	}
}
