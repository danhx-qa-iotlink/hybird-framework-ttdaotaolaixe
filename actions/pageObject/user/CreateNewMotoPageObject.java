package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import common.BasePageUI;
import pageUIs.user.MyDashboardPageUI;

public class CreateNewMotoPageObject extends BasePage {
	WebDriver driver;

	public CreateNewMotoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitleMyDashboardPage() {
		waitForElementVisiable(driver, MyDashboardPageUI.TITLE_MY_DASHBOARD);
		return getElementText(driver, MyDashboardPageUI.TITLE_MY_DASHBOARD);
	}


}
