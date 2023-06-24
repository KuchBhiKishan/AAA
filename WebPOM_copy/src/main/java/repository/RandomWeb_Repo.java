package repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.webBase;

public class RandomWeb_Repo extends webBase {

	//Trial
	
	@FindBy(xpath = "//input[@id='gsc-i-id1' and @name='search']")
	public WebElement searchbox_javatpoint;
	

	public RandomWeb_Repo() {
		
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

}
