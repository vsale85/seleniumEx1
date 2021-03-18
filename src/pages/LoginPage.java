package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Waiters{
	WebDriver driver;
	WebElement loginBtn;
	WebElement usernameField;
	WebElement passwordField;
	WebElement submitLogin;
	private String username = "sasa.vasiljevic@gmail.com";
	private String password = "jobexam";
	// CONSTRUCTOR
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	// GETTERS
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getLoginBtn() {
		return driver.findElement(By.xpath("//a[@href=\"/login\"]"));
	}

	public WebElement getUsernameField() {
		return driver.findElement(By.name("email"));
	}

	public WebElement getPasswordField() {
		return driver.findElement(By.name("password"));
	}

	public WebElement getSubmitLogin() {
		return driver.findElement(By.xpath("//button[@data-testid='submit_btn']"));
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	// METHODS
	public void logIn() {
		waitElementToClick(getLoginBtn());
		getUsernameField().clear();
		getUsernameField().sendKeys(getUsername());
		getPasswordField().clear();
		getPasswordField().sendKeys(getPassword());
		getSubmitLogin().click();

	}
}
