package pageObject.user;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	public static LoginPageObject GetLoginPage (WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static MyDashboardPageObject GetMyDashBoardPage (WebDriver driver) {
		return new MyDashboardPageObject(driver);
	}
	public static MotoListPageObject GetMotoListPage(WebDriver driver) {
		return new MotoListPageObject(driver);
	}
	public static CreateNewMotoPageObject GetCreateNewMotoPage(WebDriver driver) {
		return new CreateNewMotoPageObject(driver);
	}
}
