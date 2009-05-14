package com.thoughtworks.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UIComponentsTest {
	private final static String URL = "http://localhost/sampleUIcomponents1/sampleUIcomponents1.html";
	private Selenium selenium;
	private FlexUISelenium flexUI;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore",URL);
		selenium.start();
		selenium.open(URL);
		flexUI = new FlexUISelenium(selenium, "sampleUIcomponents1");
		flexUI.waitUntilLoaded();
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
		assertEquals("TextInput content", flexUI.text("TextInput1Id"));		
	}

    /**
     * This is hte mxml code:
     * 	<mx:TextArea x="61" y="275" id="TextAreaId"/>
     */	
	@Test
	public void writeText() {
		assertEquals("", flexUI.text("TextAreaId"));		
		flexUI.type("TextAreaId", "test typed");
		assertEquals("test typed", flexUI.text("TextAreaId"));		
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
		assertFalse(flexUI.text("TextInput1Id").equals(flexUI.text("TextInput2Id")));		
		flexUI.click("ButtonId");
		assertEquals(flexUI.text("TextInput1Id"), flexUI.text("TextInput2Id"));		
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
		assertFalse(flexUI.isChecked(checkBoxId));		
		flexUI.click(checkBoxId);
		assertTrue(flexUI.isChecked(checkBoxId));
	}	
	

}
