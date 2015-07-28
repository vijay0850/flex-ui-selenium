## FlexUISelenium ##

FlexUISelenium is an extension to the Selenium RC client driver that enables the Selenium RC client drivers to interact (and test) the Flex UI components and methods of the Flex application.

## Sample test code using FlexUISelenium ##

Below is a sample test code using FlexUISelenium .
```
@Test
@Test
public void sumForValidNumbers() {
	flexUITester.type("2").at("arg1");
	flexUITester.type("3").at("arg2");
	flexUITester.click("submit");
	assertEquals("5", flexUITester.readFrom("result"));			
}	
```

## Try out FlexUISelenium ##

The following steps describe how to install and use FlexUISelenium  in a few simple steps.



&lt;OL&gt;


> 

&lt;LI&gt;

Download and install Selenium RC
> 

&lt;LI&gt;

Download Selenium Flex API, and rebuild your Flex application with SeleniumFlexAPI.swc
> 

&lt;LI&gt;

Download FlashSelenium and add to your test project
> 

&lt;LI&gt;

Download FlexUISelenium  and add to your test project
> 

&lt;LI&gt;

Write and run your test cases against your Flex applications



The java client is used in this example, but the other Selenium RC drivers (.Net, Ruby, Phyton and PHP) will also work for driving your tests against the Flex UI.

### 1. Download and install Selenium RC ###

Download the latest version of Selenium RC here.
For more information on Selenium RC, please refer to the Selenium RC website.
After understanding how Selenium RC works, create a test project, add and execute the following Selenium RC sample java client test code:
The sample test code below makes one call to the website application: `selenium.getTitle());`. The passing test validates that Selenium RC is working for your project.

Note: selenium-java-client-driver.jar must be added to the  test project.

```
import static org.junit.Assert.*;
import org.junit.*;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SeleniumRCTest {
	private final static String BASE_URL = "http://www.geocities.com/";
	private final static String PAGE = "paulocaroli/flash/sum.html";
	private Selenium selenium;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore",BASE_URL);
		selenium.start();
		selenium.open(PAGE );
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

	@Test
	public void verifyPageTitle() {
		assertEquals("Flex UI Selenium sample page", selenium.getTitle());
	}

}
```

### 2. Download Selenium Flex API, and rebuild your Flex application with SeleniumFlexAPI.swc ###

