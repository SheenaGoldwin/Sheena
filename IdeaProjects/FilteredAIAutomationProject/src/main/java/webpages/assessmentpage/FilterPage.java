package webpages.assessmentpage;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

public class FilterPage extends WebPage {

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.Jobs-interviewList")
    protected WebElement filterCountElement;

    @FindBy(css = "button[data-cy='btn_invite_candidate']")
    protected WebElement inviteButtonElement;

    @FindBy(css = "button[data-cy='btn-filter']")
    protected WebElement filterButtonElement;

    @FindBy(css = "div.JobsFilterPanel-content input#myCandidates + svg[data-testid='CheckBoxOutlineBlankIcon']")
    protected WebElement talentCheckboxElement;

    @FindBy(css = "div > button:nth-child(2)[data-cy='btn-apply']")
    protected WebElement applyButtonElement;

    @FindBy(css = "div.Jobs-activeFilterContainer > div[role='button']")
    protected WebElement filteredListCountElement;

    public String countBeforeFilter() {
        waitForElementVisibility(inviteButtonElement);
        waitForElementVisibility(filterCountElement);
        String count = filterCountElement.getText();
        System.out.println("here:" + count);
        return count;
    }

    public void applyFilter() {
        waitForElementVisibility(filterButtonElement);
        actions.click(filterButtonElement).perform();
        try {
            actions.click(talentCheckboxElement).perform();
        } catch (Exception e) {
            waitClick(talentCheckboxElement);
        }
        try {
            actions.click(applyButtonElement).perform();
        } catch (Exception e) {
            moveToElementAndClick(applyButtonElement);
        }
    }

    public String countAfterFilter() {
        try {
            waitForElementVisibility(filteredListCountElement);
        } catch(TimeoutException e) {
            waitForElementVisibility(filteredListCountElement);
        }
        String countAfter = filterCountElement.getText();
        System.out.println(countAfter);
        return countAfter;
    }

    public boolean verifyFilterFunction(String before, String after) {
        if(!before.equals(after)) {
            return true;
        }
        return false;
    }

}
