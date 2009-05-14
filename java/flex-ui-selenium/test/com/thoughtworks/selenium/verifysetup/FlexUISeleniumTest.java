package com.thoughtworks.selenium.verifysetup;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * This test only run properly if:
 * selenium-server is running
 * selenium-java-client-driver.jar and junit.jar have been added to the project or classpath
 * flashselenium.jar has been added to the project or classpath
 * flashselenium.jar has been added to the project or classpath
 * flex-ui-selenium.jar has been added to the project or classpath
 */
public class FlexUISeleniumTest {
	private final static String URL = "http://www.geocities.com/paulocaroli/flash/sum.html";
	private Selenium selenium;
	private FlexUISelenium flexUI;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore",URL);
		selenium.start();
		selenium.open(URL);
		flexUI = new FlexUISelenium(selenium, "compareSumFlexObjId");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

	@Test
	public void verifyFlexAppSumIsCorrect() {
		flexUI.type("2").at("arg1");
		flexUI.type("3").at("arg2");
		flexUI.click("submit");
		assertEquals("5", flexUI.readFrom("result"));		
	}
	
}
