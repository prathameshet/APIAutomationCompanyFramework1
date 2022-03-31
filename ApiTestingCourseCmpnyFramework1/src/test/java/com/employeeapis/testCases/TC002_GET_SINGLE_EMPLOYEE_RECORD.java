package com.employeeapis.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapis.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_GET_SINGLE_EMPLOYEE_RECORD extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException{
		logger.info("*************Started TC002_GET_SINGLE_EMPLOYEE_RECORD******************");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	    httpRequest=RestAssured.given();
	    response=httpRequest.request(Method.GET,"/employee/"+empID);
	
	    Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody()
	{
		//logger.info("************** Checking Response Body *********************");
	
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID),true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode =response.getStatusCode();
        Assert.assertEquals(statusCode, 200);	
	}
	
	@Test
	void checkResponseTime()
	{
		long responseTime=response.getTime();
	    Assert.assertTrue(responseTime<2000);
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
	Assert.assertEquals(contentType, "application/json");
}

@Test
void checkserverType() {
	String serverType=response.header("Server");
	Assert.assertEquals(serverType, "nginx");
}



@Test
void checkContentLength() {    
	String contentLength = response.header("Content-Length");
	Assert.assertTrue(Integer.parseInt(contentLength)<1500);
}



@AfterClass
void tearDown() {
	logger.info("*******************  Finished TC001_Get_Single_Employee_Record  ****************");
}

}
