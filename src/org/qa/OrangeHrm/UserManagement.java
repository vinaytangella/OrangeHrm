package org.qa.OrangeHrm;

import org.qa.OrangeHrmUtils.Utilities;
import org.testng.annotations.Test;

public class UserManagement {
	
	@Test(priority=2)
	public void createUser() throws InterruptedException{
		
		Utilities util = new Utilities();
		util.returnWebElementObj("xpath", "//*[@id='menu_admin_viewAdminModule']",Base.driver).click();
		util.returnWebElementObj("xpath", "//*[@id='btnAdd']",Base.driver).click();
		util.returnSelectObj("xpath","//*[@id='systemUser_essRole']", Base.driver).selectByValue("2");
		util.returnSelectObj("xpath","//*[@id='systemUser_supervisorRole']", Base.driver).selectByValue("3");
		util.returnSelectObj("xpath","//*[@id='systemUser_adminRole']", Base.driver).selectByValue("1");
		util.returnWebElementObj("id", "systemUser_employeeName_empName",Base.driver).sendKeys(util.generateUserNames());
		util.returnWebElementObj("id", "systemUser_userName",Base.driver).sendKeys(util.generateUserNames());
		util.returnSelectObj("id","systemUser_status", Base.driver).selectByValue("1");
		util.returnWebElementObj("id", "systemUser_password",Base.driver).sendKeys(util.generateUserNames());
		util.returnWebElementObj("id", "systemUser_confirmPassword",Base.driver).sendKeys(util.generateUserNames());
	}
}