Download the Selenium Flex API zip file [here](http://sourceforge.net/project/showfiles.php?group_id=228427).
For more information on Selenium Flex API, please refer to [the Selenium Flex API website](http://sourceforge.net/projects/seleniumflexapi/).
Extract the SeleniumFlexAPI.swc.
Build your Flex application including the SeleniumFlexAPI.swc library.

In FlexBuilder, add the SeleniumFlexAPI.swc in the <i>/src</i> folder, then build your application with <i>-include-libraries SeleniumFlexAPI.swc</i> as the additional compiler arguments

The [sample Flex application](http://www.geocities.com/paulocaroli/flash/sum.html) has been build with the SeleniumFlexAPI.swc library. Below is the sample flex application source code.
```
<?xml version="1.0"?>
<!-- usingas/SourceInclude.mxml -->
<mx:Application xmlns:mx="http://www.adobe.com/2006/mxml" layout="absolute" creationComplete="init();">
	<mx:Script>
 	<![CDATA[ 
		private function computeSum(a:Number, b:Number):Number {
			return a + b;
		}
	]]>
	</mx:Script>

    <mx:TextInput id="arg1" text="3" width="40" x="170" y="24" textAlign="right"/>
    <mx:TextInput id="arg2" text="3" width="40" x="170" y="52" textAlign="right"/>

    <mx:TextArea id="result" height="25" width="78" x="132" y="82" textAlign="right"/>

    <mx:Button id="submit" label="Compute Sum" 
        click="result.text=String(computeSum(Number(arg1.text), Number(arg2.text)));" 
        x="105" 
        y="115"
    />

    <mx:Label x="148" y="52" text="+" fontWeight="bold" fontSize="17" width="23"/>
</mx:Application>
```
### 3. Download FlashSelenium and add to your test project ###
Download the latest version of FlashSelenium [here](http://code.google.com/p/flash-selenium/downloads/list).
For more information on FlashSelenium, please refer to [the FlashSelenium website](http://code.google.com/p/flash-selenium/).
The [Writing and running functional tests for Flash with Selenium RC](http://www.adobe.com/devnet/flash/articles/flash_selenium.html)  article has more information on FlashSelenium.

The sample test code below makes one call to the flash application: `flashApp.PercentLoaded());`. The passing test validates that FlashSelenium is working for your project.

Note:  flashselenium.jar must be added to the  test project.

```
import static org.junit.Assert.*;
import org.junit.*;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlashSelenium;
import com.thoughtworks.selenium.Selenium;

public class FlashSeleniumtest {
	private final static String BASE_URL = "http://www.geocities.com/";
	private final static String PAGE = "paulocaroli/flash/sum.html";
	private Selenium selenium;
	private FlashSelenium flashApp;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore",BASE_URL);
		selenium.start();
		flashApp = new FlashSelenium(selenium, "compareSumFlexObjId");		
		selenium.open(PAGE);
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

	@Test
	public void verifyFlashMovieIsLoaded() {
		assertEquals(100, flashApp.PercentLoaded());
	}

}

```

### 4. Download FlexUISelenium  and add to your test project ###

Write and run your test cases against the Flex UI components of your Flex application build with SeleniumFlexAPI.swc

Download the latest version of FlexUISelenium  [here](http://code.google.com/p/flex-ui-selenium/downloads/list).
The sample test code below makes the following calls to the Flex UI application:
```
flexUI.type("arg1", "2");
flexUI.type("arg2", "3");
flexUI.click("submit");
flexUI.text("result"));	
```

The passing test validates that FlexUISelenium is working for your project.

Note:  flex-ui-selenium.jar must be added to the  test project.

```
import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.Selenium;

public class FlexUISeleniumTest {
	private final static String BASE_URL = "http://www.geocities.com/";
	private final static String PAGE = "paulocaroli/flash/sum.html";
	private Selenium selenium;
	private FlexUISelenium flexUITester;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore",BASE_URL);
		selenium.start();
		selenium.open(PAGE);
		flexUITester = new FlexUISelenium(selenium, "compareSumFlexObjId");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

	@Test
	public void verifyFlexAppSumIsCorrect() {
		flexUITester.type("2").at("arg1");
		flexUITester.type("3").at("arg2");
		flexUITester.click("submit");
		assertEquals("5", flexUITester.readFrom("result"));		
	}
	
}
```


### 5. Write and run your test cases against your Flex applications ###

This step is with you. Below you can find the test code against Calculate Sum, the sample application  used to describe the basics of FlexUISelenium. Don't forget to build with your Flex application (to go under test) with SeleniumFlexAPI.swc.

Check out the sample Flex-base calculateSum application [here](http://www.geocities.com/paulocaroli/flash/sum.html).

The Flex code for calculateSum.swf is [here](http://code.google.com/p/flex-ui-selenium/source/browse/trunk/flex/compareSum/src/compareSum.mxml).

The JUnit java test code for CalculateSumTest is [here](http://code.google.com/p/flex-ui-selenium/source/browse/trunk/java/flex-ui-selenium/test/com/thoughtworks/selenium/samples/CalculateSumTest.java).


## Existing proven test frameworks ##

FlexUISelenium  uses Selenium RC, FlashSelenium and Selenium Flex API. FlexUISelenium  uses FlashSelenium for enabling the communication of Selenium RC client with the Flash application (swf). And Selenium Flex API exposes all the UI components of the Flex application which are built with SeleniumFlexAPI.swc.

[Selenium RC](http://wiki.openqa.org/display/SRC/Home) is a server that allows you to launch browser sessions and run Selenium tests in those browsers. Conceptually, the server exposes two main interfaces to the outside. One is for controlling the browser, the other is to receive commands that instruct it what to do.

[FlashSelenium](http://code.google.com/p/flash-selenium/) is an extension to the Selenium RC client driver that enables the Selenium RC client test drivers to call ActionScript methods of the Flex application. FlashSelenium relies in the developer to manually expose the Flex application specific methods and component.

[Selenium Flex API](http://sourceforge.net/projects/seleniumflexapi/) is an extension to Selenium IDE and a mechanism that automatically exposes the Flex application UI components.


#### All fits together nicely! ####

FlexUISelenium is the component dispatching flex test commands against the selenium server. Such commands are able to directly invoke the Flex Application UI components, because they have been exposed by SeleniumFlexAPI.swc.

FlexUISelenium is executed within the Selenium RC client test programs. The test programs are written in some binding language, and executed by a unit tst framework; for example a test program in Java executed with JUnit.

