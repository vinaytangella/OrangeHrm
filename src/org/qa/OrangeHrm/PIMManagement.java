package org.qa.OrangeHrm;

import java.io.IOException;

import org.qa.OrangeHrmUtils.Utilities;
import org.testng.annotations.Test;

public class PIMManagement {
	
	Utilities util = new Utilities();
	
	@Test(priority=5)
	public void add_Employee() throws IOException{
		
		String sFilePath=System.getProperty("user.dir");
		util.returnWebElementObj("id", "menu_pim_viewPimModule", Base.driver).click();
		util.returnWebElementObj("id", "menu_pim_addEmployee", Base.driver).click();
		util.returnWebElementObj("cssselector", "input#firstName", Base.driver).sendKeys("");
		util.returnWebElementObj("cssselector", "input#middleName", Base.driver).sendKeys("");
		util.returnWebElementObj("cssselector", "input#lastName", Base.driver).sendKeys("");
		util.returnWebElementObj("cssselector", "input#photofile", Base.driver).click();
		//This is the code to upload the photo of the employer through autoit script
		util.upload_File(sFilePath+"\\autoITStuff\\fileupload.exe", "Open", sFilePath+"\\data\\pic1.jpg");
		
	}

	
}
