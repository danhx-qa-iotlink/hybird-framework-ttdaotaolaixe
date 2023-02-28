package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.user.CreateNewMotoPageUI;
import pageUIs.user.MotoListPageUI;

public class MotoListPageObject extends BasePage {
	WebDriver driver;

	public MotoListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitlePage() {
		waitForElementVisiable(driver, MotoListPageUI.TITLE_LIST_MOTO);
		return getElementText(driver, MotoListPageUI.TITLE_LIST_MOTO);
	}

	public CreateNewMotoPageObject clickToCreateNewVihicle() {
		waitForElementClickable(driver, MotoListPageUI.TAO_MOI_BUTTON);
		clickToElement(driver, MotoListPageUI.TAO_MOI_BUTTON);
		return PageGeneratorManager.GetCreateNewMotoPage(driver);
	}

	public void inputLicensePlates(String licensePlates) {
waitForElementVisiable(driver, CreateNewMotoPageUI.LICENSE_PLATES_TEXTBOX);
senKeyToElement(driver, CreateNewMotoPageUI.LICENSE_PLATES_TEXTBOX, licensePlates);
	}

	public void chooseRank(String itemText) {
waitForElementClickable(driver, CreateNewMotoPageUI.RANK_DROPDOWNLIST);
selectItemInDefaultDropdown(driver, CreateNewMotoPageUI.RANK_DROPDOWNLIST, itemText);
	}

	public void chooseYear(String year) {
		waitForElementClickable(driver, CreateNewMotoPageUI.YEAR_YEAR_PICKER);
		selectItemInDefaultDropdown(driver, CreateNewMotoPageUI.YEAR_YEAR_PICKER, year);
	}

	public void inputOwner(String owner) {
		waitForElementVisiable(driver, CreateNewMotoPageUI.OWNER_TEXTBOX);
		senKeyToElement(driver, CreateNewMotoPageUI.OWNER_TEXTBOX, owner);		
	}

	public void inputGPLX(String gplx) {
		waitForElementVisiable(driver, CreateNewMotoPageUI.GPLX_TEXTBOX);
		senKeyToElement(driver, CreateNewMotoPageUI.GPLX_TEXTBOX, gplx);			
	}

	public void inputModel(String model) {
		waitForElementVisiable(driver, CreateNewMotoPageUI.MODEL_TEXTBOX);
		senKeyToElement(driver, CreateNewMotoPageUI.MODEL_TEXTBOX, model);			
	}


	public void chooseDeviceLocation(String itemDevice) {
		waitForElementClickable(driver, CreateNewMotoPageUI.DEVICE_DROPDOWNLIST);
		selectItemInDefaultDropdown(driver, CreateNewMotoPageUI.DEVICE_DROPDOWNLIST, itemDevice);
			}
}
