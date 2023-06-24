package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import ListenerSetup.ExtentTestManager;
import ListenerSetup.Screen;
import io.github.bonigarcia.wdm.WebDriverManager;
import util.TestUtil;

public class webBase {
	public static WebDriverWait wait;
	public static WebDriver driver=null;
	// private static String driverPath = "E:\\A_WORK\\Automation";
	public static Properties prop;
public static	Screen ss;

	public webBase() {
		try {

			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/config/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private  void setDriver(String browserType, String appURL) throws InterruptedException {
		switch (browserType) {
		case "chrome":
			 initChromeDriver(appURL);
			break;
		case "firefox":
		 initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			initChromeDriver(appURL);
			
		}
	}

	private static void initChromeDriver(String appURL) throws InterruptedException {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", prop.getProperty("driverPath") + "chromedriver.exe");
		// WebDriverManager.chromedriver().setup();
		System.out.println("DONEEEEEEEEEEEEEEE");
	    driver = new ChromeDriver();
	    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$");
		// WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.get("https://www.javatpoint.com/");
		System.out.println("^^^^^^^");
		Thread.sleep(10000);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
	}

	public static void initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", prop.getProperty("driverPath") + "\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
	
	}

	
	@BeforeClass
	public void initializeTestBaseSetup() {
		try {
			
			setDriver(prop.getProperty("browserType"), prop.getProperty("appURL"));

		} catch (Exception e) {
			System.out.println("Error..AAA..." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() {
	   

		driver.quit();
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void ExtraScreenShot() {
		ss = new Screen();
		ss.Screnshot();
	}
	
	public void addStep(int i,String Steps) {
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-"+i+"</b>: "+Steps+"");
	}
	public void addResults(int i,String Result) {
		if(i==0) {
			ExtentTestManager.getTest().log(Status.INFO, "<b>Result-</b>: "+Result+"");
		}else{
			ExtentTestManager.getTest().log(Status.INFO, "<b>Result-"+i+"</b>: "+Result+"");
			i++;
		}
	}
	public void addNote(String Note) {
		ExtentTestManager.getTest().log(Status.INFO, "<b>Note-</b>: "+Note+"");
	}
	
	
	
//	/**
//	 * Enter URL as string
//	 * */
//	public void HitToURL() {
//		
//	}
//	
//	
//	
//	@Parameters({ "appURL" })
//	private WebDriver HitToURL1(String appURL) throws InterruptedException {
//		driver.get(appURL);
//return driver;
//	}
}

