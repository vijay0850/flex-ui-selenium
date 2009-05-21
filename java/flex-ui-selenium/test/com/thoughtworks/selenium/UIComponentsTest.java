package com.thoughtworks.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The tests run against a Flex application with the following code:
 * 

   <?xml version="1.0" encoding="utf-8"?>
    <mx:Application xmlns:mx="http://www.adobe.com/2006/mxml" layout="absolute">
	<mx:CheckBox x="90" y="39" id="CheckboxId" label="Checkbox"/>
	<mx:TextInput x="61" y="113" id="TextInput1Id" text="TextInput content" />
	<mx:TextInput x="61" y="193" id="TextInput2Id" />
	<mx:Button x="82" y="154" id="ButtonId" label="Copy to TextInput2" 
		click="TextInput2Id.text=TextInput1Id.text"
		/>
	<mx:TextArea x="61" y="275" id="TextAreaId"/>
	
   </mx:Application>
   
   IMPORTANT: the application is built with FlexSEleniumAPI.swc
 */	
public class UIComponentsTest {
	private final static String URL = "http://localhost/sampleUIcomponents1/sampleUIcomponents1.html";
	private Selenium selenium;
	private FlexUISelenium testerDude;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore",URL);
		selenium.start();
		selenium.open(URL);
		testerDude = new FlexUISelenium(selenium, "sampleUIcomponents1");
		testerDude.waitUntilLoaded();
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

    /**
     * This is hte mxml code:
     * 		<mx:TextInput x="61" y="113" id="TextInput1Id" text="TextInput content" />
     */	
	@Test
	public void readText() {
		assertEquals("TextInput content", testerDude.readFrom("TextInput1Id"));		
	}

    /**
     * This is hte mxml code:
     * 	<mx:TextArea x="61" y="275" id="TextAreaId"/>
     */	
	@Test
	public void writeText() {
		assertEquals("", testerDude.readFrom("TextAreaId"));		
		testerDude.type("test typed").at("TextAreaId");
		assertEquals("test typed", testerDude.readFrom("TextAreaId"));		
	}

    /**
     * This is hte mxml code:
     * 	<mx:TextInput x="61" y="113" id="TextInput1Id" text="TextInput content" />
	 *  <mx:TextInput x="61" y="193" id="TextInput2Id" />
	 *   <mx:Button x="82" y="154" id="ButtonId" label="Copy to TextInput2" 
	 *   	click="TextInput2Id.text=TextInput1Id.text"
     */
	@Test
	public void clickOnButton() {
		assertFalse(testerDude.readFrom("TextInput1Id").equals(testerDude.readFrom("TextInput2Id")));		
		testerDude.click("ButtonId");
		assertEquals(testerDude.readFrom("TextInput1Id"), testerDude.readFrom("TextInput2Id"));		
	}
	
    /**
     * This is hte mxml code:
     * 	<mx:CheckBox x="90" y="39" id="CheckboxId" label="Checkbox"/>
     */	
	@Test
	public void clickOnCheckbox() {
		notClickedClickClicked("CheckboxId");		
	}

    /**
     * This is hte mxml code:
     * 	<mx:CheckBox x="90" y="39" id="CheckboxId" label="Checkbox"/>
     */	
	@Test
	public void isCheckboxChecked() {
		notClickedClickClicked("CheckboxId");		
	}
	
	private void notClickedClickClicked(String checkBoxId) {
		assertFalse(testerDude.isChecked(checkBoxId));		
		testerDude.click(checkBoxId);
		assertTrue(testerDude.isChecked(checkBoxId));
	}	
	

}
