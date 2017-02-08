package org.qa.OrangeHrm;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.qa.OrangeHrmUtils.Utilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class Base {
	static WebDriver driver;
	static Properties p;
	static Logger log;
	
	Utilities util = new Utilities();
	
	@BeforeSuite
	@Parameters({"sUrl","sBrowserName"})
	public void setUp(String sUrl, String sBrowserName) throws InterruptedException, IOException{
		
		String sPath,sProperties_Path;
		DesiredCapabilities dc;
		sProperties_Path = System.getProperty("user.dir")+"//locators//LoginScreen.properties";
		log=Logger.getLogger("Selenium log");
		log.info("All the properties are being loaded from the path"+sProperties_Path);
		
		p = new Properties();
		FileInputStream fis = new FileInputStream(sProperties_Path);
		p.load(fis);
		log.info("File Input Stream has been created and all the properties have been loaded");
		
		switch(sBrowserName.toLowerCase()){
		
		case "chrome":
			sPath = System.getProperty("user.dir")+"//drivers//chromedriver.exe";
			log.info("The chrome driver executable is being set from the path: "+sPath);
			System.setProperty("webdriver.chrome.driver", sPath);
			driver = new ChromeDriver();
			log.info("WebDriver has been instantiated to Chrome browser");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.navigate().to(sUrl);
			driver.manage().window().maximize();
			break;
		
		case "firefox":
			sPath = System.getProperty("user.dir")+"//drivers//geckodriver.exe";
			dc  = DesiredCapabilities.firefox();
			dc.setCapability("marionette", true);
			System.setProperty("webdriver.gecko.driver", sPath);
			driver = new FirefoxDriver(dc);
			log.info("WebDriver has been instantiated to Firefox browser");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.navigate().to(sUrl);
			driver.manage().window().maximize();
			break;
			
		case "ie":
			sPath = System.getProperty("user.dir")+"//drivers//IEDriverServer.exe";
			dc  = DesiredCapabilities.internetExplorer();
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			dc.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "ignore");
			System.setProperty("webdriver.ie.driver", sPath);
			driver = new InternetExplorerDriver(dc);
			log.info("WebDriver has been instantiated to Internet browser");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.navigate().to(sUrl);
			Thread.sleep(5000);
			driver.switchTo().alert().dismiss();
			break;
		}
	}
	
	@Test(priority=6)
	public void test1(){
		System.out.println("This is test");
		Utilities uti = new Utilities();
		uti.generateEmails();
	}
	
	@Test(priority=1)
	@Parameters({"sUsername", "sPassword"})
	public void login(String sUsername, String sPassword){
		
		util.returnWebElementObj("xpath", p.getProperty("username"), driver).sendKeys(sUsername);
		driver.findElement(By.xpath(p.getProperty("password"))).sendKeys(sPassword);
		driver.findElement(By.xpath(p.getProperty("singnInBtn"))).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='welcome']")).isDisplayed(), "The User is successfully Logged In");
	}
	
	@Test(priority=4)
	public void training_Test() throws InterruptedException{
		driver.findElement(By.xpath("//*[@id='welcome']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='welcome-menu']/descendant::a[@href='/core/trainingSSO']")).click();
		Set<String> sWhandles = driver.getWindowHandles();
		Iterator<String> ite = sWhandles.iterator();
		String sParentHandle = ite.next();
		String sChildHandle = ite.next();
		driver.switchTo().window(sChildHandle);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@href='index.php']")).isDisplayed(), "The control is in new window");
		driver.findElement(By.xpath("//*[@href='index.php']")).click();
		Thread.sleep(4000);
		driver.close();
		driver.switchTo().window(sParentHandle);
	}
	
	@Test(priority=7)
	public void logout() throws InterruptedException{
		driver.findElement(By.xpath("//*[@id='welcome']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='welcome-menu']/descendant::a[@href='/auth/logout']")).click();
	}
			
	@AfterSuite
	public void tearDown(){
		driver.close();
		driver.quit();
	}
}