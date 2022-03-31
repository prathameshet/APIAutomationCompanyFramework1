package com.employeeapis.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapis.base.TestBase;
import com.employeeapis.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_PUT_EMPLOYEE_RECORD extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	void updateEmployee() throws InterruptedException 
	{
		logger.info("***************** Started TC004_PUT_EMPLOYEE_RECORD *******************");

		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";

		httpRequest=RestAssured.given();

		JSONObject requestParams=new JSONObject();

		requestParams.put("name",empName);
		requestParams.put("salary",empSalary);
		requestParams.put("age",empAge);

		httpRequest.header("Content-Type","application/json");

		httpRequest.body(requestParams.toJSONString());

		response=httpRequest.request(Method.PUT,"/update/"+empID);

		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody() {
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);	
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
		Assert.assertEquals(contentType, "application/json");
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
	logger.info("*******************  Finished TC004_PUT_EMPLOYEE_RECORD  ****************");
}


}
