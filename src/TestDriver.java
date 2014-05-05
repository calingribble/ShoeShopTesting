import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class TestDriver {
	public static void main (String [] args){
		//Google Chrome Web Driver
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeDriver chrome = new ChromeDriver();
		//Story 1: Monthly display of new releases
		new MonthlyDisplayNewRelease().run(chrome);
		//Story 2: Submit email for reminder
		new SubmitEmailReminder().run(chrome);	
		chrome.quit();
		
		
		//Internet Explorer Web Driver
		System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		InternetExplorerDriver ie = new InternetExplorerDriver();
		//Story 1: Monthly display of new releases
		new MonthlyDisplayNewRelease().run(ie);
		//Story 2: Submit email for reminder
		new SubmitEmailReminder().run(ie);
		ie.quit();
	}
}
