package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UseCasesPage extends Waiters{
	WebDriver driver;
	WebElement createCaseBtn;
	WebElement titleField;
	WebElement descriptionField;
	WebElement expectedResultField;
	WebElement stepsField;
	WebElement submitCaseBtn;
	WebElement switchToAutomated;
	List<WebElement> allSteps;
	List<WebElement> casesList;
	// constructor
	public UseCasesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	// GETTERS
	public WebElement getCreateCaseBtn() {
		return driver.findElement(By.cssSelector("a[href=\"/create-usecase\"]"));
	}

	public WebElement getTitleField() {
		return driver.findElement(By.name("title"));
	}

	public WebElement getDescriptionField() {
		return driver.findElement(By.name("description"));
	}

	public WebElement getExpectedResultField() {
		return driver.findElement(By.name("expected_result"));
	}

	public WebElement getStepsField() {
		return driver.findElement(By.id("stepId"));
	}

	public List<WebElement> getAllSteps() {
		return driver.findElements(By.cssSelector("input[name^='testStepId']"));
	}

	public WebElement getSubmitCaseBtn() {
		return driver.findElement(By.cssSelector("button[data-testid='submit_btn']"));
	}

	public WebElement getSwitchToAutomated() {
		return driver.findElement(By.cssSelector("label[for='switch']"));
	}

	public List<WebElement> getCasesList() {
		return driver.findElements(By.cssSelector("a[href^='/use-cases/']"));
	}
	// METHODS
	public int countCases() {
		return getCasesList().size();
	}
	
	public void createCase(String title, String description, String expected, String steps)
			throws InterruptedException {
		getCreateCaseBtn().click();
		getTitleField().sendKeys(title);
		getDescriptionField().sendKeys(description);
		getExpectedResultField().sendKeys(expected);
		getSwitchToAutomated().click();
		getStepsField().sendKeys(steps);
		Thread.sleep(1500);
		waitElementToClick(getSubmitCaseBtn());
		waitForElementToAppear(By.cssSelector("a[href=\"/create-usecase\"]"));
	}

	public String editText(int numChars) {
		return "This field previously had " + numChars + " characters";
	}
	
	public void updateCases() throws InterruptedException {		
		for (int i = 0; i < getCasesList().size(); i++) {
			getCasesList().get(i).click();
			Thread.sleep(2000);
			int countTitle = getTitleField().getAttribute("value").length();
			getTitleField().clear();
			getTitleField().sendKeys(editText(countTitle));
			int countDescription = getDescriptionField().getText().length();
			getDescriptionField().clear();
			getDescriptionField().sendKeys(editText(countDescription));
			int countExpected = getExpectedResultField().getAttribute("value").length();
			getExpectedResultField().clear();
			getExpectedResultField().sendKeys(editText(countExpected));
			for (int j = 0; j < getAllSteps().size(); j++) {
				int countStep = getAllSteps().get(j).getAttribute("value").length();
				getAllSteps().get(j).clear();
				getAllSteps().get(j).sendKeys(editText(countStep));
			}
			getSubmitCaseBtn().click();
			waitForElementToAppear(By.cssSelector("a[href=\"/create-usecase\"]"));
		}
	}
	
	public boolean assertCasesUpdate() {
		int numCases = countCases();
		int countMetch = 0;
		for (int i = 0; i < getCasesList().size(); i++) {
			String txtToCheck = getCasesList().get(i).getText();
			if (txtToCheck.contains("This field previously had")) {
				countMetch++;
			}			
		}
		if (numCases == countMetch) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean assertUseCasesPage() {
		if (getCreateCaseBtn().isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
}
