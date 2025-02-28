package webpages.reviewerspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

public class CreateReviewersPage extends WebPage {

    public CreateReviewersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(), 'Add Reviewers')]")
    protected WebElement addReviewerButtonElement;

    @FindBy(css = "div.materialize-tags input.n-tag.tt-input")
    protected WebElement inputEmail;

    @FindBy(css = "button#js_addReviewerConfirm")
    protected WebElement nextButtonElement;

    @FindBy(css = "button#js_confirm_create")
    protected WebElement confirmButtonElement;

    @FindBy(css = "#js_name_container")
    protected WebElement newReviewerElement;

    @FindBy(css = "input#js_portal_list_search")
    protected WebElement searchBarElement;

    @FindBy(css ="ul#js_scroll_content")
    protected WebElement searchResult;

    @FindBy(css = "div > ul#js_scroll_content a.flex-auto")
    protected WebElement selectReviewerElement;

    public void createReviewers(String emailId) {
        waitClick(inputEmail);
        inputEmail.sendKeys(emailId);
        pressEnterButton();
        waitForVisibilityAndClick(nextButtonElement);
        waitForVisibilityAndClick(confirmButtonElement);
    }

    public void clickAddReviewers() {
        waitForVisibilityAndClick(addReviewerButtonElement);
    }

    public boolean verifyAddedReviewer(String emailId) {
        searchSelectAndClick(searchBarElement, emailId, selectReviewerElement);
        waitForElementVisibility(newReviewerElement);
        if(newReviewerElement.getText().contains(emailId)) {
            return true;
        } else {
            return false;
        }
    }
}
