package com.tgq.TGQPageObj;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.tgr.Utilities.MyOwnException;
import wrapper.classes.methods.MyWait;
import wrapper.classes.methods.MyWebElement;

public class TGQ_Consumer_Questions_Page extends PTGQAllPages {

	private static final Logger log = LogManager.getLogger(TGQ_Consumer_Questions_Page.class.getName());

	// Page Factory

	@FindBy(how = How.LINK_TEXT, using = "Verify Address")
	public WebElement tgq_verify_address;
	@FindBy(how = How.XPATH, using = "//*[@id='qas_pending']/h2/span")
	public WebElement tqq_address_text;
	@FindBy(how = How.LINK_TEXT, using = "I am sure the address is correct as entered")
	public WebElement tgq_use_entered_address;
	@SuppressWarnings("rawtypes")
	@FindBy(how = How.XPATH, using = "//*[@id='credit']/table[1]/tbody/tr/td/input[2]")
	public WebElement tgq_action;
	@FindBy(how = How.LINK_TEXT, using = "Order Credit")
	public WebElement tgq_order_credit;
	@FindBy(how = How.LINK_TEXT, using = "Update Credit")
	public WebElement tgq_update_credit;
	@FindBy(how = How.LINK_TEXT, using = "Order Prior Insurance")
	public WebElement tgq_Order_Prior_Insurance;
	@FindBy(how = How.LINK_TEXT, using = "Order MVR")
	public WebElement tgq_order_mvr;

	@SuppressWarnings("rawtypes")
	@FindAll(value = { @FindBy(how = How.XPATH, using = "//*[@id='driver0Vios']/div/input[2]") })
	public LinkedList tgq_mvr_action;
	@FindBy(how = How.LINK_TEXT, using = "Update MVR")
	public WebElement tgq_update_mvr;
	@FindBy(how = How.LINK_TEXT, using = "Order ADD")
	public WebElement tgq_order_add;
	@FindBy(how = How.LINK_TEXT, using = "Order CLUE")
	public WebElement tgq_order_clue;
	@FindBy(how = How.LINK_TEXT, using = "Save")
	public WebElement tgq_save;
	@FindBy(how = How.LINK_TEXT, using = "Next")
	public WebElement tgq_next_btn;
	WebDriver ldriver;

	public TGQ_Consumer_Questions_Page(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void consumerQuestions() throws MyOwnException, InterruptedException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");
		try {
			if (currentHash.get("PolicyType").equals("Bond - No Credit")) {
				if (mywebEle.isElementExist("Verify Address")) {
					if (tqq_address_text.getText().equals("Address verification will be attempted now")) {
						tgq_verify_address.click();

						Thread.sleep(4000);
					}
					myWait.until(ldriver, "ELEMENT_VISIBLE", 10, tgq_use_entered_address);
					if (tqq_address_text.getText().equals("Address verification")) {
						tgq_use_entered_address.click();
						Thread.sleep(4000);
					}
				}
				if (mywebEle.isElementExist("Order MVR")) {
					tgq_order_mvr.click();
					Thread.sleep(4000);
					if (mywebEle.isDropdownExist("order[2].action.value")) {
						List<WebElement> rdBtn_mvr = ldriver.findElements(By.name("order[2].action.value"));
						rdBtn_mvr.get(0).click();
						tgq_update_mvr.click();
						Thread.sleep(4000);
					}
				}

			} else {
				if (mywebEle.isElementExist("Verify Address")) {
					if (tqq_address_text.getText().equals("Address verification will be attempted now")) {
						tgq_verify_address.click();
						Thread.sleep(4000);
					}
					myWait.until(ldriver, "ELEMENT_VISIBLE", 10, tgq_use_entered_address);
					if (tqq_address_text.getText().equals("Address verification")) {
						tgq_use_entered_address.click();
						Thread.sleep(4000);
					}
				}
				if (mywebEle.isElementExist("Order Credit")) {
					tgq_action.click();
					tgq_order_credit.click();
					Thread.sleep(4000);
				}
				if (mywebEle.isElementExist("Order Prior Insurance")) {
					tgq_Order_Prior_Insurance.click();
					Thread.sleep(4000);
				}
				if (mywebEle.isElementExist("Order MVR")) {
					tgq_order_mvr.click();
					Thread.sleep(4000);
					if (mywebEle.isDropdownExist("order[2].action.value")) {
						List<WebElement> rdBtn_mvr = ldriver.findElements(By.name("order[2].action.value"));
						rdBtn_mvr.get(0).click();
						tgq_update_mvr.click();
						Thread.sleep(4000);
					}
				}
				if (mywebEle.isElementExist("Order ADD")) {
					tgq_order_add.click();
				}
				
				if (mywebEle.isElementExist("Order CLUE")) {
					tgq_order_clue.click();
					Thread.sleep(4000);
				}
			}

			tgq_save.click();
			Thread.sleep(4000);
			/*
			 * base.screenShot("./Results/Screenshots_" + testCaseNumber+ testRunTimeStamp +
			 * "/" + "Consumer Questions Tab.png");
			 * reportVar.logTestCaseStatusWithSnapShot(parentTestCase,
			 * "PASS","Opened Consumer Questions Tab","./Results/Screenshots_" +
			 * testCaseNumber+ testRunTimeStamp + "/" + "Consumer Questions Tab.png");
			 */
			tgq_next_btn.click();
			Thread.sleep(4000);
		} catch (Exception exp) {
			log.error(exp.getMessage());
			/*
			 * base.screenShot("./Results/Screenshots_" +testCaseNumber+ testRunTimeStamp +
			 * "/" + "Error in Opening Consumer Questions Tab.png");
			 * reportVar.logTestCaseStatusWithSnapShot(parentTestCase, "FAIL",
			 * "<font color=red><b>Error while Opening Consumer Questions Tab: </b></font><br />"
			 * + exp.getMessage() + "<br />", "./Results/Screenshots_"+testCaseNumber+
			 * testRunTimeStamp + "/" + "Error in Opening Consumer Questions Tab.png");
			 */
			throwException("Unable To open the Consumer Questions Tab \n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");

	}

}
