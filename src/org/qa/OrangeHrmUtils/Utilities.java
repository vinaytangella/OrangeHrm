package org.qa.OrangeHrmUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utilities {
	
	public String generateEmails(){
		String sEmail = "test";
		Date d = new Date();
		sEmail = sEmail+d.getTime()+"@gmail.com";
		System.out.println(sEmail);
		return sEmail;
	}
	
	public String generateUserNames(){
		String sUserName = "test";
		Date d = new Date();
		sUserName = sUserName+d.getTime();
		System.out.println(sUserName);
		return sUserName;
	}
	 
	public Select returnSelectObj(String sLocatorType, String sLocatorValue, WebDriver driver){
		Utilities util = new Utilities();
		Select oSelect = new Select(util.returnWebElementObj(sLocatorType, sLocatorValue, driver));
		return oSelect;
	}
	
	public WebElement returnWebElementObj(String sLocatortype, String sLocatorValue, WebDriver driver){
		
		WebElement oElement=null;
		
		if(sLocatortype.equalsIgnoreCase("id")){
			 oElement = driver.findElement(By.id(sLocatorValue));
		}else if(sLocatortype.equalsIgnoreCase("xpath")){
			oElement = driver.findElement(By.xpath(sLocatorValue));
		}else if(sLocatortype.equalsIgnoreCase("tagname")){
			oElement = driver.findElement(By.tagName(sLocatorValue));
		}else if(sLocatortype.equalsIgnoreCase("linktext")){
			oElement = driver.findElement(By.linkText(sLocatorValue));
		}else if(sLocatortype.equalsIgnoreCase("partiallinktext")){
			oElement = driver.findElement(By.partialLinkText(sLocatorValue));
		}else if (sLocatortype.equalsIgnoreCase("name")) {
			oElement = driver.findElement(By.name(sLocatorValue));
		}else if (sLocatortype.equalsIgnoreCase("classname")) {
			oElement = driver.findElement(By.className(sLocatorValue));
		}else {
			oElement = driver.findElement(By.cssSelector(sLocatorValue));
		}
		
		return oElement;
	}
	
	//load all the property files 
	
	public Properties load_Properties(String sPath){
		
		File oFile_Dir = new File(sPath);
		File[] oFiles = oFile_Dir.listFiles();
		
		for (File file : oFiles) {
			if(file.getAbsolutePath().contains(".properties")){
				
			}else{
				
			}
		}
		return null;
	}

	//This method is to upload any file through windows machine
	
	public void upload_File(String sAutoItApplication, String sWindowName, String sFilePath) throws IOException{
		String sCommand = String.format("%s \"%s\" \"%s\"", sAutoItApplication,sWindowName,sFilePath);
		Runtime.getRuntime().exec(sCommand);
	}
}
