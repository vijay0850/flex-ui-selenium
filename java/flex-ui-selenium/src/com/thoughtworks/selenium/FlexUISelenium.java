package com.thoughtworks.selenium;

import com.thoughtworks.selenium.FlashSelenium;

public class FlexUISelenium implements TypeableObject{

	private static final String TRUE = "true";
	private static final int DEFAULT_WAIT_TIME = 5000;
	private FlashSelenium flashApp;
	private String textToTyupe;

	public FlexUISelenium(Selenium selenium, String flashObjectId) {
		this.flashApp = new FlashSelenium(selenium, flashObjectId);
	}

	FlexUISelenium(FlashSelenium flashApp) {
		this.flashApp = flashApp;
	}

	public boolean isVisible(String objectID) {
		return flashApp.call("getFlexVisible", objectID).equals(TRUE);
	}

	public void waitUntilLoaded() throws Exception
	{
	    while (flashApp.PercentLoaded() != 100);
	    {
	      Thread.sleep(100);
	    }
    }

	public void waitUntilVisible(String objectID) throws Exception
	{
		this.waitUntilVisible(objectID, DEFAULT_WAIT_TIME);
	}

	public void waitUntilVisible(String objectID, int timeout) throws Exception
	{
		while(timeout > 0 && ! this.isVisible(objectID)) {
			Thread.sleep(1000);
			timeout--;
		}
		if(timeout == 0){
			throw new Exception("The Flex object:" + objectID + " was not visible. Time spent waiting:" + timeout);
		}
	}

	public void wait(int timeout) throws Exception
	{
		while(timeout > 0) {
			Thread.sleep(1000);
			timeout--;
		}
	}

	public TypeableObject type(String text) {
		this.textToTyupe = text;
		return (TypeableObject) this;
	}

	public void at(String objectId) {
		flashApp.call("doFlexType", objectId, textToTyupe);
	}

	public void click(String clickableObjId) {
		flashApp.call("doFlexClick", clickableObjId, "");
	}

	public String readFrom(String objId) {
		return flashApp.call("getFlexText", objId, "");
	}

	public boolean isChecked(String checkBoxId) {
		return Boolean.parseBoolean(flashApp.call("getFlexCheckBoxChecked", checkBoxId, ""));
	}

	public void doubleClick(String objId) {
		flashApp.call("doFlexDoubleClick", objId, "");
	}





}
