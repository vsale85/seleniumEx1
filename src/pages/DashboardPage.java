package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends Waiters{
	WebDriver driver;
	WebElement logoutBtn;
	WebElement useCaseCard;
	
	// CONSTRUCTOR
	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	// GETTERS
	public WebElement getUseCaseCard() {
		return driver.findElement(By.xpath("//div[@data-testid=\"use_cases_card_id\"]"));
	}

	public WebElement getLogoutBtn() {
		return driver.findElement(By.xpath("//a[@href='#top']"));
	}

	public void navigateToUseCases() {
		getUseCaseCard().click();
	}
	// METHODS
	public boolean assertLogin() {
		waitForElementToAppear(By.xpath("//a[@href='#top']"));
		if (getLogoutBtn().getText().equals("Logout")) {
			return true;
		} else {
			return false;
		}
	}
}
