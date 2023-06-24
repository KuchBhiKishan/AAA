package ListenerSetup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import io.appium.java_client.windows.WindowsDriver;
import pages.BasePage;

public class Screen extends TestListener {

	public void Screnshot() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMMyyyy-HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String timeStamp = dtf.format(now);
		String targetLocation = null;
		String attachPath = null;
		String testMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		String testClassName = getTestClassName(Thread.currentThread().getStackTrace()[2].getClassName());
		// String testMethodName = result.getName().toString().trim();
		System.out.println(testClassName);
		System.out.println(testMethodName);
		String screenShotName = testMethodName + "_" + timeStamp + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ reportFileName + fileSeperator + "PassScreenshots";
		System.out.println(reportsPath);
		try {
			File file = new File(reportsPath + fileSeperator + testClassName); // Set
																				// screenshots
																				// folder
			if (!file.exists()) {
				if (file.mkdirs()) {
					// log.info("Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					// log.info("Failed to create directory: " + file.getAbsolutePath());
				}

			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
			attachPath = "PassScreenshots" + fileSeperator + testClassName + fileSeperator + screenShotName; // location
			File targetFile = new File(targetLocation);
			// log.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
			// log.info("Target File location - " + targetFile.getAbsolutePath());
			FileHandler.copy(screenshotFile, targetFile);

		} catch (FileNotFoundException e) {
			// log.info("File not found exception occurred while taking screenshot " +
			// e.getMessage());
		} catch (Exception e) {
			// log.info("An exception occurred while taking screenshot " + e.getCause());
		}

		// attach screenshots to report
		try {
			ExtentTestManager.getTest().pass("Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(attachPath).build());
		} catch (IOException e) {
			// log.info("An exception occured while taking screenshot " + e.getCause());
		}
	}

	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}
}
