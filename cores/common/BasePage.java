package common;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.user.MotoListPageObject;
import pageObject.user.MyDashboardPageObject;
import pageObject.user.PageGeneratorManager;
import pageUIs.user.MyDashboardPageUI;

/**
 * @author LEGION
 */
public class BasePage {
	// Chính bản thân hàm này có thể khởi tạo 1 đối tượng của class BasePage lên
	// Sau đó trả về đối tượng này 1 class khác

	// Static vào hàm:
	// Thuộc phạm vi của class( Không cần thông qua 1 object/instence gọi ra dùng
	// được)
	// Không thuộc phạm vi object
	public static BasePage getBasePageInstance() {
		return new BasePage();
	}

	/**
	 * Web browser 1 access modifier: public 2 kieu tra ve cua ham 2.1 - void:
	 * Action (click/clear/sendKey/..) 2.2 lay du lieu ra: khac void:
	 * String/int/float/...) get: currentUrl/getTile/getText 3 kieu du lieu tra ve
	 * trong ham 3.1 -void: khong can return 3.2 -#void: thi return dung capacit
	 * Tham so bat buoc moi ham tuong tac voi web browser là "WebDriver"
	 **/
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public void senKeyToAlert(WebDriver driver, String valueToSendKey) {
		waitForAlertPresence(driver).sendKeys(valueToSendKey);
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void switchToWindowByID(WebDriver driver, String currentWindowID) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String id : allWindowID) {
			if (!id.equals(currentWindowID)) {
				driver.switchTo().window(id);
			}
			break;
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String id : allWindowID) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void switchtoWindowByLink(WebDriver driver, String expectedLink) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String id : allWindowID) {
			driver.switchTo().window(id);
			String actualLink = driver.getCurrentUrl();
			if (actualLink.contains(expectedLink)) {
				break;
			}
		}
	}

	public boolean closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String currentWindowID : allWindowID) {
			if (!currentWindowID.equals(parentID)) {
				driver.switchTo().window(currentWindowID);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		} else
			return false;
	}

	/**
	 * Web Elememt Tham so bat buoc moi ham tuong tac voi web element là "WebDriver"
	 * & "String locator" Xpath/css/Id/Name/Class/..
	 **/
	public By getXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getXpath(locator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locator) {
		return driver.findElements(getXpath(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}

	public void senKeyToElement(WebDriver driver, String locator, String valueInput) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(valueInput);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String ItemText) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(ItemText);
	}

	public String getFirstSelectTextItem(WebDriver driver, String locator, String ItemText) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropDownMutiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath,
			String ExpectedItemText) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(2);
		List<WebElement> listChildItems = new WebDriverWait(driver, longTimeOut)
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXpath(childXpath)));
		for (WebElement tempItem : listChildItems) {
			if (tempItem.getText().trim().equals(ExpectedItemText)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempItem);
				sleepInSecond(1);
				tempItem.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getAttribute(propertyName);
	}

	public int getListElementSize(WebDriver driver, String locator) {
		return getListWebElement(driver, locator).size();
	}

	public void checkToCheckOrRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToCheckOrRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	public void switchToIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}

	public void drapAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}

	public void hightlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
				getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
		return status;
	}

	public void waitForElementVisiable(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOfElementLocated(getXpath(locator)));
	}

	public void waitForElementInvisiable(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeOut)
				.until(ExpectedConditions.invisibilityOfElementLocated(getXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.elementToBeClickable(getXpath(locator)));
	}

	public MotoListPageObject clickToMotoIcon(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LIST_MOTO_PAGE_LINK);
		clickToElement(driver, BasePageUI.LIST_MOTO_PAGE_LINK);
		return PageGeneratorManager.GetMotoListPage(driver);
	}

	public void clickToMenuVihicle(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.VIHICLE_MENUBAR);
		clickToElement(driver, BasePageUI.VIHICLE_MENUBAR);
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public int randoomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}

	private long longTimeOut = 30;
}
