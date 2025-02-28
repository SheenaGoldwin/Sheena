package webpages.reviewerspage;

import generalutils.UsedData;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

import java.awt.event.KeyEvent;
import java.util.List;

public class AutoAssignPage extends WebPage {

    public AutoAssignPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.materialize-tags > span > input[placeholder='Enter email']")
    protected WebElement enterEmailElement;

    @FindBy(css = "button#js_addAutoAssignReviewerConfirm")
    protected WebElement autoAssignNextElement;

    @FindBy(css = "input#js_addAutoCandidates")
    protected WebElement selectTalentElement;

    @FindBy(css = "div[role='listbox'] > div > div:nth-child(2)")
    protected WebElement sampleDropDownElement;

    @FindBy(css = "div > div div[data-cy='div_interview_info']")
    protected WebElement talentDropDownElement;

    @FindBy(css = "div > button#js_auto_confirm_create")
    protected WebElement autoAssignConfirmElement;

    @FindBy(css = "input#js_portal_list_search")
    protected WebElement searchBarElement;

    @FindBy(xpath = "//*[contains(text(), 'merlin1@gmail.com')]")
    protected WebElement searchResult;

    @FindBy(css = "div.row.center-align.no-margin-bottom > div > div > div > div > i")
    protected List<WebElement> autoAssignedTalents;

    @FindBy(css = "div.row.no-margin-bottom > div:nth-child(4) > div > div > div:nth-child(3)")
    protected WebElement selectTalentContainerElement;

    public String autoAssign(String emailId) {
        actions.moveToElement(enterEmailElement).click().perform();
        enterEmailElement.sendKeys(emailId);
        pressEnterButton();
        waitClick(autoAssignNextElement);
        selectTalentElement.click();
        selectTalentElement.sendKeys(UsedData.talentSearch);
        waitForVisibilityAndClick(sampleDropDownElement);
        waitForElementVisibility(selectTalentContainerElement);
        String talent = selectTalentContainerElement.getText();
        try {
            waitForElementVisibility(autoAssignConfirmElement);
        } catch (ElementClickInterceptedException e) {
            waitForElementVisibility(autoAssignConfirmElement);
            moveToElementDown(autoAssignConfirmElement);
            pressEnterButton();
        }
        autoAssignConfirmElement.click();
        return talent;
    }

    public boolean verifyAutoAssign(String talent1, String emailId) {
        searchElement(searchBarElement, emailId);
        try{
            waitClick(searchResult);
        } catch (RuntimeException e) {
            moveToElementDown(searchResult);
            searchResult.click();
        }
        waitForListOfElementsVisibility(autoAssignedTalents);
        boolean talentStatus = false;
        for (WebElement value : autoAssignedTalents) {
            String assignedTalent = value.getText();
            if(assignedTalent.contains(talent1)) {
                talentStatus = true;
                break;
            }
        }
        return talentStatus;
    }
}