package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	public WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
	switch (browserList) {
	case FIREFOX:
		//Lưu ý: Tạm thời firefox đang lỗi nên dùng hàm tự động cập nhật driver không hoạt động
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver= WebDriverManager.firefoxdriver().create();
		driver = new FirefoxDriver();
		break;
	case CHROME:
		driver =	WebDriverManager.chromedriver().create();
		break;
	case EDGE:
		driver =WebDriverManager.edgedriver().create();
		break;
		default: 
			throw new RuntimeException("null, Browser name is not valid");
		}
	
	driver.get("https://iotlink.stunited.vn/signin");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	System.out.println("Driver ID: "+driver.toString());
	return driver;
}
	
}
