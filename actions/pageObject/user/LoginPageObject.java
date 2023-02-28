package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.user.LoginPageUI;

public class LoginPageObject extends BasePage {
	
	WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void inputToEmailAddressTextbox(String emailAddress) {
		senKeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX,emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		senKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public MyDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.GetMyDashBoardPage(driver);
	}
}
