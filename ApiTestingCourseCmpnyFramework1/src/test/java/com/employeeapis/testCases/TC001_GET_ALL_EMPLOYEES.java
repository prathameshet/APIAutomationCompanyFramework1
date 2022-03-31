package com.employeeapis.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapis.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GET_ALL_EMPLOYEES extends TestBase{
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException{
		logger.info("*************Started TC001_GET_ALL_EMPLOYEES******************");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	    httpRequest=RestAssured.given();
	    response=httpRequest.request(Method.GET,"/employees");
	
	    Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("************** Checking Response Body *********************");
	
		String responseBody=response.getBody().asString();
		logger.info("Response Body ===>" + responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("************ Checking status Code *******************");
	 
		int statusCode =response.getStatusCode();
        logger.info("Status code is ===>"+statusCode);
	    Assert.assertEquals(statusCode, 200);	
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("***************** Checking Response Time ****************");
		long responseTime=response.getTime();
		logger.info("Response time is ===>"+responseTime);
		
		if(responseTime>2000)
			logger.warn("Response time is greater than 2000");
	
	    Assert.assertTrue(responseTime<5000);
	}
	
	@Test
	void checkstatusLine() {
		logger.info("*****************Checking status****************** line");
	    
		String statusLine=response.getStatusLine();
		logger.info("Status line is ==>"+statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
	}
	
@Test
void checkContentType()
{
	logger.info("******************* Checking Content Type ********************");
    
	String contentType=response.header("Content-Type");
	logger.info("Content type is ====>"+contentType);
	Assert.assertEquals(contentType, "application/json");
}

@Test
void checkserverType() {
	
logger.info("******************* Checking Server Type ********************");
    
	String serverType=response.header("Server");
	logger.info("Server type is ====>"+serverType);
	Assert.assertEquals(serverType, "nginx");
}

@Test
void checkcontntEncoding() {
logger.info("******************* Checking Content Encoding ********************");
    
	String contentEncoding=response.header("Content-Encoding");
	logger.info("Content Encoding is ====>"+contentEncoding);
	Assert.assertEquals(contentEncoding, "gzip");
}

@Test
void checkContentLength() {
	
	logger.info("************* Checking Content Length    *****************");
    
	String contentLength = response.header("Content-Length");
	logger.info("Content Length is ===>"+contentLength);
    
	if(Integer.parseInt(contentLength)<100) {
		       logger.warn("Content Length is less than 100");
	}
	
	Assert.assertTrue(Integer.parseInt(contentLength)>100);
}

@Test
void checkCookies() {
   
	logger.info("******************* Checking Cookies ******************");
   
	String cookie= response.getCookie("PHPSESSID");
}

@AfterClass
void tearDown() {
	logger.info("*******************  Finished TC001_Get_All_Employees  ****************");
}

}
