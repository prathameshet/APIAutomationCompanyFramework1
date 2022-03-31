package com.employeeapis.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapis.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_DELETE_EMPLOYEE_RECORD extends TestBase{

	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException {
		logger.info("**************************  Started TC005_DELETE_EMPLOYEE_RECORD *******************************");

	    RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	    
	    httpRequest=RestAssured.given();
	    
	    response=httpRequest.request(Method.GET,"/employees");
	    
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    
	    String empID=jsonPathEvaluator.get("[0].id");
	  
	    
	    response=httpRequest.request(Method.DELETE,"/delete/"+empID);
	
	    Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody() {
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains("successfully deleted records"), true);
	}

	@Test
	void checkStatusCode()
	{
		int statusCode =response.getStatusCode();
		Assert.assertEquals(statusCode, 200);	
	}

	@Test
	void checkstatusLine() {
		String statusLine=response.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		String contentType=response.header("Content-Type");
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test
	void checkserverType() {
		String serverType=response.header("Server");
		Assert.assertEquals(serverType, "nginx");
	}
	
	void checkcontntEncoding() {		    
		String contentEncoding=response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
	}

@AfterClass
void tearDown() {
	logger.info("*******************  Finished TC005_DELETE_EMPLOYEE_RECORD  ****************");
}

	
}
