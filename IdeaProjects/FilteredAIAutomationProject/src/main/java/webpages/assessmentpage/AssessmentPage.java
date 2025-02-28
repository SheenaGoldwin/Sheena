package webpages.assessmentpage;

import generalutils.UsedData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webpages.WebPage;

public class AssessmentPage extends WebPage {

    public AssessmentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.Jobs-spacing.Jobs-newIv > a > button[data-cy='btn_new_iv']")
    protected WebElement plusCreateAssessmentElement;

    @FindBy(css = "div.Jobs-interviewList")
    protected WebElement filterCountElement;

    @FindBy(css = "ul.Jobs-jobList > li:nth-child(3) div.ListItem-firstLine")
    protected WebElement assessmentList;

    @FindBy(css = "button[data-cy='btn_invite_candidate']")
    protected WebElement inviteButtonElement;

    public CreateAssessmentPage goToCreateAssessmentPage() {
        waitClick(plusCreateAssessmentElement);
        return PageFactory.initElements(driver, CreateAssessmentPage.class);
    }

    public CustomizeAssessmentPage goToBuildYourOwnAssessmentPage() {
        waitClick(plusCreateAssessmentElement);
        return PageFactory.initElements(driver, CustomizeAssessmentPage.class);
    }

    public FilterPage goToFilterPage() {
        waitForElementVisibility(inviteButtonElement);
        waitForElementVisibility(filterCountElement);
        return PageFactory.initElements(driver, FilterPage.class);
    }

    public InviteTalentsPage goToInviteTalentsPage() {
        waitClick(assessmentList);
        waitClick(inviteButtonElement);
        return PageFactory.initElements(driver, InviteTalentsPage.class);
    }

    public boolean getUrl() {
        waitForElementVisibility(plusCreateAssessmentElement);
        String url = driver.getCurrentUrl();
        if(url.contains(UsedData.partOfUrlContent)) {
            return true;
        }
        return false;
    }

}