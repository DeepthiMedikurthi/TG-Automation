package com.tgr.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.tgr.Utilities.MyOwnException;

import wrapper.classes.methods.MyWait;
import wrapper.classes.methods.MyWebElement;

public class DriversPage extends CommonPage {

	private static final Logger log = LogManager.getLogger(DriversPage.class.getName());

	WebDriver ldriver;
	ExtentTest testCase;

	public DriversPage(WebDriver driver) {
		super(driver);

		this.ldriver = driver;
		PageFactory.initElements(driver, this);
	}

	// ===================== PAGE OBJECTS ======================

	@FindBy(how = How.NAME, using = "driver[0].firstName")
	public WebElement firstName;
	@FindBy(how = How.XPATH, using = "//*[@id=\"driver1\"]/div[3]/div/div/label[2]")
	public WebElement gender;
	@FindBy(how = How.NAME, using = "driver[0].birthDate.mo")
	public WebElement month;
	@FindBy(how = How.NAME, using = "driver[0].birthDate.dy")
	public WebElement date;
	@FindBy(how = How.NAME, using = "driver[0].birthDate.yr")
	public WebElement year;
	@FindBy(how = How.ID, using = "maritalStatus")
	public WebElement maritalStatus;
	@FindBy(how = How.XPATH, using = "//*[@id=\"sr221\"]/div/div/label[2]")
	public WebElement sr22;
	@FindBy(how = How.XPATH, using = "//*[@id=\"takenDriverCourse0\"]/div[1]/div/label[2]")
	public WebElement takenDriverCourse;

	@FindBy(how = How.NAME, using = "driver[0].ageFirstLicensed")
	public WebElement ageFirstLicensed;
	@FindBy(how = How.XPATH, using = "//*[@id=\"daccCntN0lbl\"]")
	public WebElement previousAcidents;
	@FindBy(how = How.LINK_TEXT, using = "Continue")
	public WebElement continueLink;

	// ===================== PAGE METHODS ======================

	public void driversInfo() throws InterruptedException, MyOwnException {

		log.info("METHOD(login) EXECUTION STARTED SUCCESSFULLY");
		try {
			MyWait.until(ldriver, "ELEMENT_VISIBLE", 90, continueLink);
			if (currentHash.get("state").equals("NC")) {
				MyWebElement.enterText(firstName, "Test"+randomNameString(3));
				MyWebElement.enterText(month, "10");
				MyWebElement.enterText(date, "10");
				MyWebElement.enterText(year, "1990");
				Select marriedStatus = new Select(maritalStatus);
				marriedStatus.selectByVisibleText("Single");
				Select ageFirstlicensed = new Select(ageFirstLicensed);
				ageFirstlicensed.selectByVisibleText("17");
				previousAcidents.click();
			} else {
				MyWebElement.enterText(month, "10");
				MyWebElement.enterText(date, "10");
				MyWebElement.enterText(year, "1990");
				gender.click();
				Select marriedStatus = new Select(maritalStatus);
				marriedStatus.selectByVisibleText("Single");

				sr22.click();
				takenDriverCourse.click();
				previousAcidents.click();
			}
			continueLink.click();

		} catch (AssertionError exp) {
			log.error(exp.getMessage());

			throwException("UNABLE TO open INTO THE TGR APPLICATION FROM THE METHOD login\n" + exp.getMessage() + "\n");
		} catch (Exception exp) {
			log.error(exp.getMessage());

			throwException("UNABLE TO open INTO THE TGR APPLICATION FROM THE METHOD login\n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");
	}

}
