package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tests extends BaseTest {

	@BeforeMethod
	public void setUpForTest() throws InterruptedException {
		Thread.sleep(3000);
	}

	@Test(priority = 5)
	public void validLogIn() {
		loginPage.logIn();
		assertTrue(dashboardPage.assertLogin());
	}

	@Test(priority = 10)
	public void navigateToUseCases() throws InterruptedException {
		Thread.sleep(2000);
		dashboardPage.navigateToUseCases();
		assertTrue(useCasePage.assertUseCasesPage());
	}

	@Test(priority = 15)
	public void create4Cases() throws InterruptedException {
		int countBefore = useCasePage.countCases();
		int countAfter = 0;
		for (int i = 0; i < 4; i++) {			
			useCasePage.createCase(reader.getCellData("useCases", i + 2, 1),
					reader.getCellData("useCases", i + 2, 2),
					reader.getCellData("useCases", i + 2, 3),
					reader.getCellData("useCases", i + 2, 4));
			countAfter++;
		}		
		assertEquals(countBefore, useCasePage.countCases() - countAfter);
	}
	
	@Test(priority = 20)
	public void updateCases() throws InterruptedException {
		useCasePage.updateCases();
		assertTrue(useCasePage.assertCasesUpdate());
	}

	@AfterMethod
	public void afterMethod() {	
		driver.navigate().refresh();
	}
}
