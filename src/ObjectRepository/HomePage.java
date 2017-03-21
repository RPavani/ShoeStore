package ObjectRepository;

import org.openqa.selenium.WebElement;

public class HomePage {

	public static final String EmailField_TXT = "//input[@id = 'remind_email_input']";
	public static final String EmailField_SUBMIT = "//input[@id = 'remind_email_submit']";
	public static final String Verification_TXT = "//div[@class='flash flash_success']";
	public static final String InvalidEmailid_TXT = "//div[@class='flash alert_danger']";
	public static final String NumberOfShoes = "//ul[@id='shoe_list']/li";
		
	public static String getEmailField()
	{
		return EmailField_TXT;
	}
	public static String getEmailsubmitfield()
	{
		return EmailField_SUBMIT;
	}
	
	public static String getValidEmailidText()
	{
		return Verification_TXT;
	}
	
	public static String getInvalidEmailidText()
	{
		return InvalidEmailid_TXT;
	}
	public static String getNumberOfShoes()
	{
		return NumberOfShoes;
	}
	
}
