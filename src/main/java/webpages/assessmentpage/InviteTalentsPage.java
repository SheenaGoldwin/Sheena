package webpages.assessmentpage;

import generalutils.UsedData;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

import java.util.List;

public class InviteTalentsPage extends WebPage {

    public InviteTalentsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[role='presentation'] div > input#invite-emails")
    protected WebElement inviteEmailElement;

    @FindBy(css = "div[role='dialog'] > div:nth-child(3) > button:nth-child(2)[type='button']")
    protected WebElement sendLaterButtonElement;

    @FindBy(css = "div[role='dialog']")
    protected WebElement calenderAppearenceElement;

    @FindBy(css = "div[aria-live='polite'] > div > div")
    protected WebElement monthDayTextElement;

    @FindBy(css = "div > button[title='Next month']")
    protected WebElement nextMonthArrowButtonElement;

    @FindBy(css = "div[role='dialog'] > div > div > div:nth-child(3) > button:last-child > div")
    protected WebElement saveButtonElement;

    @FindBy(css = "div[role='dialog'] > div:last-child")
    protected WebElement billingPageElement;

    @FindBy(css = "div[role='presentation'] > div > div:nth-child(3)")
    protected WebElement assessmentScheduledLater;

    @FindBy(css = "div > div[role='dialog'] > button:first-child")
    protected WebElement modalCloseButtonElement;

    @FindBy(css = "div[role='dialog']")
    protected WebElement viewModal;

    @FindBy(css = "div[aria-live='polite']")
    protected WebElement viewMonthYear;

    @FindBy(css = "div[role='rowgroup'] > div > button")
    protected List<WebElement> allDates;

    public String inviteTalentToAssessment(String emailId) {
        String selectedMonthYear = "May 2025";
        String selectDate = "24";
        waitForVisibilityAndClick(inviteEmailElement);
        inviteEmailElement.sendKeys(emailId);
        waitClick(sendLaterButtonElement);
        waitForElementVisibility(calenderAppearenceElement);

        String monthDay = monthDayTextElement.getText();
        while (!monthDay.equals(selectedMonthYear)) {
            waitClick(nextMonthArrowButtonElement);
            monthDay = monthDayTextElement.getText();
        }
        waitForElementVisibility(viewMonthYear);
        pauseExecution(2);
        for(WebElement dateToSend : allDates) {
            if(dateToSend.getText().contains(selectDate)) {
                actions.click(dateToSend).perform();
                break;
            }
        }
        waitClick(saveButtonElement);
        waitForElementVisibility(viewModal);
        pauseExecution(1);
        String billing;
        try{
            billing = assessmentScheduledLater.getText();
        }catch(TimeoutException e) {
            billing = billingPageElement.getText();
        }
        waitClick(modalCloseButtonElement);
        return billing;
    }

    public boolean verifyInviteSent(String billingDetails) {
        if(billingDetails.contains(UsedData.verifyAltInvite)) {
            return true;
        }
        return false;
    }
}
