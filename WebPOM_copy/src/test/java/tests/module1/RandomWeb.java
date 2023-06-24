package tests.module1;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.webBase;
import repository.RandomWeb_Repo;

public class RandomWeb extends RandomWeb_Repo {
	
	RandomWeb_Repo repo;
	 
	private void syso() {
		// TODO Auto-generated method stub
System.out.println("ss");
	}
	@BeforeClass
	public void BeforeClass() throws InterruptedException {
		System.out.println("Launching google chrome with new profile..");
		//System.setProperty("webdriver.chrome.driver", prop.getProperty("driverPath") + "chromedriver.exe");
		 WebDriverManager.chromedriver().setup();
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
		repo = new RandomWeb_Repo();
		
	}
	
	
  @Test(description="Video recording")
  public void tc01() throws InterruptedException {
	  
	  addStep(1, "hello n");
	  Thread.sleep(1000);
	
//	  repo.searchbox_javatpoint.sendKeys("nirav");
	  ExtraScreenShot();
	  Thread.sleep(1200);
	  
  }
}
