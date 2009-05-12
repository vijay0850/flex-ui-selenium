package com.thoughtworks.selenium.samples;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.Selenium;


public class CalculateSumTest {
	private final static String URL = "http://www.geocities.com/paulocaroli/flash/sum.html";
	private Selenium selenium;
	private FlexUISelenium flexUI;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore",URL);
		selenium.start();
		selenium.open(URL);
		flexUI = new FlexUISelenium(selenium, "compareSumFlexObjId");
		flexUI.waitUntilLoaded();
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

	@Test
	public void sumForValidNumbers() {
		flexUI.type("arg1", "2");
		flexUI.type("arg2", "3");
		flexUI.click("submit");
		assertEquals("5", flexUI.text("result"));		

		flexUI.type("arg1", "-2");
		flexUI.type("arg2", "-3");
		flexUI.click("submit");
		assertEquals("-5", flexUI.text("result"));		
	}

	@Test
	public void sumForUnvalidNumbers() {
		flexUI.type("arg1", "Not a Number");
		flexUI.type("arg2", "3");
		flexUI.click("submit");
		assertEquals("NaN", flexUI.text("result"));		
	}
	
}
