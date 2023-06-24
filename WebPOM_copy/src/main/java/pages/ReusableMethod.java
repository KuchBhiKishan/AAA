package pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import ListenerSetup.ExtentTestManager;
import io.appium.java_client.windows.WindowsDriver;

public class ReusableMethod extends BasePage {

	public String fileSeperator = System.getProperty("file.separator");
	public String NTestData = System.getProperty("user.dir") + fileSeperator + "TestData" + fileSeperator + "NTestData"
			+ fileSeperator;

	// Open Project Dialog.
	@FindBy(xpath = ".//*[@Name='Open Project']")
	public WebElement btn_OpenProject;

	@FindBy(xpath = ".//*[@Name='Open Project ...  Ctrl+O']")
	public WebElement btn_OpenProject_Classic;

	@FindBy(xpath = ".//*[@AutomationId='1843']")
	public WebElement Dropdown_SelectProject_to_open;

	@FindBy(xpath = ".//*[@AutomationId='1']")
	public WebElement btn_OpenProject_OK;

	// Login to IGiS dialog.
	@FindBy(xpath = ".//*[@AutomationId='1643']")
	public WebElement txtBox_username;

	@FindBy(xpath = ".//*[@AutomationId='1596']")
	public WebElement txtBox_password;

	@FindBy(xpath = ".//*[@AutomationId='1167']")
	public WebElement btn_rememberme;

	@FindBy(xpath = ".//*[@AutomationId='1']")
	public WebElement btn_login;

	// Close Project Method Xpaths.
	@FindBy(xpath = ".//*[@Name='File']")
	public WebElement btn_Tab_File;

	@FindBy(xpath = ".//*[@Name='Close Project']")
	public WebElement btn_CloseProject;

	@FindBy(xpath = ".//*[@AutomationId='6']")
	public WebElement btn_SaveChanges_Yes;

	@FindBy(xpath = ".//*[@AutomationId='7']")
	public WebElement btn_SaveChanges_No;

	public ReusableMethod() {
		// this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	public ReusableMethod(WindowsDriver driver1) {
		// this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver1, this);
	}

	/**
	 * Enter Project name as a "String" to open Existing Project in IGiS.
	 * 
	 */
	public void openProject(String projectname) throws InterruptedException {
		Thread.sleep(1500);
		// driver.findElementByXPath(".//*[@Name='File']").click();

		btn_OpenProject.click();
		Thread.sleep(2000);
		Dropdown_SelectProject_to_open.sendKeys(projectname);
		btn_OpenProject_OK.click();
		try {
			txtBox_username.sendKeys();
			txtBox_password.sendKeys();
			btn_rememberme.click();
			btn_login.click();

		} catch (Exception e) {
		}

	}

	 
	
	




	


}

