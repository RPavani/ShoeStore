package StepDefinitions;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class TestRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = CommonSteps.getBrowser("chrome");
		CommonSteps.gotoHomePage(driver);
		boolean isEmailVerification = true;
		boolean isMonthVerification = true;
		if(isMonthVerification)
		{
			ArrayList<String> months = CommonSteps.getMonths();
			CommonSteps.clickEachMonth(driver,months);
			System.out.println("Months validation is completed");
		}
		if(isEmailVerification)
		{
		CommonSteps.enterTextAndClick(driver);
		boolean emailidverification = CommonSteps.verifyMessage(driver);
		if(emailidverification)
		{
			System.out.println("Email verification is completed");
		}
		}
		
		
		driver.close();
	}

}
