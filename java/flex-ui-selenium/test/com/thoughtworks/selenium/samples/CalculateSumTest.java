package com.thoughtworks.selenium.samples;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.Selenium;


/**
 * sample Selenium rC test case running against a felx application
 * The pre-requisites for runnign this testr case are: 
 * selenium-server is running
 * selenium-java-client-driver.jar and junit.jar have been added to the project or classpath
 * flashselenium.jar has been added to the project or classpath
 * flex-ui-selenium.jar has been added to the project or classpath
 */
public class CalculateSumTest {
	private final static String URL = "http://www.geocities.com/paulocaroli/flash/sum.html";
	private Selenium selenium;
	private FlexUISelenium flexUITester;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore",URL);
		selenium.start();
		selenium.open(URL);
		flexUITester = new FlexUISelenium(selenium, "compareSumFlexObjId");
		flexUITester.waitUntilLoaded();
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

	@Test
	public void sumForValidNumbers() {
		flexUITester.type("2").at("arg1");
		flexUITester.type("3").at("arg2");
		flexUITester.click("submit");
		assertEquals("5", flexUITester.readFrom("result"));		

		flexUITester.type("-2").at("arg1");
		flexUITester.type("-3").at("arg2");
		flexUITester.click("submit");
		assertEquals("-5", flexUITester.readFrom("result"));		
	}

	@Test
	public void sumForUnvalidNumbers() {
		flexUITester.type("Not a Number").at("arg1");
		flexUITester.type("3").at("arg2");
		flexUITester.click("submit");
		assertEquals("NaN", flexUITester.readFrom("result"));		
	}
	
}
