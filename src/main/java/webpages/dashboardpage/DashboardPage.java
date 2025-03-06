package webpages.dashboardpage;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webpages.WebPage;
import webpages.analyticspage.AnalyticsPage;
import webpages.assessmentpage.AssessmentPage;
import webpages.logoutpage.ProfilePage;
import webpages.reviewerspage.ReviewersPage;

public class DashboardPage extends WebPage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[data-cy='header_nav'] > div  a:nth-child(3)")
    protected WebElement reviewerButtonElement;

    @FindBy(css = "div[data-cy='header_nav'] > div  a:last-child > span:first-child")
    protected WebElement analyticsButtonElement;

    @FindBy(css = "div > a[data-cy='nav_pipeline']")
    protected WebElement assessmentsButtonElement;

    @FindBy(css = "ul#js_navContainerLeft > li:first-child > a")
    protected WebElement secondaryAssessmentsButtonElement;

    @FindBy(css = "div[data-cy='nav_userInfo']")
    protected WebElement profileElement;

    @FindBy(css = "div[data-cy='header_nav'] > div > button")
    protected WebElement hamburgerMenuButton;

    @FindBy(css = "div.MobileDrawer-flexAuto > a:first-child")
    protected WebElement assessmentsMenuButton;

    @FindBy(css = "div.MobileDrawer-flexAuto > a:nth-child(2)")
    protected WebElement reviewersMenuButton;

    @FindBy(css = "div.MobileDrawer-flexAuto > a:nth-child(4)")
    protected WebElement analyticsMenuButton;

    public AnalyticsPage getAnalyticsPage() {
        try {
            waitForVisibilityAndClick(analyticsButtonElement);
        } catch (TimeoutException e) {
            hamburgerMenuButton.click();
            analyticsMenuButton.click();
        }
        return PageFactory.initElements(driver, AnalyticsPage.class);
    }

    public AssessmentPage getAssessmentPage() {
        try {
            waitForVisibilityAndClick(assessmentsButtonElement);
        } catch (TimeoutException e) {
            hamburgerMenuButton.click();
            assessmentsMenuButton.click();
        }
        return PageFactory.initElements(driver, AssessmentPage.class);
    }

    public ReviewersPage getReviewersPage() {
        try {
            waitForVisibilityAndClick(reviewerButtonElement);
        } catch (TimeoutException e) {
            hamburgerMenuButton.click();
            reviewersMenuButton.click();
        }
        return PageFactory.initElements(driver, ReviewersPage.class);
    }

    public ProfilePage getProfilePage() {
        waitForVisibilityAndClick(profileElement);
        return PageFactory.initElements(driver, ProfilePage.class);
    }

    public AssessmentPage goToAssessmentPageUsingSecKey() {
        waitForVisibilityAndClick(secondaryAssessmentsButtonElement);
        return PageFactory.initElements(driver, AssessmentPage.class);
    }

}