package repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.webBase;

public class repository1 extends webBase {

	//Trial
	
	@FindBy(xpath = "//a[text()=' Java']")
	public WebElement btn_Java;
	
	@FindBy(xpath = "//input[@name='phone']")
	public WebElement Username;
	@FindBy(xpath = "//input[@name='password']")
	public WebElement Password;
	@FindBy(xpath = "//span[text()='Login']")
	public WebElement button_Login;
	
	// Creating table
	
	@FindBy(xpath = "//a[text()='table']")
	public WebElement button_TopPanel_Table;
	@FindBy(xpath = "//span[text()='Add Table']")
	public WebElement button_AddTable;
	@FindBy(xpath = "//input[@name='name_of_table']")
	public WebElement Tablename;
	@FindBy(xpath = "//input[@name='capacity']")
	public WebElement Capacity;
	@FindBy(xpath = "//span[text()='Save']")
	public WebElement SaveTable;
	@FindBy(xpath = "//div[text()='AutoTable6']")
	public WebElement CreatedTable;
	
	// Add food category
	
	@FindBy(xpath = "//*[@id=\"root\"]/div[1]/header/div[2]/button/span[1]")
	public WebElement Hamburger_menu;
	@FindBy(xpath = "//div[text()='Food Category']")
	public WebElement button_FoodCategory;
	@FindBy(xpath = "//span[text()='Add Food Category']")
	public WebElement button_AddFoodCategory;
	@FindBy(xpath = "//input[@name='name']")
	public WebElement FoodCategory_name;
	@FindBy(xpath = "//span[text()='Save']")
	public WebElement FoodCategory_Save;
	@FindBy(xpath = "//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignLeft']")
	public WebElement CreatedFoodCategory;
	
	public boolean VerifyCreatedTable(String tablename) {
		boolean flag = driver.findElement(By.xpath("//div[text()='"+tablename+"']")).isDisplayed();
		return flag;
	}

	public repository1() {
		
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

}
