package StepDefinitions;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import ObjectRepository.HomePage;

public class CommonSteps {
	
	public static WebDriver getBrowser(String browser)
	{
		WebDriver driver = null;
		if(browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\SeleniumDrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		return driver;
	}
	
	public static void gotoHomePage(WebDriver driver)
	{
		try
		{
		final String URL = "https://rb-shoe-store.herokuapp.com/";
		driver.get(URL);
		driver.manage().window().maximize();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public static WebElement getElementByProperty(String objectProperty,WebDriver driver)
	{
		WebElement webelement = null;
		try
		{
		webelement = driver.findElement(By.xpath(objectProperty));
		}catch(Exception e){
			System.out.println(e);
		}
		return webelement;
	}
	
	public static void enterTextAndClick(WebDriver driver)
	{
		WebElement textfield = CommonSteps.getElementByProperty(HomePage.EmailField_TXT,driver);
		try
		{
		textfield.clear();
		textfield.sendKeys(randomEmail());
		if(textfield.isDisplayed())
		{
			System.out.println("Text is entered successfully");
			
			String submitfield = HomePage.getEmailsubmitfield();
			WebElement submitQuery = getElementByProperty(submitfield, driver);
			submitQuery.click();
		}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public static String randomEmail() {
		String emailid = "random-" + UUID.randomUUID().toString() + "@example.com";
		return emailid;
    }
	public static boolean verifyMessage(WebDriver driver)
	{
		boolean isVerified = false;
		try
		{
		String message = HomePage.getValidEmailidText();
		WebElement successMessage = getElementByProperty(message,driver);
		if (successMessage.isDisplayed())
		{
			System.out.println("Valid email id verification is success");
			isVerified = true;
		}
		}catch(Exception e){
			System.out.println(e);
		}
		return isVerified;
	}
	public static ArrayList<String> getMonths()
	{
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		ArrayList<String> monthsList = new ArrayList<String>();
		for (int i = 0; i < months.length; i++) 
		{
		      String month = months[i];
		      System.out.println(month);
		      monthsList .add(months[i]);  
		}
		
		return monthsList;
		
	}
	public static void clickEachMonth(WebDriver driver,ArrayList<String> months)
	{
		int noofmonths = months.size();
		System.out.println(noofmonths);
		
		for(int i = 0;i<noofmonths;i++)
		{
			String month = months.get(i).toString();
			if(month.equalsIgnoreCase("January") || month.equalsIgnoreCase("February") || month.equalsIgnoreCase("March") || month.equalsIgnoreCase("April") ||month.equalsIgnoreCase("May")
		    		  || month.equalsIgnoreCase("June") || month.equalsIgnoreCase("July") || month.equalsIgnoreCase("August") || month.equalsIgnoreCase("September") || month.equalsIgnoreCase("October") ||
		    		  month.equalsIgnoreCase("November") || month.equalsIgnoreCase("December"))
			{
				driver.findElement(By.linkText(month)).click();
				System.out.println(month);
				verifyShoeDetails(driver);
				driver.navigate().back();
			}
			else
			{
				System.out.println("Not a valid month");
			}
		}			
	
	}
	
	public static void verifyShoeDetails(WebDriver driver)
	{
		try
		{
			int numberofshoes = driver.findElements(By.xpath(HomePage.NumberOfShoes)).size();
			System.out.println("Number of shoes:" +numberofshoes);
			for(int i = 1;i<= numberofshoes;i++)
			{
				List<WebElement> Rows = driver.findElements(By.xpath("//ul[@id='shoe_list']/li["+i+"]/div/table/tbody/tr"));
				System.out.println("**************************************");
				
				for(int j = 1;j<= Rows.size();j++)
				{	
					String s1 = driver.findElement(By.xpath("//ul[@id='shoe_list']/li["+i+"]/div/table/tbody/tr["+j+"]/td[1]")).getText();
					s1.trim();
										
					if(s1.equalsIgnoreCase("Brand"))
					{
						String brand = driver.findElement(By.xpath("//ul[@id='shoe_list']/li["+i+"]/div/table/tbody/tr["+j+"]/td[2]/a")).getText();
						if(brand.isEmpty())
						{
							System.out.println("Brand is not displayed");
						}
						else
						{
							System.out.println("Brand is displayed");
						}
						
					}
					else if(s1.equalsIgnoreCase("Name"))
					{
						String name = driver.findElement(By.xpath("//ul[@id='shoe_list']/li["+i+"]/div/table/tbody/tr["+j+"]/td[2]")).getText();
						if(name.isEmpty())
						{
							System.out.println("Name is not displayed");
						}
						else
						{
							System.out.println("Name is displayed");
						}
						
					}
					else if(s1.equalsIgnoreCase("Price"))
					{
						String price = driver.findElement(By.xpath("//ul[@id='shoe_list']/li["+i+"]/div/table/tbody/tr["+j+"]/td[2]")).getText();
						if(price.isEmpty())
						{
							System.out.println("Price is not displayed");
						}
						else
						{
							System.out.println("Price is displayed");
						}
					}
					else if(s1.equalsIgnoreCase("Description"))
					{
						String description = driver.findElement(By.xpath("//ul[@id='shoe_list']/li["+i+"]/div/table/tbody/tr["+j+"]/td[2]")).getText();
						if(description.isEmpty())
						{
							System.out.println("Description is not displayed");
						}
						else
						{
							System.out.println("Description is displayed");
						}
					}
					else if(s1.equalsIgnoreCase("Release Month"))
					{
						String releaseMonth = driver.findElement(By.xpath("//ul[@id='shoe_list']/li["+i+"]/div/table/tbody/tr["+j+"]/td[2]/a")).getText();
						if(releaseMonth.isEmpty())
						{
							System.out.println("Release Month is not displayed");
						}
						else
						{
							System.out.println("Release Month is displayed");
						}
					}
					else if(s1.isEmpty())
					{
						WebElement ImageFile = driver.findElement(By.xpath("//ul[@id='shoe_list']/li[1]/div/table/tbody/tr["+j+"]/td/img"));
				        
				        Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
				        if (!ImagePresent)
				        {
				             System.out.println("Image is not displayed.");
				        }
				        else
				        {
				            System.out.println("Image is displayed.");
				        }
					}
					else
					{
						break;
					}
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
