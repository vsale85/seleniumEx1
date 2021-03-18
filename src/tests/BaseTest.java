package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.DashboardPage;
import pages.ExcelReader;
import pages.LoginPage;
import pages.UseCasesPage;
import pages.Waiters;

public class BaseTest {

	WebDriver driver;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	UseCasesPage useCasePage;
	ExcelReader reader;
	Waiters waiters;

	@BeforeClass
	public void setUpForTests() throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		useCasePage = new UseCasesPage(driver);
		reader = new ExcelReader("data/useCasesData.xlsx");
		waiters = new Waiters(driver);
		driver.manage().window().maximize();
		driver.navigate().to("https://qa-sandbox.apps.htec.rs");
	}

	@AfterClass
	public void finishTests() throws IOException {
		reader.closeFis();
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
