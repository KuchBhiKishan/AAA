package pages;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ListenerSetup.ExtentTestManager;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static WindowsDriver driver = null; // define driver for main application
	public static WindowsDriver<WindowsElement> driver1 = null; // define driver for sub application
	public static WindowsDriver<WindowsElement> driver2 = null; // define driver for sub application
	public static String currentwindow; // variable for child or parent window switch
	public static ITestContext context; // Define ITestContext for
	public WebDriver webDriver;

	/**
	 * Set the context to take a screenshot if the test passes or fails.
	 * 
	 * @param context
	 */
	public void SetContext(ITestContext context) {
		this.context = context;
	}

	/**
	 * Use this methods for switch to main driver from the sub driver.
	 * 
	 */
	public static void SwitchToMainDriver() {
		context.setAttribute("WebDriver", driver);

	}

	/**
	 * Create session for windowPc
	 * 
	 */
	public static WindowsDriver<WindowsElement> GetDesktopSession() throws MalformedURLException {
		// Get Desktop Session
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "Root");
		cap.setCapability("deviceName", "WindowsPC");

		WindowsDriver<WindowsElement> session = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723"),
				cap);
		return session;
	}

	public static void Driver2(String windowTitle) throws MalformedURLException {
		// WindowsDriver<WindowsElement>
		WindowsDriver<WindowsElement> desktopSession = GetDesktopSession();
		WindowsElement modal = desktopSession.findElementByName(windowTitle);
		String adasModalWindowHandle1 = modal.getAttribute("NativeWindowHandle");
		int winHandleInt = Integer.parseInt(adasModalWindowHandle1);
		String adasModalWindowHandle = Integer.toHexString(winHandleInt);
		// adasModalWindowHandle = (int.Parse(adasModalWindowHandle)).ToString("x");
		DesiredCapabilities cap = new DesiredCapabilities();
		// var appModalCapabilities = new AppiumOptions();
		cap.setCapability("appTopLevelWindow", adasModalWindowHandle);
		// return new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723"),
		// cap);
		driver2 = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723"), cap);
		context.setAttribute("WebDriver", driver2);
	}

	/**
	 * Get window Handle of any application by using window title.
	 * 
	 * @param windowTitle Give window title in string format.
	 * 
	 */
	public static void Driver1(String windowTitle) throws MalformedURLException {
		WindowsDriver<WindowsElement> desktopSession = GetDesktopSession();
		WindowsElement modal = desktopSession.findElementByName(windowTitle);
		String adasModalWindowHandle1 = modal.getAttribute("NativeWindowHandle");
		int winHandleInt = Integer.parseInt(adasModalWindowHandle1);
		String adasModalWindowHandle = Integer.toHexString(winHandleInt);
		// adasModalWindowHandle = (int.Parse(adasModalWindowHandle)).ToString("x");
		DesiredCapabilities cap = new DesiredCapabilities();
		// var appModalCapabilities = new AppiumOptions();
		cap.setCapability("appTopLevelWindow", adasModalWindowHandle);
		driver1 = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723"), cap);
		context.setAttribute("WebDriver", driver1);
	}

	/**
	 * For Launch IGiS app
	 * @throws InterruptedException 
	 * 
	 */
	public static void LaunchApp() throws InterruptedException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Program Files\\IGIS\\IGIS-v11\\SGL_IGIS.exe");
		cap.setCapability("platformName", "Windows");
		cap.setCapability("deviceName", "WindowsPC");
		Thread.sleep(2000);
		try {
			driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		context.setAttribute("WebDriver", driver);
	}

	/**
	 * For Switch currentWindow to childWindow
	 * 
	 */
	public static void SwitchToChildWindow() {
		currentwindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> i = allWindows.iterator();

		String childwindow = i.next();
		driver.switchTo().window(childwindow);

	}

	/**
	 * For Switch childWindow to currentWindow
	 * 
	 */
	public static void SwitchToParentWindow() {
		driver.switchTo().window(currentwindow);

	}

	/**
	 * Verify file is present or not on particular directory.
	 * 
	 * @param filePath Give filePath in string format.
	 * @return true if file is preset else reurn false
	 * 
	 */
	public boolean VerifyFileIsPeresent(String filePath) {
		File tempFile = new File(filePath);
		return tempFile.exists();
	}

	/**
	 * Select value from the split button
	 * 
	 * @param element     Give element of the split button.
	 * @param indexNumber Give index in int format.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void SelectFromtheSplitButton(WebElement element, int indexNumber) throws InterruptedException {

		element.click();
		Thread.sleep(1000);

		for (int i = 0; i < indexNumber; i++) {

			element.sendKeys(Keys.DOWN);
		}
		element.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}

	/**
	 * verify that element is present or not.
	 * 
	 * @param element   Give element which you want to verify.
	 * @param delaytime Give delaytime in minites.
	 * @throws InterruptedException
	 * 
	 */
	public boolean isElementPresent(WebElement element, long delaytime) throws InterruptedException {
		long time = delaytime * 60000;
		boolean b = false;
		do {

			b = waitCondition(element, time);
			time = time - 3000;
		} while (time > 0 && b == false);
		return b;
	}

	/**
	 * verify that element is present or not.
	 * 
	 * @param element   Give element which you want to verify.
	 * @param delaytime Give delaytime in minites.
	 * @throws InterruptedException
	 * 
	 */
	public boolean isElementNotPresent(WebElement element, long delaytime) throws InterruptedException {
		long time = delaytime * 60000;
		boolean b = false;
		do {

			b = waitCondition(element, time);
			time = time - 3000;
		} while (time > 0 && b == true);
		if (b == false)
			return true;
		else
			return false;
	}

	private boolean waitCondition(WebElement element, long delaytime) throws InterruptedException {
		try {
			element.isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {

			Thread.sleep(3000);
			return false;
		}
	}


	/**
	 
=======

	/**
>>>>>>> branch 'master' of https://bitbucket.sgligis.com:8080/scm/da/automation.git
	 * verify that elements are present or not.
	 * 
	 * @param elements Give lost of  elements which you want to verify.
	 * @throws InterruptedException
	 * 
	 */
	public boolean VerifyElementsIsDisplay(WebElement[] elements) throws InterruptedException {
		boolean b = true;
		for (int i = 0; i < elements.length; i++) {
			try {
				elements[i].isDisplayed();
			} catch (org.openqa.selenium.NoSuchElementException e) {
				Thread.sleep(2000);
				try {
					AddResult(0, elements[i].toString() + "Element not found");
				} catch (Exception e1) {
				}
				b = false;
			}

		}
		return b;
	}

	/**
	 * Get list of drop down.
	 * 
	 * @param element     Give element of the drop down.
	 * @return list of drop down value in string format.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public List<String> GetListOfDropdownList(WebElement element) throws InterruptedException {
		List<String> dropDownListValues = new ArrayList<String>();
		Thread.sleep(1000);

		for (int i = 0; i < 100; i++) {
			dropDownListValues.add(i, element.getText());
			if (i >= 1) {
				if (dropDownListValues.get(i - 1).equals(dropDownListValues.get(i))) {
					break;
				} else {
					element.sendKeys(Keys.DOWN);
				}
			} else {
				element.sendKeys(Keys.DOWN);
			}
		}
		Thread.sleep(2000);
		return dropDownListValues;
	}

	/**
	 * Delete all file from the particular directory.
	 * 
	 * @param filePath Give folder path in string format
	 * 
	 */
	public void DeleteAllFile(String filePath) {
		File directory = new File(filePath);
		for (File file : Objects.requireNonNull(directory.listFiles())) {
			if (!file.isDirectory()) {
				file.delete();
			}
		}

	}

	/**
	 * Delete file by partial name of file and file format.
	 * 
	 * @param filePath Give files Path in string format.
	 * @param give     partial or full name of file.
	 * @param give     file format(Ex. ".png" , ".pdf" ext...)
	 * 
	 */
	public void DeleteParticularFiles(String filePath, String fileStartsWith, String endsWith) {
		File file = new File(filePath);
		for (File file1 : file.listFiles()) {
			if (file1.getName().startsWith(fileStartsWith) && file1.getName().endsWith(endsWith)) {
				file1.delete();
			}
		}
	}

	/**
	 * Create folder on the particular directory.
	 * 
	 * @param folderPath Give folder path in string format.
	 */
	public void CreateFolder(String folderPath) {
		File theDir = new File(folderPath);
		if (!theDir.exists()) {
			theDir.mkdirs();
		}

	}

	// Nirav.

	public void cleartextbox(WebElement element) {
		element.sendKeys(Keys.CONTROL, "a");
		element.sendKeys(Keys.DELETE);
	}

	public void selectvalueoftxtBox(WebElement element) {
		element.sendKeys(Keys.CONTROL, "a");

	}
	
	public void setText(WebElement element,String text) 
	{
		element.sendKeys(Keys.CONTROL, "a");
		element.sendKeys(text);
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;
		}

		return flag;
	}

	public void LaunchWebApplication(String url) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		webDriver.get(url);
		// context.setAttribute("WebDriver", webDriver);
		Thread.sleep(5000);

	}

	/**
	 * Adds Steps following to the entered step number.
	 * 
	 * @param number Enter step number in int format
	 * @param step   Enter step in string format
	 * 
	 */
	public void AddStep(int number, String step) {
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-" + number + "</b> : " + step + ".");
	}

	/**
	 * Adds Result following to the result number if gretaer than zero.
	 * 
	 * @param number Enter result number in int format if available
	 * @param result Enter result in string format
	 */
	public void AddResult(int r, String result) {
		if (r == 0) {
			ExtentTestManager.getTest().log(Status.INFO, "<b>Result</b> : " + result + ".");
		} else {
			ExtentTestManager.getTest().log(Status.INFO, "<b>Result-" + r + "</b> : " + result + ".");
		}
	}
	

	/**
	 * Adds Steps following to the entered step number.
	 * 
	 * @param Enter condition where testcase should get FAIL.
	 * 
	 */
	public void TestCaseFail(String result) {
		ExtentTestManager.getTest().log(Status.FAIL, "<b>Result-" + result + "</b> : " + result + ".");
	}
	
	/**
	 * Adds Note 
	 * 
	 * @param Note   Enter Note in string format
	 * 
	 */
	public void AddNote( String Note) {
		ExtentTestManager.getTest().log(Status.INFO, "<b>Note- </b> : " + Note + ".");
	}

	/**
	 * Enter DropDown menu element and required value to slect.
	 * 
	 * @param element       Enter result number in int format if available
	 * @param requiredValue Enter result in string format
	 * @author Nirav Barad
	 */
	public void SelectValueFromDropDown(WebElement element, String requiredValue)
			throws InterruptedException, MalformedURLException {
		Thread.sleep(1500);
		for (int i = 0; i < 100; i++) {
			String s = element.getText();
			if (s.equals(requiredValue)) {
				break;
			} else {
				element.sendKeys(Keys.ARROW_DOWN);
			}
		}

	}
}
