package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionClass {
	WebDriver driver;

	public ActionClass(WebDriver driver) {

		this.driver = driver;
	}

	static Actions action;

	public void mouseHoverEvent(WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).click().perform();
		;

	}

	public void scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public Long getPageScrollPosition() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return (Long) executor.executeScript("return window.pageYOffset;");
		}
	
	public String  getFontSize(WebElement text) {
		return text.getCssValue("font-size");
	}
}
