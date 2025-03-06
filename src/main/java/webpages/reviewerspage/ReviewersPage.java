package webpages.reviewerspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webpages.WebPage;

public class ReviewersPage extends WebPage {

    public ReviewersPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[contains(text(), 'PENDING REVIEW')]")
    protected WebElement pendingReviewElement;

    @FindBy(xpath = "//*[contains(text(), 'Add Reviewers')]")
    protected WebElement addReviewerButtonElement;

    @FindBy(xpath = "//*[contains(text(), 'Remind')]")
    protected WebElement remindButtonElement;

    @FindBy(css = "input#js_portal_list_search")
    protected WebElement searchBarElement;

    @FindBy(css ="ul#js_scroll_content")
    protected WebElement searchResult;

    @FindBy(css = "div > div:nth-child(3) > a:nth-child(3)")
    protected WebElement archiveElement;

    @FindBy(css = "a#js_auto_assign_reviewers_btn")
    protected WebElement autoAssignButtonElement;

    public CreateReviewersPage getCreateReviewerPage() {
        waitForElementVisibility(pendingReviewElement);
        waitForVisibilityAndClick(addReviewerButtonElement);
        return PageFactory.initElements(driver, CreateReviewersPage.class);
    }

    public ReminderPage getReminderPage() {
        String emailId = "";
        searchSelectAndClick(searchBarElement, emailId, searchResult);
        waitForVisibilityAndClick(remindButtonElement);
        return PageFactory.initElements(driver, ReminderPage.class);
    }

    public ArchievePage getArchivePage() {
        String emailId = "";
        searchSelect(searchBarElement, emailId, searchResult);
        waitForElementVisibility(archiveElement);
        return PageFactory.initElements(driver, ArchievePage.class);
    }

    public AutoAssignPage getAutoAssignPage() {
        waitForVisibilityAndClick(autoAssignButtonElement);
        return PageFactory.initElements(driver, AutoAssignPage.class);
    }
}