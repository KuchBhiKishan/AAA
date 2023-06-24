package ListenerSetup;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.io.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;

import io.appium.java_client.windows.WindowsDriver;
import pages.webBase;

public class TestListener extends webBase implements ITestListener {
	public static String  reportFileName;

	public String getClassName(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		return iTestContext.getCurrentXmlTest().getClasses().stream().findFirst().get().getName();
	}

	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
		// added second to report name because if it removes first report for same minutes.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMMyyyy-HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		reportFileName = "Report_"+dtf.format(now);
		System.out.println(reportFileName);
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		String ss = result.getMethod().getDescription();

		ExtentTestManager.getTest().log(Status.INFO,
				String.format("<b style=\"color:#000080;font-size:18px;\">Test Description : " + ss + "</b>"));

	}

	public void onTestSuccess(ITestResult result) {
		// log.info("*** Test execution " + result.getMethod().getMethodName() + "
		// failed...");
		// log.info((result.getMethod().getMethodName() + " failed!"));

		ITestContext context = result.getTestContext();
//		WindowsDriver driver = null;
//		driver = (WindowsDriver) context.getAttribute("WebDriver");
//		WebDriver driver = null;
//        driver = (WebDriver) context.getAttribute("WebDriver");
		String targetLocation = null;
		String attachPath =null;
		
		String testClassName = getTestClassName(result.getInstanceName()).trim();
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator +  "TestReport"+ fileSeperator+ reportFileName + fileSeperator +  "PassScreenshots";
		// log.info("Screen shots reports path - " + reportsPath);
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
		 	attachPath=	"PassScreenshots"+fileSeperator + testClassName + fileSeperator + screenShotName;		// location
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
		ExtentTestManager.getTest().log(Status.PASS, "Test Pass");
//                            System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
//                            ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

//            public void onTestFailure(ITestResult result) {
//                            System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
//                            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
//            }
	public void onTestFailure(ITestResult result) {
		// log.info("*** Test execution " + result.getMethod().getMethodName() + "
		// failed...");
		// log.info((result.getMethod().getMethodName() + " failed!"));

		ITestContext context = result.getTestContext();
		// WebDriver driver = (WebDriver) context.getAttribute("driver");
//		WebDriver driver = null;
//        driver = (WebDriver) context.getAttribute("WebDriver");
		String targetLocation = null;
		String attachPath =null;

		String testClassName = getTestClassName(result.getInstanceName()).trim();
		// String timeStamp = Util.getCurrentTimeStamp(); // get timestamp
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator +  "TestReport"+ fileSeperator+ reportFileName + fileSeperator
				+  "FailScreenshots";
		// log.info("Screen shots reports path - " + reportsPath);
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
		 	attachPath=	"FailScreenshots"+fileSeperator + testClassName + fileSeperator + screenShotName;		// location
			File targetFile = new File(targetLocation);														// location
			
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

			ExtentTestManager.getTest().log(Status.FAIL, "Failed Case is: " + result.getName());

			ExtentTestManager.getTest().fail("Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(attachPath).build());

			ExtentTestManager.getTest().log(Status.FAIL,
					result.getName() + " FAIL with error " + result.getThrowable());

		} catch (IOException e) {
			// log.info("An exception occured while taking screenshot " + e.getCause());
		}
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
	}

	public String getTestClassName(String testName) {
		// TODO Auto-generated method stub
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		ExtentTestManager.getTest().log(Status.SKIP,
				result.getName() + " FAIL with error " + result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}
