import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MonthlyDisplayNewRelease {
	
	public void run(WebDriver driver) {
		String testName = "Monthly display of new releases";
		//open browser to the shoe store
		driver.get("http://shoestore-manheim.rhcloud.com/");
		
		//grab months by link name, grab shoes from shoe list
		By byMonths = By.cssSelector("a[href*='month']");
		By byShoes = By.xpath("//ul[@id='shoe_list']/li");
		
		//Store shoes and months to list
		List<WebElement> allShoes = driver.findElements(byShoes); 
		List<WebElement> allMonths = driver.findElements(byMonths);
		
		//Format for test results
		String leftAlignFormat = "| %-15s | %-11s | %-11s | %-11s | %n";
		int failCount = 0;
		String shoe, blurb, image, price;
		
		
		//Display story name at the beginning of test results
		System.out.println(testName+"...\n");
		
		//Iterate through all months
		for (int pos = 0; pos < allMonths.size(); pos++) {
			if (getElementWithIndex(byMonths, pos, driver).isDisplayed()) {
				getElementWithIndex(byMonths, pos, driver).click();
				allShoes = driver.findElements(byShoes);
					
					System.out.println(getElementWithIndex(byMonths, pos,driver).getText());
					System.out.format("+-----------------+-------------+-------------+-------------+%n");
					System.out.printf("| Shoes		  | Blurb   	| Image	      | Price       |%n");
					System.out.format("+-----------------+-------------+-------------+-------------+%n");
					int i = 1;
					
					//Iterate through all shoes for each month
					for (WebElement element: allShoes) {
						//Does the shoe have a blurb?
						if(thereIsABlurb(element)){
							blurb = "pass";
						}else{
							blurb = "***FAIL***";
							failCount++;
						}
						//Does the shoe have an image?
						if(thereIsAnImage(element)){
							image = "pass";
						}else{
							image = "***FAIL***";
							failCount++;
						}
						//Does the shoe have a price?
						if(thereIsAPrice(element)){
							price = "pass";
						}else{
							price = "***FAIL***";
							failCount++;
						}
						shoe = "Shoe "+i;
						System.out.format(leftAlignFormat, shoe, blurb, image, price);
						System.out.format("+-----------------+-------------+-------------+-------------+%n");
						i++;
					}
					//If the page has no shoes, print no results
					if(i == 1) System.out.println("***No Results***");
					System.out.println();
				}
			
		}	
		System.out.println("There are "+failCount+" failed tests in '"+testName+"'.\n");
	}

	public WebElement getElementWithIndex(By by, int pos, WebDriver driver) {
		return driver.findElements(by).get((int) pos);
	}
	
	public boolean thereIsABlurb(WebElement we){
		return we.findElement(By.xpath(".//td[@class='shoe_result_value shoe_description']")).isDisplayed();
	}
	
	public boolean thereIsAnImage(WebElement we){
		return we.findElement(By.xpath(".//td[@class='shoe_image']")).isDisplayed();
	}
	
	public boolean thereIsAPrice(WebElement we){
		return we.findElement(By.xpath(".//td[@class='shoe_result_value shoe_price']")).isDisplayed();
	}
}