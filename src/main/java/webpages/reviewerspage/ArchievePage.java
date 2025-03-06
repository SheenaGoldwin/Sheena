package webpages.reviewerspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

import java.util.List;

public class ArchievePage extends WebPage {

    public ArchievePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input#js_portal_list_search")
    protected WebElement searchBarElement;

    @FindBy(css ="ul#js_scroll_content")
    protected WebElement searchResult;

    @FindBy(css = "div > div:nth-child(3) > a:nth-child(3)")
    protected WebElement archiveElement;

    @FindBy(css = "a#js_confirm_btn")
    protected WebElement confirmArchiveElement;

    @FindBy(css = "#js_scroll_content")
    protected List<WebElement> noResultsFoundElement;

    public void archive() {
        archiveElement.click();
        waitClick(confirmArchiveElement);
    }

    public boolean verifyArchievedReviewer(String emailId) {
        waitForElementVisibility(searchResult);
        searchSelectForList(searchBarElement, emailId, noResultsFoundElement);
        for(WebElement e : noResultsFoundElement) {
            String value = e.getText();
            if(!value.equalsIgnoreCase(emailId)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}