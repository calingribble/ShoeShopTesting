import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SubmitEmailReminder {
	
	public void run(WebDriver driver) {
		String testName = "Submit email for reminder";
		//open browser to the shoe store
		driver.get("http://shoestore-manheim.rhcloud.com/");
		
		//Format for test results
		String leftAlignFormat = "| %-22s | %-15s | %-11s | %n";
		int failCount = 0;
		String validity, result;
		String [] emails = {"example@example.com", "12345@example.com", "example@12345.com",
							"example@example", "12345@example", "example@12345",
							"example", "12345", "example12345",
							"example.com", "12345.com", "@.com",
							" ", "@", ".com",};
		
		//Display story name at the beginning of test results
		System.out.println(testName+"...\n");
		
		System.out.format("+------------------------+-----------------+-------------+%n");
		System.out.printf("| Email		         | Validity   	   | Test Result |%n");
		System.out.format("+------------------------+-----------------+-------------+%n");
		
		for(String email : emails){
		if (driver.findElement(By.cssSelector("input[id*='remind_email_input']")).isDisplayed()) {
			driver.findElement(By.cssSelector("input[id*='remind_email_input']")).sendKeys(email);
			if (driver.findElement(By.cssSelector("form[id*='remind_email_form']")).isDisplayed()) {
				driver.findElement(By.cssSelector("form[id*='remind_email_form']")).submit();
				if(driver.findElement(By.cssSelector("div[class*='flash notice']")).isDisplayed()){
					if(emailIsValid(email)){
						validity = "Valid Email";
					}else{
						validity = "Invalid Email";
					}
					if(driver.findElement(By.cssSelector("div[class*='flash notice']")).getText().equals("Thanks! We will notify you of our new shoes at this email: "+email)){
						result = "pass";
					}else{
						result = "***FAIL***";
						failCount++;
					}
					System.out.format(leftAlignFormat, email, validity, result);
					System.out.format("+------------------------+-----------------+-------------+%n");
				}
				
			}
		}
		}
		System.out.println("There are "+failCount+" failed tests in '"+testName+"'.\n");
		
	}
	
	public boolean emailIsValid(String email) {
	       java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");
	       java.util.regex.Matcher m = p.matcher(email);
	       return m.matches();
	}
}
